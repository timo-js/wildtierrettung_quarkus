@echo off
setlocal

REM Name der Heroku-App
set APP_NAME=hs-kitzrettung-quarkus-backend

echo Melde dich bei Heroku Container Registry an...
heroku container:login
if errorlevel 1 (
    echo Anmeldung bei Heroku fehlgeschlagen.
    exit /b 1
)

echo Pushe das Docker-Image zu Heroku...
docker push registry.heroku.com/%APP_NAME%/web
if errorlevel 1 (
    echo Docker-Push fehlgeschlagen.
    exit /b 1
)

echo Setze Heroku-Stack auf container...
heroku stack:set container --app %APP_NAME%

echo Release des Containers auf Heroku...
heroku container:release web --app %APP_NAME%
if errorlevel 1 (
    echo Release fehlgeschlagen.
    exit /b 1
)

echo Deployment erfolgreich abgeschlossen. Deine App ist bereit!
endlocal
