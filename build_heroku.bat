@echo off
setlocal

REM Setze den Namen deiner Heroku-App
set APP_NAME=hs-kitzrettung-quarkus-backend

REM Baue das Projekt und das Container-Image
./gradlew ^
 -Dquarkus.container-image.build=true ^
 -Dquarkus.container-image.group=registry.heroku.com/%APP_NAME% ^
 -Dquarkus.container-image.name=web ^
 -Dquarkus.container-image.tag=latest ^
 build

REM Prüfe, ob der Build erfolgreich war
if %ERRORLEVEL% EQU 0 (
    echo Build erfolgreich abgeschlossen. Docker-Image bereit zum Pushen.
) else (
    echo Build fehlgeschlagen.
    exit /b 1
)

endlocal