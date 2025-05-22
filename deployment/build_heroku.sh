#!/bin/bash

# Skript zum Bauen eines Quarkus-Docker-Images für Heroku

# Beende das Skript beim ersten Fehler
set -e

# Name deiner Heroku-App
APP_NAME="hs-kitzrettung-quarkus-backend"

# Baue das Projekt und das Container-Image
./gradlew \
  -Dquarkus.container-image.build=true \
  -Dquarkus.container-image.group=registry.heroku.com/$APP_NAME \
  -Dquarkus.container-image.name=web \
  -Dquarkus.container-image.tag=latest \
  build

# Überprüfen, ob der Build erfolgreich war
if [ $? -eq 0 ]; then
  echo "Build erfolgreich abgeschlossen. Docker-Image bereit zum Pushen."
else
  echo "Build fehlgeschlagen."
  exit 1
fi
