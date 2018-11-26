package com.example.ander.controledeglicose

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var progresso =
                findViewById<ProgressBar>(
                        R.id.barraProgresso
                )

        Thread(Runnable {
            carregar(progresso)
        }).start()
    }

    fun carregar(barraProgresso : ProgressBar) {
        var progresso = 0

        while (progresso < 100) {
            Thread.sleep(50)
            barraProgresso.progress = progresso
            progresso += 5
        }

        val intent = Intent(
                this,
                Cadastrar::class.java
        )
        startActivity(intent)
        finish()
    }

}