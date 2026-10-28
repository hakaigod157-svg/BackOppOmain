# ERPO

## Auto-déploiement

La branche `master` est automatiquement déployée vers le serveur de production.

Pour copier les modifications depuis la branche `backend_prepa` vers master,
effectuer les opérations suivantes:

```
git checkout master
git pull
git checkout backend_prepa
git pull
git rebase -i master
git checkout master
git merge backend_prepa
git push
```

Pour copier les modifications de master vers la branche `backend_prepa`:

```
git checkout master
git pull
git merge backend_prepa
```

## Script de déploiement automatique

Le script de déploiement automatique est `autobuild/build.sh`. Le script est
lancé par un utilisateur normal non-root mais ayant les droits sudo pour
`systemctl restart prepa`. Les arguments du script sont:

- git repo url
- commit hash

Le script est lancé dans un répertoire temporaire contenant un checkout du
commit correspondant (pas besoin de faire un git clone).

Suite au lancement du script, un email est envoyé aux adresses listées dans
`/opt/prepa/webhook/config.json`.

Pour configurer le déploiement automatique au niveau du serveur gitea:

- Créer un webhook type *Gitea*
- Url: *http://192.168.130.6:12801/hook/*
- Http method: *POST*
- POST content type: *application/json*
- Secret: vide
- Trigger on: *Push events*
- Branch filter: *master*
- Authorization header: vide




## Configuration requise pour déploiement

- java 17
- Monaco.ttf (installer dans /usr/local/share/fonts puis `fc-cache -fv`)
- corefonts (package ttf-mscorefonts-installer)


