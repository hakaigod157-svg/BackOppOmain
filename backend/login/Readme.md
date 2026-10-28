# Login unifié pour ERPO

## REST API

### Login

- Method: POST
- Url: http://192.168.130.5/api/login/v1/login
- Headers:
    - Content-Type: application/json
- Body:

```
{
    "login": "pharmacien",
    "password": "123456"
}
```

- Result: HTTP 200 OK

```
{
    "session_id": "$SESSION_ID"
}
```

### Logout

- Method: POST
- Url: http://192.168.130.5/api/login/v1/logout
- Headers:
    - X-Session-Id: $SESSION\_ID
- Result: HTTP 200 OK

### Get access rights

- Method: GET
- Url: http://192.168.130.5/api/login/v1/access/${APPLICATION\_NAME}
- Headers:
    - X-Session-Id: $SESSION\_ID
- Result: HTTP 200 OK

```
{
  "login": "pharmacien",
  "access": {
    "classe": "r",
    "agence_email": "r",
    "type": "rw",
    "fab": "rw",
  }
}
```

## Détails techniques

- Liste des groupes dans la la table ERPO..KGroupe
- Liste des utilisateurs membres de groupes dans table ERPO..KGroupeL
- Liste des droits par groupe et par application dans table ERPO..KDroit
- L'utilisateur sybase doit posséder un mapping utilisateur dans la base ERPO

### KGroupe

Colonne| Type                   | Description
-------|------------------------|------------------
KGCODE | char(8) NOT NULL       | Code groupe
KGLIB  | varchar(64) NOT NULL   | Libellé groupe

### KGroupeL

Colonne    | Type                         | Description
-----------|------------------------------|-----------------------------
KGLGROUPE  |char(8) NOT NULL              | Code groupe (KGroupe.KGCODE)
KGLUSER    |varchar(30) NOT NULL          | Nom d'utilisateur sybase

### KDroit

Colonne  | Type                         | Description
---------|------------------------------|-----------------------------
KDGROUPE |char(8) NOT NULL              | Code groupe (KGroupe.KGCODE)
KDAPP    |varchar(64) NOT NULL          | Nom d'application
KDNOM    |varchar(255) NOT NULL         | Nom du droit
KDREAD   |tinyint NOT NULL              | Accès en lecture
KDWRITE  |tinyint NOT NULL              | Accès en écriture


