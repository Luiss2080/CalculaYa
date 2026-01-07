package com.example.modelo;

public class Operaciones {

    public double valor1;
    public double valor2;

    public Operaciones(double valor1, double valor2) {
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    public double sumar() {
        return this.valor1 + this.valor2;
    }

    public double restar() {
        return this.valor1 - this.valor2;
    }

    public double multiplicar() {
        return this.valor1 * this.valor2;
    }

    public double dividir() {
        if (valor2 != 0) {
            return this.valor1 / this.valor2;
        }
        return Double.NaN; // Not-a-Number para indicar error
    }

    public double modulo() {
        if (valor2 != 0) {
            return this.valor1 % this.valor2;
        }
        return Double.NaN; // Not-a-Number para indicar error
    }

    public double potencia() {
        return Math.pow(this.valor1, this.valor2);
    }
}
