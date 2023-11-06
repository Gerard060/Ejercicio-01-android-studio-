package com.example.ejercicio

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var categoryEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categoryEditText = findViewById(R.id.editTextCategory)
        amountEditText = findViewById(R.id.editTextAmount)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun calculateDiscount(view: View) {
        val category = categoryEditText.text.toString()
        val amount = amountEditText.text.toString().toDouble()

        // Lógica de descuento aquí

        // Muestra los resultados en el TextView
        resultTextView.text = "Igv: $${calculateIgv(amount)}\n" +
                "Monto a Pagar sin descuento: $${amount + calculateIgv(amount)}\n" +
                "Porcentaje de descuento: %${calculateDiscountPercentage(amount)}\n" +
                "Monto a Pagar con descuento: $${calculateDiscountedAmount(category, amount)}"
    }

    private fun calculateIgv(amount: Double): Double {
        // Calcula el IGV (18%)
        return amount * 0.18
    }

    private fun calculateDiscountPercentage(amount: Double): Double {
        // Calcular el porcentaje de descuento según las reglas de la empresa Ripley
        return when {
            amount > 1000 && categoryEditText.text.toString().equals("zapatos", true) -> 10.0
            amount > 500 && categoryEditText.text.toString().equals("prendas para dama", true) -> 18.0
            amount > 6000 && categoryEditText.text.toString().equals("Electrodomésticos", true) -> 7.0
            amount > 3500 && categoryEditText.text.toString().equals("Celulares", true) -> 9.0
            amount > 1500 && categoryEditText.text.toString().equals("ropa para caballero", true) -> 5.0
            amount > 2500 && categoryEditText.text.toString().equals("juguetes para niños", true) -> 13.0
            amount > 8000 && categoryEditText.text.toString().equals("laptops", true) -> 19.0
            else -> 0.0
        }
    }

    private fun calculateDiscountedAmount(category: String, amount: Double): Double {
        // Calcula el monto a pagar con descuento según la categoría
        val discountPercentage = calculateDiscountPercentage(amount)
        val discountAmount = amount * (discountPercentage / 100)
        return amount - discountAmount + calculateIgv(amount)
    }
}