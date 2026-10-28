#!/bin/bash

set -euo pipefail

SERVER="192.168.130.6"
PORT="12022"
USER="root"

REMOTE_DIR="/opt/prepa/target/backend-simul"
WAR_FILE="target/prepa-V1.0.war"

PROPS_FILE="./src/main/resources/application.properties"

export JAVA_HOME=$(/usr/libexec/java_home -v 17)   # macOS
# ou sur Linux :
# export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"

echo "Java utilisé : $(java -version 2>&1 | head -1)"

VERSION=$(date +"%Y.%m.%d")

###############################################################################
# BACKUP CONFIG (IMPORTANT)
###############################################################################

if [ ! -f "$PROPS_FILE" ]; then
    echo "application.properties introuvable"
    exit 1
fi

cp "$PROPS_FILE" "${PROPS_FILE}.bak"

restore() {
    echo "*** RESTORE application.properties ***"
    mv -f "${PROPS_FILE}.bak" "$PROPS_FILE" 2>/dev/null || true
}

trap restore EXIT

###############################################################################
# PATCH CONFIG AVANT BUILD
###############################################################################

echo "*** Patch application.properties (SIMUL) ***"

# changer port
sed -i.bak 's/^server.port=.*/server.port=12082/' "$PROPS_FILE" || true

# ajouter server.address si absent
grep -q "^server.address=" "$PROPS_FILE" || \
    echo "server.address=0.0.0.0" >> "$PROPS_FILE"

###############################################################################
# BUILD
###############################################################################

echo "*** Maven Package ***"
./mvnw clean package -DskipTests

if [ ! -f "$WAR_FILE" ]; then
    echo "WAR introuvable"
    exit 1
fi

###############################################################################
# SSH MASTER (1 seule connexion / 1 password)
###############################################################################

SSH_SOCKET="/tmp/ssh_simul_${SERVER}_${PORT}"

echo "*** Opening SSH session (1 password only) ***"

ssh -p "$PORT" -M -S "$SSH_SOCKET" -fnNT "$USER@$SERVER"

###############################################################################
# CREATE DIR
###############################################################################

ssh -p "$PORT" -S "$SSH_SOCKET" "$USER@$SERVER" << EOF
mkdir -p "$REMOTE_DIR"
EOF

###############################################################################
# UPLOAD WAR
###############################################################################

echo "*** Upload WAR ***"

scp -P "$PORT" -o ControlPath="$SSH_SOCKET" \
    "$WAR_FILE" \
    "$USER@$SERVER:$REMOTE_DIR/prepa-V1.0.war"

###############################################################################
# RESTART SERVICE
###############################################################################

ssh -p "$PORT" -S "$SSH_SOCKET" "$USER@$SERVER" << EOF
systemctl restart prepa-simul.service
sleep 2
systemctl status prepa-simul.service --no-pager
EOF

###############################################################################
# CLOSE SSH SESSION
###############################################################################

ssh -S "$SSH_SOCKET" -O exit "$USER@$SERVER" 2>/dev/null || true

echo "*** DEPLOY DONE ($VERSION) ***"
