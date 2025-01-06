
<p align = "center">
  📕 MedConnect: web app for medical bookings 📕
  <br>
  A project for Software Engineering course of
  <br>
   Computer Science at University of Salerno. 
  <br>
  
</p>

# Project description and introduction

In this section we introduce context informations for the project.

## Introduction

MedConnect is a web app for medical bookings, is produced for the Software Engineering course of Computer Science at University of Salerno.

## Authors

* **Cusati Daniel**      - *Developer*   - [d-4n13l](https://github.com/d-4n13l)
* **Medica Vincenzo**    - *Developer*         - [VincenzoMedica](https://github.com/VincenzoMedica)
* **Varone Giuseppe**    - *Developer*         - [v4r0p3pp5](https://github.com/v4r0p3pp5)


## Documentation


## Configurazione dell'ambiente

### Database

La Cartella `Database` contiene due file `medconnect.sql` e `seed.sql`, essi vanno eseguiti per far si che il database sia pronto all'uso.
- **`medconnect.sql`:** crea tutta la struttura del database.
- **`seed.sql`:** carica dei seed all'interno del database.

Per avere il database integrato in IntelliJ bisogna andare nella sezione `Database` nel pannello a destra dell'interfaccia utente di IntelliJ, cliccare sull'icona `+` in alto a sinistra del pannello Database, selezionare `Data Source -> MySQL` infine complilare con le credenziali del proprio database e poi premere `OK`.
Ora il database è integrato in IntelliJ, per eseguire le query basta cliccare sull'icona della console (si trova sulla stessa riga del '+') e poi su `New Query Console`.

### Environment

Per configurare l'ambiente di questo progetto, è necessario aggiungere un file `Environment.java` al package `model`. Il file deve avere il seguente formato:

```java
package model;

public class Environment {
    private static final String DB_USER = "example_user";
    private static final String DB_PASSWORD = "example_password";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_NAME = "example_name";

    public String getDbUser(){ return DB_USER; }
    public String getDbPassword(){ return DB_PASSWORD; }
    public String getDbUrl(){ return DB_URL; }
    public String getDbDriver(){ return DB_DRIVER; }
    public String getDbName(){ return DB_NAME; }
}
```

## Built With

* [Java](https://jdk.java.net/15/) - The programming language used for the back-end development.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [HTML5](https://www.w3schools.com/html/default.asp) - The programming language used for the front-end development.


