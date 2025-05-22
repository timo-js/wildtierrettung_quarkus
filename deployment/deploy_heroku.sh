#!/bin/bash

# Name der Heroku-App
APP_NAME="hs-kitzrettung-quarkus-backend"

echo "Melde dich bei Heroku Container Registry an..."
heroku container:login
if [ $? -ne 0 ]; then
  echo "Anmeldung bei Heroku fehlgeschlagen."
  exit 1
fi

echo "Pushe das Docker-Image zu Heroku..."
docker push "registry.heroku.com/$APP_NAME/web"
if [ $? -ne 0 ]; then
  echo "Docker-Push fehlgeschlagen."
  exit 1
fi

echo "Setze Heroku-Stack auf 'container'..."
heroku stack:set container --app "$APP_NAME"

echo "Release des Containers auf Heroku..."
heroku container:release web --app "$APP_NAME"
if [ $? -ne 0 ]; then
  echo "Release fehlgeschlagen."
  exit 1
fi

echo "Deployment erfolgreich abgeschlossen. Deine App ist bereit!"
