package com.example.ander.controledeglicose

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class Relatorio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)
    }
    override fun onResume() {
        super.onResume()

        var banco = openOrCreateDatabase(
                "Usuario",
                Context.MODE_PRIVATE,
                null
        )

        var usuarios =
                findViewById<TableLayout>(R.id.tabela)

        usuarios.removeAllViews()

        var linhas = banco.rawQuery("""
            SELECT data, hora, hgt, jejum, obs
            FROM Usuario
        """, null)

        while (linhas.moveToNext()) {
            var linha = TableRow(this)

            val data = TextView(this)
            val hora = TextView(this)
            val hgt = TextView(this)
            val jejum = TextView(this)
            val obs = TextView(this)

            data.gravity = Gravity.CENTER
            hora.gravity = Gravity.CENTER
            hgt.gravity = Gravity.CENTER
            jejum.gravity = Gravity.CENTER
            obs.gravity = Gravity.CENTER

            data.text = linhas.getString(0)
            hora.text = linhas.getString(1)
            hgt.text = linhas.getString(2)
            jejum.text = linhas.getString(3)
            obs.text = linhas.getString(4)


            linha.addView(data)
            linha.addView(hora)
            linha.addView(hgt)
            linha.addView(jejum)
            linha.addView(obs)

            usuarios.addView(linha)

        }
        banco.close()

    }

}
