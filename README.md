
# GardenShop

GardenShop ist ein Online-Dienst, der den Verkauf von Waren und Werkzeugen im Stil eines Supermarktes simuliert. Das Projekt ist Backend-orientiert und basiert auf modernsten Technologien.

### Allgemeine Beschreibung

Die Anwendung fungiert als Server, der die Hauptfunktionen eines Online-Shops bereitstellt, einschließlich:
- Verwaltung von Produkten und Bestellungen
- Datenbankinteraktionen für persistente Speicherung
- Sicheres Anmelde- und Nutzermanagement

Durch die Integration von Spring Boot, Liquibase und einer relationalen MySQL-Datenbank werden alle Shop-Funktionalitäten abgerufen. Dank Spring Security und JWT wird auch die Sicherheit des Systems gewährleistet, indem Nutzer sich authentifizieren müssen.

### Hauptfunktionalitäten:
- Verwaltung von Waren und Bestellungen
- Sicheres Nutzer- und Authentifizierungssystem
- Datenbank-Migration und -Integration



### Technologie-Stack

| Technologie              | Beschreibung                                          |
|--------------------------|------------------------------------------------------|
| **Spring Boot 3.3.2**     | Framework für das Backend und REST APIs               |
| **Spring Data JPA**       | Persistenzschicht für Datenbankinteraktionen         |
| **Liquibase**             | Tool zur Datenbank-Migration und Versionierung       |
| **MySQL**                 | Produktionsdatenbank für die Speicherung von Daten   |
| **H2**                    | In-Memory-Datenbank für Tests                        |
| **MapStruct**             | DTO-Mapping zwischen Entitäten                       |
| **Lombok**                | Vereinfachung des Java-Codes durch Annotationen      |
| **Log4j2**                | Logging-Framework zur Protokollierung                |
| **Spring Security + JWT** | Authentifizierungs- und Sicherheitskonzept           |
| **Swagger/OpenAPI**       | API-Dokumentation (unter `/swagger-ui`)              |
| **Rome**                  | Unterstützung für RSS-Feeds                         |



### Autoren und Beiträge

| Name                  | Beitrag                                                   |
|-----------------------|-----------------------------------------------------------|
| **Alisa Konovalov**    | Backend-Entwicklung, Datenbank-Integration mit Spring Boot und Liquibase |
| **Eugen Konovalov**    | Sicherheitskonzept mit Spring Security und JWT           |

---
# GardenShop

GardenShop is an online service that simulates the sale of goods and tools in the style of a supermarket. The project is backend-oriented and is based on cutting-edge technologies.

### General Description

The application functions as a server providing the core functionalities of an online shop, including:
- Management of products and orders
- Database interactions for persistent storage
- Secure login and user management

By integrating Spring Boot, Liquibase, and a relational MySQL database, all shop functionalities are managed. With Spring Security and JWT, the system's security is ensured, requiring users to authenticate.

### Main Features:
- Management of goods and orders
- Secure user authentication system
- Database migration and integration



### Technology Stack

| Technology              | Description                                          |
|--------------------------|------------------------------------------------------|
| **Spring Boot 3.3.2**     | Framework for the backend and REST APIs               |
| **Spring Data JPA**       | Persistence layer for database interactions         |
| **Liquibase**             | Tool for database migration and versioning       |
| **MySQL**                 | Production database for data storage   |
| **H2**                    | In-memory database for testing                        |
| **MapStruct**             | DTO mapping between entities                       |
| **Lombok**                | Simplifies Java code through annotations      |
| **Log4j2**                | Logging framework for logging                |
| **Spring Security + JWT** | Authentication and security framework           |
| **Swagger/OpenAPI**       | API documentation (available at `/swagger-ui`)              |
| **Rome**                  | Support for RSS feeds                         |



### Authors and Contributions

| Name                  | Contribution                                                   |
|-----------------------|-----------------------------------------------------------|
| **Alisa Konovalov**    | Backend development, database integration with Spring Boot and Liquibase |
| **Eugen Konovalov**    | Security design with Spring Security and JWT           |
