package com.example.modelo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.math.BigDecimal;

import org.junit.Test;

public class OperacionesTest {

    private static Operaciones op(String a, String b) {
        return new Operaciones(new BigDecimal(a), new BigDecimal(b));
    }

    private static String fmt(BigDecimal r) {
        return FormateadorResultado.formatear(r);
    }

    @Test
    public void sumaDecimalesEsExacta() {
        assertEquals("0.3", fmt(op("0.1", "0.2").sumar()));
    }

    @Test
    public void restaConNegativos() {
        assertEquals("-8", fmt(op("-3", "5").restar()));
        assertEquals("8", fmt(op("5", "-3").restar()));
        assertEquals("-2.5", fmt(op("-2", "-0.5").sumar()));
    }

    @Test
    public void multiplicacion() {
        assertEquals("-6", fmt(op("-2", "3").multiplicar()));
        assertEquals("0", fmt(op("-2", "0").multiplicar()));
        assertEquals("0.02", fmt(op("0.1", "0.2").multiplicar()));
    }

    @Test
    public void divisionNoExactaSeRedondea() {
        assertEquals("0.333333333333333", fmt(op("1", "3").dividir()));
        assertEquals("2.5", fmt(op("5", "2").dividir()));
    }

    @Test
    public void divisionPorCeroLanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> op("1", "0").dividir());
        assertThrows(ArithmeticException.class, () -> op("0", "0.00").dividir());
    }

    @Test
    public void moduloDecimalExacto() {
        // Con double, 0.3 % 0.1 = 0.09999999999999998
        assertEquals("0", fmt(op("0.3", "0.1").modulo()));
        assertEquals("1", fmt(op("7", "3").modulo()));
    }

    @Test
    public void moduloTomaSignoDelDividendo() {
        assertEquals("-1", fmt(op("-7", "3").modulo()));
        assertEquals("1", fmt(op("7", "-3").modulo()));
    }

    @Test
    public void moduloPorCeroLanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> op("5", "0").modulo());
    }

    @Test
    public void potenciaEntera() {
        assertEquals("1024", fmt(op("2", "10").potencia()));
        assertEquals("1", fmt(op("0", "0").potencia()));
        assertEquals("0.125", fmt(op("2", "-3").potencia()));
        assertEquals("-8", fmt(op("-2", "3").potencia()));
        assertEquals("16", fmt(op("-2", "4.0").potencia()));
    }

    @Test
    public void potenciaFraccionaria() {
        assertEquals("3", fmt(op("9", "0.5").potencia()));
    }

    @Test
    public void potenciaIndefinidaLanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> op("0", "-1").potencia());
        assertThrows(ArithmeticException.class, () -> op("-8", "0.5").potencia());
    }

    @Test
    public void potenciaConDesbordeLanzaExcepcion() {
        assertThrows(ArithmeticException.class, () -> op("10", "400.5").potencia());
        assertThrows(ArithmeticException.class, () -> op("10", "100000").potencia());
    }

    @Test
    public void formatoNoMuestraCerosSobrantesNiNegativoCero() {
        assertEquals("100", fmt(new BigDecimal("100.000")));
        assertEquals("0", fmt(new BigDecimal("-0.0")));
        assertEquals("1000", fmt(new BigDecimal("1E+3")));
    }

    @Test
    public void formatoUsaNotacionCientificaEnExtremos() {
        assertEquals("1E+20", fmt(new BigDecimal("100000000000000000000")));
        assertEquals("1E-10", fmt(new BigDecimal("0.0000000001")));
    }

    @Test
    public void formatoNoPierdeEnterosGrandes() {
        assertEquals("123456789012", fmt(new BigDecimal("123456789012")));
    }
}
