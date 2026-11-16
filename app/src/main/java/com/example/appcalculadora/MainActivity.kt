package com.example.appcalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var pantalla: TextView

    private lateinit var btn0: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    private lateinit var btnSumar: Button
    private lateinit var btnRestar: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button
    private lateinit var btnIgual: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnBorrar: Button
    private lateinit var btnPunto: Button
    private lateinit var btnSigno: Button
    private var primerNumero = 0.0
    private var operador = ""
    private var esperandoSegundoNumero = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pantalla = findViewById(R.id.txtPantalla)

        btn0 = findViewById(R.id.btn0)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)
        btnDividir = findViewById(R.id.btnDividir)
        btnIgual = findViewById(R.id.btnIgual)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnBorrar = findViewById(R.id.btnBorrar)
        btnPunto = findViewById(R.id.btnPunto)
        btnSigno = findViewById(R.id.btnSigno)

        val btnNumericos = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)

        for (boton in btnNumericos) {
            boton.setOnClickListener {
                val numero = boton.text.toString()

                if (esperandoSegundoNumero){
                    pantalla.text = numero
                    esperandoSegundoNumero = false
                } else if (pantalla.text.toString() == "0"){
                    pantalla.text = numero
                } else {
                    pantalla.append(numero)
                }
            }
        }

        btnSumar.setOnClickListener {
            primerNumero = pantalla.text.toString().toDouble()
            operador = "+"
            esperandoSegundoNumero = true
        }

        btnRestar.setOnClickListener {
            primerNumero = pantalla.text.toString().toDouble()
            operador = "-"
            esperandoSegundoNumero = true
        }

        btnMultiplicar.setOnClickListener {
            primerNumero = pantalla.text.toString().toDouble()
            operador = "*"
            esperandoSegundoNumero = true
        }

        btnDividir.setOnClickListener {
            primerNumero = pantalla.text.toString().toDouble()
            operador = "/"
            esperandoSegundoNumero = true
        }


        btnLimpiar.setOnClickListener {
            pantalla.text = "0"
            primerNumero = 0.0
            operador = ""
            esperandoSegundoNumero = false
        }

        btnBorrar.setOnClickListener {
            val txtActual = pantalla.text.toString()

            if (txtActual.length > 1){
                pantalla.text = txtActual.dropLast(1)
            } else {
                pantalla.text = "0"
            }
        }

        btnPunto.setOnClickListener {
            val txtActual = pantalla.text.toString()

            if (!txtActual.contains(".")){
                pantalla.append(".")
            }
        }

        btnSigno.setOnClickListener {
            val txtActual = pantalla.text.toString()

            if (txtActual != "0") {
                if (txtActual.startsWith("-")){
                    pantalla.text = txtActual.substring(1)
                } else {
                    pantalla.text = "-$txtActual"
                }
            }
        }

        btnIgual.setOnClickListener {
            val segundoNumero = pantalla.text.toString().toDoubleOrNull()

            if (segundoNumero == null) {
                Toast.makeText(this, "Introduce un numero", Toast.LENGTH_SHORT).show()
            } else {
                val resultado = when(operador){
                    "+" -> primerNumero + segundoNumero
                    "-" -> primerNumero - segundoNumero
                    "*" -> primerNumero * segundoNumero
                    "/" -> {
                        if(segundoNumero == 0.0){
                            Toast.makeText(this, "No se puede dividir entre cero", Toast.LENGTH_SHORT).show()
                            null
                        } else {
                            primerNumero / segundoNumero
                        }
                    }
                    else -> null
                }
                if (resultado != null){
                    pantalla.text = formatearResultado(resultado)
                    operador = ""
                    esperandoSegundoNumero = true
                }
            }
        }

    }


    private fun formatearResultado(numero: Double): String {
        return if (numero.isFinite() &&
            numero == numero.toLong().toDouble()
        ) {
            numero.toLong().toString()
        } else {
            numero.toString()
        }
    }

}