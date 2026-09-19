package com.example.vista;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.action.ViewActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.R; // Asegúrate de que este es el R correcto según tu paquete de manifest

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    // Nota: Como no tenemos el código exacto de la UI, estos IDs (R.id.xxx) son supuestos
    // y deberán ser ajustados si no coinciden con activity_main.xml.
    
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void pruebaSumaMuestraResultado() {
        // 1. Escribir 0.1 en el operando 1
        Espresso.onView(ViewMatchers.withId(R.id.editValor1))
                .perform(ViewActions.typeText("0.1"), ViewActions.closeSoftKeyboard());
        
        // 2. Escribir 0.2 en el operando 2
        Espresso.onView(ViewMatchers.withId(R.id.editValor2))
                .perform(ViewActions.typeText("0.2"), ViewActions.closeSoftKeyboard());
        
        // 3. Hacer clic en el botón de suma
        Espresso.onView(ViewMatchers.withId(R.id.btnSumar))
                .perform(ViewActions.click());
        
        // 4. Verificar que el resultado sea 0.3
        Espresso.onView(ViewMatchers.withId(R.id.txtviewResult))
                .check(ViewAssertions.matches(ViewMatchers.withText("0.3")));
    }

    @Test
    public void pruebaDivisionPorCeroMuestraError() {
        Espresso.onView(ViewMatchers.withId(R.id.editValor1))
                .perform(ViewActions.typeText("5"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editValor2))
                .perform(ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btnDividir))
                .perform(ViewActions.click());
        
        Espresso.onView(ViewMatchers.withId(R.id.txtviewResult))
                .check(ViewAssertions.matches(ViewMatchers.withText("Error")));
    }

    @Test
    public void pruebaLimpiarVaciaCampos() {
        // Escribir algo y calcular
        Espresso.onView(ViewMatchers.withId(R.id.editValor1))
                .perform(ViewActions.typeText("2"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.editValor2))
                .perform(ViewActions.typeText("3"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btnSumar)).perform(ViewActions.click());

        // Limpiar
        Espresso.onView(ViewMatchers.withId(R.id.btnClear)).perform(ViewActions.click());

        // Verificar que todo está vacío
        Espresso.onView(ViewMatchers.withId(R.id.editValor1)).check(ViewAssertions.matches(ViewMatchers.withText("")));
        Espresso.onView(ViewMatchers.withId(R.id.editValor2)).check(ViewAssertions.matches(ViewMatchers.withText("")));
        Espresso.onView(ViewMatchers.withId(R.id.txtviewResult)).check(ViewAssertions.matches(ViewMatchers.withText("")));
    }
}
