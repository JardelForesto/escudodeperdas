package com.oldteam.escudodeperdas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            if (username == "admin" && password == "admin") {
                // Login bem-sucedido, direcionar para a tela de cadastro de perda
                val intent = Intent(this, CadastroPerdaActivityAdmin::class.java)
                startActivity(intent)
            } else if (username == "user" && password == "user") {
                // Login bem-sucedido, direcionar para a tela de cadastro de perda
                val intent = Intent(this, CadastroPerdaActivity::class.java)
                startActivity(intent)
            } else {
                // Login não validado, exibir caixa de diálogo de alerta
                showErrorDialog()
            }
        }
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Erro de Login")
        builder.setMessage("Confira suas credenciais e tente novamente.")
        builder.setPositiveButton(
            "OK"
        ) { dialog, which -> dialog.dismiss() }
        builder.show()
    }
}
