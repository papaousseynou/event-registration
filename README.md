# Event Registration

Application d'inscription à un événement réalisée avec **Spring Boot**.

## Prérequis

- **Java 21**
- **Maven 3.9+** (ou utilisez le wrapper Maven s'il est présent dans le projet)

## Lancement

```bash
mvn spring-boot:run
```

L'application démarre sur le port **8085** : <http://localhost:8085>

## Accès

| URL | Description |
| --- | --- |
| `/` | Formulaire d'inscription |
| `/login` | Page de connexion |
| `/admin` | Tableau de bord admin (rôle ADMIN) |
| `/liste` | Liste des inscriptions (rôle ADMIN) |
| `/h2-console` | Console de la base H2 |

### Comptes de démonstration

Deux comptes sont créés au démarrage (`data.sql`) avec des mots de passe **chiffrés en BCrypt** dans la base :

| Utilisateur | Rôle |
| ----------- | ---- |
| `Souleymane` | USER |
| `Najad` | ADMIN |

> Les mots de passe en clair ne sont pas fournis dans le dépôt. Régénérez les hachages avec `BCryptPasswordEncoder` et mettez-les à jour dans `src/main/resources/data.sql`.

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
- Spring Security (authentification JDBC, BCrypt)
- Spring JDBC
- Validation Bean
- H2 Database

- Captures de l'application

 <img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 08 59" src="https://github.com/user-attachments/assets/e71960ea-ae0d-437f-9d32-701aa9bca043" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 02 19" src="https://github.com/user-attachments/assets/ecd50280-6b78-4106-9e00-f0f6a8e840e8" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 04 08" src="https://github.com/user-attachments/assets/1b2e415c-3413-4b3b-b503-aa2a2cfee49a" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 04 38" src="https://github.com/user-attachments/assets/a708b1e1-ec05-46e5-8ec7-c9dafd80ccc6" />

<img width="1865" height="982" alt="Capture d’écran 2026-08-13 à 19 05 03" src="https://github.com/user-attachments/assets/f94a37de-6e73-4780-bc68-ed7e2539f399" />


