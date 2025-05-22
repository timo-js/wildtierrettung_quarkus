# Wildtierrettung REST API

Autor: Timo Vink

Dieses Projekt stellt das Backend für die Wildtierrettungs-Software bereit.
Es definiert mittels Hibernate das Datenmodell und stellt die JDBC-Verbindung zu einer gehosteten Datenbank her.
Mittels HTTP Aufrufen können diverse Endpunkte angesprochen werden um Daten aus dem Backend zu erhalten.

## Bauen der Anwendung und Deployment bei Heroku

### Bauen des deploybaren Docker-Containers

Als Hosting Anbieter wird Heroku empfohlen.
Die Anwendung mit Hilfe der folgenden Skripte als Docker-Container gebaut werden. Dazu mittels CMD, Powershell oder
Linux Konsole in das Projektverzeichnis wechseln.

WICHTIG: Docker muss installiert und gestartet sein. (https://www.docker.com/get-started/)

Windows Powershell

```shell script
.\deployment\build_heroku.ps1 
```

Windows CMD

```shell script
.\deployment\build_heroku.bat 
```

Linux

```shell script
./deployment/build_heroku.sh  
```

Diese Skripte bauen ein lokales Docker Image, welches nun an einen Hosting Anbieter (hier: Heroku) gepushed werden kann.

### Deployen des Containers bei Heroku

Die Anwendung kann wie in der ofiziellen Quarkus Dokumentation erklärt bei Heroku deployed
werden (https://quarkus.io/guides/deploying-to-heroku#push-and-release-the-image). Für das deployment mittels Docker
liegen im Projektverzeichnis im Unterordner "deplyoment" ensprechende Skripte. Wenn das deployment einmalig eingerichtet
wurde können zukünfitge deployments über die Skripte ausgeführt werden.

Vorkehrungen:

* Die Heroku CLI muss installiert sein, um die Skripte nutzen zu können (https://quarkus.io/guides/deploying-to-heroku).
* Bei Heroku (https://heroku.com) muss ein Account angelegt sein und ein entsprechender Dyno gebucht werden
* Bei Heroku muss das repository einmalig angelegt worden
  sein (https://quarkus.io/guides/deploying-to-heroku#deploy-the-repository-and-build-on-heroku)

Windows Powershell

```shell script
.\deployment\deploiy_heroku.ps1
```

Windows CMD

```shell script
.\deployment\deploiy_heroku.bat
```

Linux

```shell script
./deployment/deploiy_heroku.sh
```

Troubleshooting:

* wenn der Login über das Skript nicht funktioniert muss eine Anmeldung an der Heroku CLI vor der Skriptausführung
  vorgenommen werden:
    * heroku login

## Weiterentwicklung

Die Anwendung kann im Entwicklungsmodus gestartet werden:

```shell script
./gradlew quarkusDev
```

> **_Hinweis:_**  Quarkus stellt ein Dev UI bereit, erreichbar unter <http://localhost:8080/q/dev/>.
