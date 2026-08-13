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
