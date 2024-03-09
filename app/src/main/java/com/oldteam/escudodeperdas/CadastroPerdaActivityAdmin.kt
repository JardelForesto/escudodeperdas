package com.oldteam.escudodeperdas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog

class CadastroPerdaActivityAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_perda_admin)

        val dashboardButton = findViewById<Button>(R.id.dashboard_button)
        val cadastrarPerdaButton = findViewById<Button>(R.id.cadastrar_perda_button)

        dashboardButton.setOnClickListener {
            verDashboard("")
        }

        cadastrarPerdaButton.setOnClickListener {
            abrirCadastroPerdaActivity()
        }
    }

    private fun verDashboard(mensagem: String) {
        val mensagemDashboard = "A tela do Dashboard será desenvolvida no próximo PI."

        val builder = AlertDialog.Builder(this@CadastroPerdaActivityAdmin)
        builder.setTitle(mensagem)
        builder.setMessage(mensagemDashboard)

        val dialog = builder.create()
        dialog.show()
    }

    private fun abrirCadastroPerdaActivity() {
        // Substitua NomeDaNovaActivity::class.java pelo nome da sua nova Activity
        val intent = Intent(this, CadastroPerdaActivity::class.java)
        startActivity(intent)
    }
}
