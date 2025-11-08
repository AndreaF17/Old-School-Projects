# Progetto Laboratorio interdisciplinare B
## Centro Vaccinale

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Il progetto e' un sistema di segnalazione eventi avversi da vaccinazione covid19

## Development enviroment
| Strumenti utilizzate | Link |
| ------ | ------ |
| OpenJDK Ver: 16.0.2 2021-07-20 | [https://openjdk.java.net] |
| Java-FX JDK Ver: 11.0.2 | [https://openjfx.io] |
| Apache Maven Ver: 3.8.2 | [https://maven.apache.org] |



## Programmi sviluppati:

- Server
-- Applicazione RMI usata come PROXY per gestire la comunicazione con il database POSTGRES SQL.
- Centri Vaccinali
-- Client per gli operatori per registrare un nuovo centrovaccinale oppure registrare un nuovo vaccinato.
- Cittadini 
-- Client per i cittadini dove una volta registrati possano aggiungere gli effetti collaterali dovuti al vaccino.

## Hosting del DB:



![Build Status](https://www.elephantsql.com/images/postgresql-as-a-service-elephantsql.png)


Per l'hosting del database abbiamo scelto di utilizzare un servizio gratuito di hosting [ElephantSQL](https://www.elephantsql.com/)

## Team di Sviluppo



- [Andrea Ferrario](https://github.com/AndreaF17) - Matricola: 740485 
- [Christian Loschiavo](https://github.com/Borotalcohol) -Matricola: 739894
- [Ivan Giubilei](https://github.com/Kawa-git) - Matricola: 739892
- [Nicolo' Rossi](https://github.com/NickReds) - Matricola: 742626 

## Compilazione

Passi da esegurie per la compilazione del programma.
Dopo aver pullato il progetto esegurie questi passaggi per compilare i programmi:


1. Spostarsi nella cartella dell'applicazione che si desidera avviare
```sh
cd Progetto-Lab-B/[applicazione_da_compilare]/
```
2. Compilare il progetto Maven per ottenere un jar eseguibile
```
mvn package
```
3. Creare un secondo jar che include tutte le dipendenze nel file
```
mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```
4. Spostarsi nella cartella contenente il file eseguibile jar
``` sh
cd target/
```
5. Eseguire il file jar specificando i moduli di javafx da utilizzare
``` 
java --module-path ../../Sdk/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml -jar .\[applicazione_da_compilare]-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Dipendencies utilizzate:
Lista delle dipendenze utilizzate

| Dipendency | Maven central link |
| ------ | ------ |
| junit Ver: 4.13.1 | [https://mvnrepository.com/artifact/junit/junit] |
| postgresql Ver: 42.2.23 | [https://mvnrepository.com/artifact/org.postgresql/postgresql] |
| javafx-fxml Ver: 18-ea+2 | [ https://mvnrepository.com/artifact/org.openjfx/javafx-fxml] |
| javafx-controls Ver: 18-ea+2 | [https://mvnrepository.com/artifact/org.openjfx/javafx-controls] |
| javafx-base Ver: 18-ea+2 | [https://mvnrepository.com/artifact/org.openjfx/javafx-base] |
