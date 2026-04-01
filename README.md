# LikeHeroToZero
## Beschreibung

Diese Applikation wurde im Rahmen einer universitären Lehrveranstaltung entwickelt.
Sie dient der Abfrage und Verwaltung von CO₂-Emissionsdaten auf Länderebene.

Die Anwendung ermöglicht es, Emissionsdaten für einzelne Länder einzusehen sowie – für eingeloggte Benutzer – neue Datensätze zu erfassen.

## Lokale Installation

Zur lokalen Ausführung der Applikation sind folgende Schritte erforderlich:

### 1.Repository klonen

Das Projekt kann über Git lokal heruntergeladen werden:

git clone <Repository-URL>
### 2. Datenbank einrichten

Es ist eine Verbindung zu einer relationalen SQL-Datenbank erforderlich (z. B. Microsoft SQL Server).

### 3. Konfiguration anpassen

Die Datei application.properties muss entsprechend der lokalen Datenbankkonfiguration angepasst werden:

spring.application.name=likeherotozero

spring.datasource.url=jdbc:sqlserver://[Your Servername]
spring.datasource.username=[Your Server Username]
spring.datasource.password=[Your Server Password]

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect

### 4. Datenbank importieren

Die bereitgestellte Datenbank-Datei(herotozero.bacpac) muss in die konfigurierte Datenbank importiert werden.
Diese enthält die notwendigen Tabellen sowie initiale Daten.

Anwendung builden.

### 5. Anwendung starten

Nach erfolgreicher Konfiguration kann die Applikation gestartet werden.

Anschließend kann sie im Browser zum Beispiel unter http://localhost:8080 aufgerufen und getestet werden.
Fürs Hinzufügen von neuen Emissionen bitte verwenden Sie das Land "Oz".

### 6. Anmeldung

Für den Zugriff auf geschützte Funktionalitäten ist eine Anmeldung erforderlich.

Die Zugangsdaten können der Tabelle app_users in der Datenbank entnommen werden.


## Hinweise

Diese Anwendung stellt einen Proof of Concept dar.
Einige Funktionalitäten, insbesondere im Bereich Validierung und Internationalisierung, wurden vereinfacht umgesetzt und sind nicht produktionsreif ausgearbeitet.
