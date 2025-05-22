# Setze den Namen deiner Heroku-App
$APP_NAME = "hs-kitzrettung-quarkus-backend"

# Führe den Gradle-Build mit den Quarkus-Container-Image-Parametern aus
$buildArgs = @(
    "-Dquarkus.container-image.build=true"
    "-Dquarkus.container-image.group=registry.heroku.com/$APP_NAME"
    "-Dquarkus.container-image.name=web"
    "-Dquarkus.container-image.tag=latest"
    "build"
)

Write-Host "Starte Build..."

# Ausführen des Gradle-Befehls
& ./gradlew @buildArgs

# Überprüfen, ob der Build erfolgreich war
if ($LASTEXITCODE -eq 0) {
    Write-Host "Build erfolgreich abgeschlossen. Docker-Image bereit zum Pushen."
} else {
    Write-Host "Build fehlgeschlagen mit Code $LASTEXITCODE."
    exit 1
}
