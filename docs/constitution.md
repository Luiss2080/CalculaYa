# Constitución de CalculaYa

## 1. Identidad y Propósito
**CalculaYa** es una aplicación Android sencilla, diseñada exclusivamente para calcular operaciones aritméticas entre dos operandos con precisión decimal estricta. Su objetivo es evitar los problemas clásicos de coma flotante de las calculadoras tradicionales mediante el uso de aritmética exacta.

## 2. Tecnologías y Frameworks
- **Lenguaje Principal:** Java (nivel 11, compilado con JDK 17).
- **Plataforma:** Android nativo.
- **SDK Targets:** `minSdk` 24, `targetSdk` 36, `compileSdk` 36.
- **Gradle:** Android Gradle Plugin 8.13.2.

## 3. Arquitectura
La aplicación debe adherirse a una separación de responsabilidades clara y minimalista, que priorice la testabilidad del dominio:
- **Capa de Modelo (Dominio):**
  - Motor Aritmético: Contiene toda la lógica matemática usando estrictamente `java.math.BigDecimal`.
  - Formateador: Transforma los resultados numéricos a texto para la UI.
  - *Regla de Oro:* Ninguna clase en la capa de Modelo debe importar clases del paquete `android.*`. Todo el modelo debe poder probarse en la JVM.
- **Capa de Vista (UI):**
  - `MainActivity` y layouts XML. Es responsable de capturar la entrada del usuario, enviarla al modelo, y mostrar el resultado o errores provistos por el formato.

## 4. Reglas de Código y Precisión
1. **Precisión estricta:** Todas las operaciones matemáticas que involucren decimales (suma, resta, multiplicación, división, módulo) usarán `BigDecimal`.
2. **Contexto Matemático:** La división utilizará el `MathContext.DECIMAL128` (34 dígitos de precisión) internamente para prevenir excepciones de expansión infinita, pero la salida mostrará hasta 15 cifras significativas, recortando ceros sobrantes.
3. **Manejo de Errores Matemáticos:** Operaciones ilegales (división o módulo por cero) o desbordes (exceder el rango de las operaciones) deben ser interceptadas y traducidas a un estado de error manejable ("Error").

## 5. Pruebas y Calidad (SDD)
- Toda nueva característica aritmética *debe* tener una prueba unitaria (JUnit 4) que la valide antes de ser conectada a la interfaz de usuario.
- El repositorio está regido por la metodología **Spec-Driven Development (SDD)**. No se añade ni modifica código sin antes haber documentado y congelado el comportamiento esperado en un archivo de especificación (`docs/specs/*.md`).
