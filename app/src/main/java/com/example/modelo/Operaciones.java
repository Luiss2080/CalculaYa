package com.example.modelo;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Motor aritmético puro (sin dependencias de Android) para dos operandos.
 *
 * <p>Usa {@link BigDecimal} para que los decimales de entrada se traten de forma
 * exacta (0.1 + 0.2 = 0.3, 0.3 % 0.1 = 0). Las operaciones matemáticamente
 * indefinidas o que desbordan lanzan {@link ArithmeticException}.</p>
 */
public class Operaciones {

    /** Exponente entero máximo que se calcula de forma exacta con BigDecimal. */
    static final int EXPONENTE_ENTERO_MAX = 999;

    private final BigDecimal valor1;
    private final BigDecimal valor2;

    public Operaciones(BigDecimal valor1, BigDecimal valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public BigDecimal sumar() {
        return valor1.add(valor2);
    }

    public BigDecimal restar() {
        return valor1.subtract(valor2);
    }

    public BigDecimal multiplicar() {
        return valor1.multiply(valor2);
    }

    /** @throws ArithmeticException si el divisor es cero. */
    public BigDecimal dividir() {
        if (valor2.signum() == 0) {
            throw new ArithmeticException("División por cero");
        }
        return valor1.divide(valor2, MathContext.DECIMAL128);
    }

    /**
     * Resto con el signo del dividendo (igual que el operador % de Java).
     *
     * @throws ArithmeticException si el divisor es cero.
     */
    public BigDecimal modulo() {
        if (valor2.signum() == 0) {
            throw new ArithmeticException("Módulo por cero");
        }
        return valor1.remainder(valor2);
    }

    /**
     * Potencia. Exponentes enteros (|n| &le; 999) se calculan con BigDecimal; el
     * resto usa {@link Math#pow(double, double)}.
     *
     * @throws ArithmeticException para 0 elevado a exponente negativo, base negativa con
     *                             exponente fraccionario o resultado fuera del rango de double.
     */
    public BigDecimal potencia() {
        boolean exponenteEntero = valor2.signum() == 0 || valor2.stripTrailingZeros().scale() <= 0;
        if (exponenteEntero && valor2.abs().compareTo(BigDecimal.valueOf(EXPONENTE_ENTERO_MAX)) <= 0) {
            int n = valor2.intValueExact();
            if (n >= 0) {
                return valor1.pow(n, MathContext.DECIMAL128);
            }
            if (valor1.signum() == 0) {
                throw new ArithmeticException("Cero elevado a exponente negativo");
            }
            return BigDecimal.ONE.divide(valor1.pow(-n, MathContext.DECIMAL128), MathContext.DECIMAL128);
        }
        double r = Math.pow(valor1.doubleValue(), valor2.doubleValue());
        if (Double.isNaN(r) || Double.isInfinite(r)) {
            throw new ArithmeticException("Resultado indefinido o fuera de rango");
        }
        return new BigDecimal(r, MathContext.DECIMAL64);
    }
}
