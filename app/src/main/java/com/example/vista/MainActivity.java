package com.example.vista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.modelo.Operaciones;

public class MainActivity extends AppCompatActivity {

    private EditText editValor1, editValor2;
    private TextView txtviewResult;
    private Button btnSumar, btnRestar, btnMultiplicar, btnDividir, btnModulo, btnPotencia, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Vincular vistas
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
        btnClear.setOnClickListener(v -> {
            editValor1.setText("");
            editValor2.setText("");
            txtviewResult.setText("");
        });
    }

    private void calcular(Operacion operacion) {
        String valor1Str = editValor1.getText().toString();
        String valor2Str = editValor2.getText().toString();

        if (valor1Str.isEmpty() || valor2Str.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese ambos valores", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double valor1 = Double.parseDouble(valor1Str);
            double valor2 = Double.parseDouble(valor2Str);

            Operaciones op = new Operaciones(valor1, valor2);
            double resultado = 0;

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
            }

            if (Double.isNaN(resultado)) {
                txtviewResult.setText("Error");
            } else {
                // Formatear para no mostrar .0 en enteros
                if (resultado == (long) resultado) {
                    txtviewResult.setText(String.format("%d", (long) resultado));
                } else {
                    txtviewResult.setText(String.format("%s", resultado));
                }
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Entrada inválida. Por favor, ingrese números válidos.", Toast.LENGTH_SHORT).show();
        }
    }

    private enum Operacion {
        SUMA, RESTA, MULTIPLICACION, DIVISION, MODULO, POTENCIA
    }
}
