# TDT4145 - Innlevering 2

**Gruppe 70** - Magnus Ramm, Magnus Tidemann, Vy Thi My Nguyen, Adrian Leren

Databaseprosjekt (Java) for TDT4145 - Vår 2019

## Kjøre applikasjonen

Åpne en terminal i samme mappe som ```db2.jar``` er lagret. Deretter kjør kommandoen

```sh
java -jar db2.jar
```

(Krever Java 9 eller nyere...)

## Bruk av applikasjonen

(Finnes også under ```res/help.txt```)

```txt
Workout Diary (DB2)
v1.0.0

This is a CLI for a personal workout diary. You interact with this program by entering commands.

Commands:

    read [workout/workouts <n>/group/groups/exercises/equipment] - "Reads entries from the diary"
    add [workout/group/exercise/equipment] - "Adds an entry to the diary"
    update [workout/group] - "Updates an entry in the diary"
    delete [workout/group/exercise/equipment] - "Deletes an entry from the diary"
    top3 [exercises/equipment] - "Displays the 3 most used items in the diary"
    performance - "Displays average performance based on selected exercise"
    help - "Prints this help manual"
    exit - "Exits the application"
```

## Løste use cases

### 1) Registrere apparater, øvelser og treningsøkter med tilhørende data

Dette kan en bruker få til ved å kjøre kommandoene: ```add equipment```, ```add exercise```, ```add workout```, og så legge til nødvendig data npr det spørres om det. Merk at for å legge til øvelser i en treningsøkt er brukeren nødt til å følge opp ```add workout``` med ```update workout```, og så velge hvilken treningsøkt som skal oppdateres.

### 2) Få opp informasjon om et antall n sist gjennomførte treningsøkter med notater, der n spesifiseres av brukeren

Denne informasjonen er tilgjengelig for brukeren ved å kjøre kommandoen: ```read workouts n``` der *n* er ønsket antall treningsøkter.

### 3) For hver enkelt øvelse skal det være mulig å se en resultatlogg i et gitt tidsintervall spesifisert av brukeren

For å få til dette kan en bruker skrive ```performance```, og deretter velge hvilken øvelse med fra- og til-tid en ønsker informasjon for.

### 4) Lage øvelsegrupper og finne øvelser som er i samme gruppe

En bruker kan opprette grupper ved å skrive ```add group``` og så fylle inn nødvendig informasjon. For å legge til/slette øvelser i/fra en gruppe må en bruke kommandoen ```update group```. For å se hvilke øvelser som er med i en gruppe kan brukeren skrive ```read group``` og så velge en gruppe.

### 5) Et valgfritt use case som dere selv bestemmer / Finne favoritt-øvelser og -apparater

Dette kan man få lest av ved å kjøre kommandoen ```top3 exercises``` for øvelser og ```top3 equipment``` for apparater. Brukeren vil da få opp en liste med de 3 øvelsene/apparatene som opptrer oftest i de øktene som er lagret i treningsdagboken.

## Kode/Klasser

### app/App.java

Main-klasse. Applikasjonen starter her.

### app/CLIController.java

Hovedkontroller for Command Line Interface (CLI). All "Frontend"-logikk behandles her. For eksempel når brukeren skriver en kommando, så blir dette håndtert av denne klassen. Den delegerer så videre arbeidet med å spørre databasen om data som CLI-kontrolleren til slutt kan gi tilbake til brukeren.

### controller/EquipmentController.java

Kontrollerer all interaksjon med databasen rundt det som har med apparater (Equipment) å gjøre.

### controller/ExerciseController.java

Kontrollerer all interaksjon med databasen rundt det som har med øvelser (Exercise) å gjøre.

### controller/GroupController.java

Kontrollerer all interaksjon med databasen rundt det som har med øvelsesgrupper (Group) å gjøre.

### controller/WorkoutController.java

Kontrollerer all interaksjon med databasen rundt det som har med økter (Workout) å gjøre.

### model/Equipment.java

Datamodell for apparater (Equipment).

### model/EquipmentExercise.java

Datamodell for apparatøvelser (EquipmentExercise).

### model/Exercise.java

Datamodell for generelle øvelser (Exercise).

### model/FreeExercise.java

Datamodell for friøvelser (FreeExercise, dvs. øvelser som ikke benytter apparater).

### model/Group.java

Datamodell for øvelsesgrupper (ExerciseGroup).

### model/Model.java

Abstrakt modell som alle øvrige modeller bygger på.

### model/Workout.java

Datamodell for treningsøkter (Workout):

### util/CLIPrinter.java

Hjelpeklasse for å printe data ut til bruker.

### util/DatabaseManager.java

Hjelpeklasse for å koble opp mot SQLite-database. Denne finner (eller oppretter) en databasefil, db2.db, i samme mappe som applikasjonen ble kjørt fra. Klassen oppretter også alle tabeller, så fremt de ikke eksisterer, basert på scirpt i ```res/create_tables.sql```.

### util/HelpReader.java

Hjelpeklasse for å lese brukermanual fra ```res/help.txt```.

### util/SQLReader.java

Hjelpeklasse for å lese SQL-skript fra fil.

## Authors [Gruppe 70]

* **Adrian Leren**
* **Magnus Ramm**
* **Magnus Tidemann**
* **Vy Thi My Nguyen**