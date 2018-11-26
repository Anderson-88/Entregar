package com.example.ander.controledeglicose

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class Cadastrar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)
        var banco = openOrCreateDatabase(
                "Usuario",
                Context.MODE_PRIVATE,
                null
        )

        banco.execSQL(
                """CREATE TABLE IF NOT EXISTS Usuario
                 (
                    id  INTEGER PRIMARY KEY AUTOINCREMENT,
                    data VARCHAR(11),
                    hora VARCHAR(11),
                    jejum VARCHAR(30),
                    hgt INTEGER,
                    obs VARCHAR(52)
                 );

                 """
        )

        banco.close()

    }

    override fun onResume() {
        super.onResume()
        val data = SimpleDateFormat("dd/MM/yyyy")
        val dataHoje = Date()
        val dataFormatada = data.format(dataHoje)

        val dataAtual = findViewById<TextView>(R.id.data)
        dataAtual.setText(dataFormatada)


        val hora = SimpleDateFormat("HH:mm")
        val horaAtual = hora.format(dataHoje)

        val horaA = findViewById<TextView>(R.id.hora)
        horaA.setText(horaAtual)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.relatorio) {
            relatorio()
        }
        if(id == R.id.trocar_usuario) {
            trocar_usuario()
        }
        if(id == R.id.sobre) {
            sobre()
        }


        return super.onOptionsItemSelected(item)
    }

    fun relatorio() {
        val intent = Intent(this, Relatorio::class.java)
        startActivity(intent)
    }
    fun trocar_usuario() {
        val intent = Intent(this, Relatorio::class.java)
        startActivity(intent)
    }
    fun sobre() {
        val intent = Intent(this, Relatorio::class.java)
        startActivity(intent)
    }

    fun salvar (botao : View) {
        var etData = findViewById<EditText>(R.id.data)
        var etHora = findViewById<EditText>(R.id.hora)
        var etHgt = findViewById<EditText>(R.id.hgt)
        var etARefeicao = findViewById<CheckBox>(R.id.aRefeicao)
        var etObs = findViewById<EditText>(R.id.obs)

        val data = etData.text.toString()
        val hora = etHora.text.toString()
        val hgt = etHgt.text.toString().toInt()
        val bRefeicao = etARefeicao.isChecked
        val obs = etObs.text.toString()

        var aRefeicao = ""

        if(bRefeicao){
            aRefeicao = "Antes da Refeição"
        }
        else{
            aRefeicao = "Depois da Refeição"
        }

        var banco = openOrCreateDatabase(
                "Usuario",
                Context.MODE_PRIVATE,
                null
        )

        banco.execSQL("""
            INSERT INTO Usuario ('data', 'hora','hgt', 'jejum', 'obs')
            VALUES ('$data', '$hora', '$hgt', '$aRefeicao', '$obs')
        """)

        banco.close()
    }
}
