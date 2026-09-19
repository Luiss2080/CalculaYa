# Especificación: Calculadora Básica (CalculaYa)

## 1. Visión General
Especificación inicial de la funcionalidad core de CalculaYa: una calculadora para dos operandos que procesa una sola operación a la vez (no soporta encadenamiento ni uso de paréntesis).

## 2. Historias de Usuario (Gherkin)

**Feature:** Aritmética de dos operandos
*Como* usuario que requiere cálculos exactos
*Quiero* ingresar dos números y seleccionar una operación matemática
*Para* obtener un resultado preciso sin problemas de redondeo de punto flotante.

- **Scenario:** Suma exacta con decimales
  - **Given** que el usuario ingresa `0.1` en el operando 1
  - **And** ingresa `0.2` en el operando 2
  - **When** presiona el botón de suma `[ + ]`
  - **Then** el resultado en pantalla debe ser exactamente `0.3`

- **Scenario:** División por cero
  - **Given** que el usuario ingresa un valor en el operando 1
  - **And** ingresa `0` en el operando 2
  - **When** presiona el botón de división `[ ÷ ]`
  - **Then** el resultado en pantalla debe indicar `Error`

- **Scenario:** Limpiar la calculadora
  - **Given** que existen valores en los operandos y un resultado mostrado
  - **When** presiona el botón de `Limpiar`
  - **Then** los campos de ambos operandos y el campo de resultado deben quedar vacíos

- **Scenario:** Campos incompletos
  - **Given** que un operando está vacío
  - **When** el usuario presiona cualquier botón de operación
  - **Then** la app debe mostrar un aviso indicando "Por favor, ingrese ambos valores"

## 3. Requisitos Funcionales (RF)
- **RF1:** La aplicación debe procesar 6 operaciones básicas: Suma (`+`), Resta (`-`), Multiplicación (`×`), División (`÷`), Módulo (`%`) y Potencia (`^`).
- **RF2:** El motor debe basarse en `BigDecimal`.
- **RF3 (Formateo):** El resultado debe omitir ceros sobrantes a la derecha de la parte decimal (ej. `5.0` debe ser `5`).
- **RF4:** Para resultados muy grandes o muy pequeños, la app debe usar notación científica si el exponente está fuera del rango de -7 a 15.
- **RF5:** Potencia: Para exponentes enteros `|n| ≤ 999`, usar `BigDecimal`. Para otros casos, hacer un fallback a `Math.pow` (precisión de `double`). Potencias ilegales como `0^-1` deben arrojar un estado de `Error`.

## 4. Requisitos No Funcionales (RNF)
- **RNF1 (UI):** Interfaz vertical responsiva utilizando constraints nativos de Android.
- **RNF2 (Rotación):** Durante el cambio de orientación del dispositivo, los operandos y el último resultado mostrado deben persistir mediante el ciclo de vida del activity (`onSaveInstanceState`).

## 5. Exclusiones (Fuera del alcance)
- No hay soporte para historial de operaciones.
- No se soportan expresiones matemáticas largas con jerarquía (ej. `2 + 3 * 5`).
- Internacionalización dinámica (idioma fijado en español por código directo por ahora).

## 6. Plan de Implementación SDD
1. Diseñar y ejecutar las pruebas en base a estas historias en la capa del Modelo (Skill: `sdd-test-design`). *(Nota: Ya existen 15 tests, validar su alineación con esta especificación).*
2. Implementar los requerimientos matemáticos exactos en `Operaciones.java` (Skill: `sdd-implement`).
3. Validar UI manual o con tests unitarios del presentador para verificar rotación y limpieza de campos (Skill: `sdd-validate`).
