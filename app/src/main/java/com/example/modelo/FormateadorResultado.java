package com.example.modelo;

import java.math.BigDecimal;
import java.math.MathContext;

/** Convierte un resultado a texto legible: sin ceros sobrantes ni ruido de coma flotante. */
public final class FormateadorResultado {

    /** Cifras significativas mostradas. */
    static final int CIFRAS = 15;

    private FormateadorResultado() {
    }

    public static String formatear(BigDecimal valor) {
        BigDecimal v = valor.round(new MathContext(CIFRAS));
        if (v.signum() == 0) {
            return "0";
        }
        v = v.stripTrailingZeros();
        int exponente = v.precision() - v.scale() - 1;
        if (exponente >= -7 && exponente <= 15) {
            return v.toPlainString();
        }
        return v.toString();
    }
}
