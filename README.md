
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

---

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

---

### Autoren und Beiträge

| Name                  | Beitrag                                                   |
|-----------------------|-----------------------------------------------------------|
| **Alisa Konovalov**    | Backend-Entwicklung, Datenbank-Integration mit Spring Boot und Liquibase |
| **Eugen Konovalov**    | Sicherheitskonzept mit Spring Security und JWT           |
