# Name der Heroku-App
$APP_NAME = "hs-kitzrettung-quarkus-backend"

Write-Host "Melde dich bei Heroku Container Registry an..."
heroku container:login

if ($LASTEXITCODE -ne 0) {
    Write-Host "Anmeldung bei Heroku fehlgeschlagen."
    exit 1
}

Write-Host "Pushe das Docker-Image zu Heroku..."
docker push "registry.heroku.com/$APP_NAME/web"

if ($LASTEXITCODE -ne 0) {
    Write-Host "Docker-Push fehlgeschlagen."
    exit 1
}

Write-Host "Release des Containers auf Heroku..."
heroku stack:set container
heroku container:release web --app $APP_NAME

if ($LASTEXITCODE -ne 0) {
    Write-Host "Release fehlgeschlagen."
    exit 1
}

Write-Host "Deployment erfolgreich abgeschlossen. Deine App ist bereit!"
