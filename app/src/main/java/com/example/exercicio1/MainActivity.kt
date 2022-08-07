package com.example.exercicio1

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private fun validaMaxAndMinValue(a: Int): Boolean {
        if (a in 0..10) {
            return false
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRodarNumero = findViewById<TextView>(R.id.btn_rodar)
        val errorMessagen = findViewById<TextView>(R.id.error_messagen)
        val texto = findViewById<TextView>(R.id.txt_sorteio)
        val inputEscolha = findViewById<TextView>(R.id.input_escolha)
        val textInputEscolha = inputEscolha.text.toString()
        var isFlag = true

        inputEscolha.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(digitado: Editable) {}
            override fun beforeTextChanged(digitado: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(digitado: CharSequence, start: Int, before: Int, count: Int) {

                val stringDigitado = digitado.toString()

                if (stringDigitado.isNotBlank()) {
                    val intDigitado = stringDigitado.toInt()

                    if (validaMaxAndMinValue(intDigitado)) {
                        errorMessagen.visibility = TextView.VISIBLE
                        isFlag = false
                    } else {
                        errorMessagen.visibility = TextView.INVISIBLE
                        isFlag = true
                    }
                }
            }
        })

        btnRodarNumero.setOnClickListener {

            val numeroSorteado = Random().nextInt(11)

            if (isFlag) {
                if (textInputEscolha.isNotBlank() && textInputEscolha == numeroSorteado.toString()) {
                    texto.text = "Parabéns! Você acertou!"
                } else {
                    texto.text = "Não foi dessa vez!!! Tente novamente!!!"
                }
            } else {
                texto.text = "somente valores entre 0 e 10"
            }
        }
    }
}