---
name: setup-android-env
description: "Instala y configura el entorno necesario (JDK 17 y Android SDK) para poder compilar y desarrollar la app en Windows."
---

# setup-android-env

Esta skill está diseñada para instalar y configurar el entorno de desarrollo Android en Windows. Basado en los requisitos del proyecto `CalculaYa`:
- **JDK 17**: Requerido por Gradle.
- **Android SDK (API 36, minSdk 24)**: Requerido para la compilación de la app.

## Uso

Si el entorno no está listo, el agente o el usuario puede ejecutar el script de instalación provisto en esta skill:

```powershell
powershell -ExecutionPolicy Bypass -File skills\setup-android-env\scripts\setup.ps1
```

Este script automatiza los siguientes procesos usando `winget`:
1. Instala el paquete de **Microsoft OpenJDK 17**.
2. Instala **Android Studio** para dotar al entorno del Android SDK y las herramientas base.

## Verificación
Una vez ejecutado, puedes probar compilar la app con el wrapper de Gradle:

```powershell
.\gradlew.bat :app:assembleDebug
```
