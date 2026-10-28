#!/usr/bin/env bash

set -eou pipefail

TARGET_DIR="$HOME/target"
LIBDIR="$HOME/.java/lib"

cd backend/prepa
if ! [ -f "$LIBDIR/jconn4.jar" ]; then
    echo "*** install jconn4.jar ***"
    mkdir -p "$LIBDIR"
    curl -L http://192.168.130.6/jconn4/jconn4.jar -o "$LIBDIR/jconn4.jar"
    ./mvnw install:install-file \
        -Dfile="$LIBDIR/jconn4.jar" \
        -DgroupId=com.sybase.jdbc4.jdbc \
        -DartifactId=jconn4 \
        -Dversion=4 \
        -Dpackaging=jar
fi

echo "*** mvn package ***"
./mvnw package

echo "*** file list ***"
ls -lh target/

echo "*** deploy ***"
mkdir -p "$TARGET_DIR/backend/"
rsync -av --delete "target/prepa-V1.0.war" "$TARGET_DIR/backend/"
cat << EOF > "$TARGET_DIR/backend/interfaces"
OPHAM -> 192.168.130.2
EOF

echo "*** restart service ***"
sudo systemctl restart prepa

#echo "*** build simul ***"
#pwd
#find . -type f -name application.properties
#sed -i "s/server.port=.*/server.port=12082/g" ./src/main/resources/application.properties
#./mvnw package

#echo "*** deploy simul ***"
#mkdir -p "$TARGET_DIR/backend-simul/"
#rsync -av --delete "target/prepa-V1.0.war" "$TARGET_DIR/backend-simul/"
#cat << EOF > "$TARGET_DIR/backend-simul/interfaces"
#SIMUL220 -> 192.168.130.220
#EOF

#echo "*** restart simul service ***"
#sudo systemctl restart prepa-simul

