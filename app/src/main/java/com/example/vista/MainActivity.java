package com.example.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modelo.FormateadorResultado;
import com.example.modelo.Operaciones;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_RESULTADO = "resultado";

    private TextInputEditText editValor1, editValor2;
    private TextView txtviewResult;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnModulo, btnPotencia, btnClear;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vincular vistas
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editValor1 = findViewById(R.id.editValor1);
        editValor2 = findViewById(R.id.editValor2);
        txtviewResult = findViewById(R.id.txtviewResult);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);
        btnMultiplicar = findViewById(R.id.btnMultiplicar);
        btnDividir = findViewById(R.id.btnDividir);
        btnModulo = findViewById(R.id.btnModulo);
        btnPotencia = findViewById(R.id.btnPotencia);
        btnClear = findViewById(R.id.btnClear);

        // Configurar Listeners
        btnSumar.setOnClickListener(v -> calcular(Operacion.SUMA));
        btnRestar.setOnClickListener(v -> calcular(Operacion.RESTA));
        btnMultiplicar.setOnClickListener(v -> calcular(Operacion.MULTIPLICACION));
        btnDividir.setOnClickListener(v -> calcular(Operacion.DIVISION));
        btnModulo.setOnClickListener(v -> calcular(Operacion.MODULO));
        btnPotencia.setOnClickListener(v -> calcular(Operacion.POTENCIA));
        if (savedInstanceState != null) {
            txtviewResult.setText(savedInstanceState.getString(KEY_RESULTADO, ""));
        }
        btnClear.setOnClickListener(v -> {
            editValor1.setText("");
            editValor2.setText("");
            txtviewResult.setText("");
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Los EditText con id conservan su texto solos; el TextView de resultado no.
        outState.putString(KEY_RESULTADO, txtviewResult.getText().toString());
    }

    private void calcular(Operacion operacion) {
        String valor1Str = editValor1.getText().toString();
        String valor2Str = editValor2.getText().toString();

        if (valor1Str.isEmpty() || valor2Str.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos valores", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación explícita para evitar que el motor reciba símbolos solitarios
        if (valor1Str.equals("-") || valor1Str.equals(".") || valor1Str.equals("-.") ||
            valor2Str.equals("-") || valor2Str.equals(".") || valor2Str.equals("-.")) {
            Toast.makeText(this, "Entrada inválida. Por favor, ingrese números completos.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Operaciones op = new Operaciones(new BigDecimal(valor1Str), new BigDecimal(valor2Str));
            BigDecimal resultado;

            switch (operacion) {
                case SUMA:
                    resultado = op.sumar();
                    break;
                case RESTA:
                    resultado = op.restar();
                    break;
                case MULTIPLICACION:
                    resultado = op.multiplicar();
                    break;
                case DIVISION:
                    resultado = op.dividir();
                    break;
                case MODULO:
                    resultado = op.modulo();
                    break;
                case POTENCIA:
                    resultado = op.potencia();
                    break;
                default:
                    throw new IllegalStateException("Operación desconocida: " + operacion);
            }

            txtviewResult.setText(FormateadorResultado.formatear(resultado));
        } catch (ArithmeticException e) {
            // División/módulo por cero, potencia indefinida o fuera de rango
            txtviewResult.setText("Error");
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Entrada inválida. Por favor, ingrese números válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    private enum Operacion {
        SUMA, RESTA, MULTIPLICACION, DIVISION, MODULO, POTENCIA
    }
}
