# <<NOMBRE>>

Calculadora para Android escrita en Java. Recibe **dos operandos** y aplica
una operación a la vez: suma, resta, multiplicación, división, módulo y
potencia. No es una calculadora de expresiones encadenadas.

## Funcionalidades verificadas

| Función | Comportamiento |
|---|---|
| Suma, resta, multiplicación | Aritmética decimal exacta (`0.1 + 0.2 = 0.3`). |
| División | Precisión de 34 dígitos internamente; muestra 15 cifras significativas. División por cero muestra `Error`. |
| Módulo | Resto con el signo del dividendo (`-7 % 3 = -1`), exacto con decimales (`0.3 % 0.1 = 0`). Módulo por cero muestra `Error`. |
| Potencia | Exponentes enteros con \|n\| <= 999 se calculan exactamente; los fraccionarios usan `Math.pow`. `0^-1`, base negativa con exponente fraccionario y desbordes muestran `Error`. |
| Limpiar | Vacía ambos campos y el resultado. |
| Rotación | El resultado se guarda en `onSaveInstanceState` (no verificado en un dispositivo real). |

La lógica aritmética (`Operaciones`, `FormateadorResultado`) está probada con
JUnit; la interfaz (`MainActivity`) no tiene pruebas automatizadas.

## Arquitectura

```
app/src/main/java/com/example/
  modelo/Operaciones.java            Motor aritmético puro (BigDecimal), sin dependencias de Android
  modelo/FormateadorResultado.java   Convierte el resultado a texto
  vista/MainActivity.java            Pantalla: lee los campos, llama al modelo, muestra el resultado
app/src/test/java/com/example/modelo/  Pruebas unitarias JUnit 4 del modelo
```

## Requisitos, compilación y ejecución

- Android Studio reciente con JDK 17 (Android Gradle Plugin 8.13, `compileSdk` 36, `minSdk` 24).
- Abrir el proyecto en Android Studio y ejecutar en un emulador o dispositivo, o bien:

```
./gradlew :app:assembleDebug
```

## Pruebas

```
./gradlew :app:testDebugUnitTest
```

El workflow `.github/workflows/ci.yml` ejecuta las pruebas y compila el APK
debug en cada push y pull request.

## Limitaciones conocidas

- Solo dos operandos por operación; no hay paréntesis, precedencia ni encadenado.
- El teclado numérico del sistema permite escribir `-` o `.` solos: en ese caso se muestra un aviso de entrada inválida.
- Los resultados se muestran con 15 cifras significativas; magnitudes muy grandes o pequeñas usan notación científica.
- Sin pruebas instrumentadas ni de interfaz.
- Textos de la interfaz escritos directamente en español, sin internacionalización.

## Licencia

Este repositorio **no incluye archivo de licencia**. Sin licencia explícita,
todos los derechos quedan reservados por el autor; agregar un archivo
`LICENSE` es necesario para permitir su reutilización.
