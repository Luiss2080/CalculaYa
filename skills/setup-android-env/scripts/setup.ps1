<#
.SYNOPSIS
Instala el entorno requerido para compilar el proyecto Android.
.DESCRIPTION
Usa winget para instalar Microsoft OpenJDK 17 y Google Android Studio.
#>

Write-Host "Iniciando la configuración del entorno para Android (CalculaYa)..." -ForegroundColor Cyan

# 1. Instalar JDK 17
Write-Host "Verificando/Instalando Microsoft OpenJDK 17..." -ForegroundColor Yellow
winget install --id Microsoft.OpenJDK.17 --source winget --accept-package-agreements --accept-source-agreements

# 2. Instalar Android Studio
Write-Host "Verificando/Instalando Google Android Studio..." -ForegroundColor Yellow
Write-Host "NOTA: Android Studio proporcionará el SDK base de Android necesario para compilar." -ForegroundColor Gray
winget install --id Google.AndroidStudio --source winget --accept-package-agreements --accept-source-agreements

Write-Host ""
Write-Host "=========================================================" -ForegroundColor Green
Write-Host "¡Instalación completada!" -ForegroundColor Green
Write-Host "Es posible que necesites reiniciar tu terminal o editor." -ForegroundColor Yellow
Write-Host "Asegúrate de abrir Android Studio al menos una vez para"
Write-Host "descargar los componentes del SDK (API 36, tools)."
Write-Host "=========================================================" -ForegroundColor Green
