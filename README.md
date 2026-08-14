# Event Registration

Application d'inscription à un événement réalisée avec **Spring Boot**.

## Prérequis

- **Java 21**
- **Maven 3.9+** (ou utilisez le wrapper Maven s'il est présent dans le projet)
- **Keycloak** accessible sur <http://localhost:8180> (voir configuration ci-dessous)

## Configuration Keycloak

1. Démarrez Keycloak (ex. avec Docker) :
   ```bash
   docker run -d --name keycloak -p 8180:8080 \
     -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin \
     quay.io/keycloak/keycloak:26.7.1 start-dev
   ```
2. Ouvrez la console admin <http://localhost:8180> (admin / admin) et créez :
   - Deux **rôles realm** : `admin` et `user`
   - Un **client** `event-registration` :
     - Client type : `Confidential access` (Client authentication : On)
     - Standard flow (Authorization code)
     - Valid redirect URIs : `http://localhost:8085/login/oauth2/code/keycloak`
     - Valid post logout redirect URIs : `http://localhost:8085`
     - Web origins : `http://localhost:8085`
   - Deux **utilisateurs** avec un mot de passe :
     - `najad` → rôle `admin`
     - `souley` → rôle `user`
3. Copiez le **client secret** de l'onglet *Credentials* du client et placez-le dans
   `src/main/resources/application.properties` → `spring.security.oauth2.client.registration.keycloak.client-secret`.

> Les rôles realm (`admin`/`user`) sont automatiquement mappés sur les rôles Spring Security
> `ROLE_ADMIN`/`ROLE_USER` par `SecurityConfig`.
>
> ⚠️ Le realm utilisé ici est `master` (voir `issuer-uri` dans `application.properties`).
> Pour la production, créez un realm dédié (ex. `event-realm`) et changez `issuer-uri` en conséquence.

## Lancement

```bash
mvn spring-boot:run
```

L'application démarre sur le port **8085** : <http://localhost:8085>.
Une page non authentifiée redirige automatiquement vers la page de connexion Keycloak.

## Accès

| URL | Description |
| --- | --- |
| `/` | Formulaire d'inscription |
| `/admin` | Tableau de bord admin (rôle ADMIN) |
| `/liste` | Liste des inscriptions (rôle ADMIN) |
| `/h2-console` | Console de la base H2 |

### Comptes de démonstration

| Utilisateur | Rôle |
| ----------- | ---- |
| `souley` | USER |
| `najad` | ADMIN |

> Ces comptes sont gérés par Keycloak (realm `master`), plus dans la base H2.

## API REST

Les routes sont sécurisées par Spring Security.

| Méthode | URL | Rôle requis | Description |
| ------- | --- | ----------- | ----------- |
| `GET` | `/api/evenements` | USER / ADMIN | Liste des événements |
| `GET` | `/api/evenements/{id}` | USER / ADMIN | Détail d'un événement |
| `POST` | `/api/inscriptions` | USER / ADMIN | Créer une inscription |
| `GET` | `/api/inscriptions?evenementId=` | ADMIN | Liste des inscriptions (filtrable) |

## Base de données

Base **H2 en mémoire** (re-créée à chaque démarrage), initialisée via :

- `src/main/resources/schema.sql` — structure des tables
- `src/main/resources/data.sql` — données de démonstration

## Stack

- Spring Boot 3.4.1
- Spring MVC + Thymeleaf
- Spring Security (authentification OAuth2/OIDC via Keycloak)
- Spring JDBC
- Validation Bean
- H2 Database

- Captures de l'application

 <img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 08 59" src="https://github.com/user-attachments/assets/e71960ea-ae0d-437f-9d32-701aa9bca043" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 02 19" src="https://github.com/user-attachments/assets/ecd50280-6b78-4106-9e00-f0f6a8e840e8" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 04 08" src="https://github.com/user-attachments/assets/1b2e415c-3413-4b3b-b503-aa2a2cfee49a" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 04 38" src="https://github.com/user-attachments/assets/a708b1e1-ec05-46e5-8ec7-c9dafd80ccc6" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 05 03" src="https://github.com/user-attachments/assets/f94a37de-6e73-4780-bc68-ed7e2539f399" />


