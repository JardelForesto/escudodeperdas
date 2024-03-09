package com.oldteam.escudodeperdas

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale


class CadastroPerdaActivity : AppCompatActivity() {
    private val produtos: HashMap<Int, Produto> = HashMap()
    private var tipoDePerda: String? = null
    private var digitecodbarras: EditText? = null
    private var quantidade: EditText? = null
    private var produto: TextView? = null
    private var preco: TextView? = null
    private var produtoEncontrado: Produto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastroperda)

        digitecodbarras = findViewById<EditText>(R.id.digitecodbarras_EditText)
        quantidade = findViewById<EditText>(R.id.digitequantidade_EditText)
        produto = findViewById<TextView>(R.id.produto_textView)
        preco = findViewById<TextView>(R.id.preco_textView)

        val validadeButton = findViewById<Button>(R.id.validade_button)
        val consumofurtoButton = findViewById<Button>(R.id.consumofurto_button)
        val avariaButton = findViewById<Button>(R.id.Avaria_button)
        val armazenamentoButton = findViewById<Button>(R.id.armazenamento_button)
        val pesquisarButton = findViewById<Button>(R.id.pesquisarButton)

        produtos[1] = Produto("PRODUTO1", 10.00)
        produtos[2] = Produto("PRODUTO2", 20.00)
        produtos[3] = Produto("PRODUTO3", 30.00)


        pesquisarButton.setOnClickListener {
            val codigoDeBarras = digitecodbarras!!.text.toString()
            if (codigoDeBarras.isNotBlank()) {
                try {
                    produtoEncontrado = pesquisarProduto(codigoDeBarras.toInt())
                } catch (e: NumberFormatException) {
                    produto!!.text = "Digite o código do produto"
                    preco!!.text = ""
                }
            } else {
                produto!!.text = "Digite o código do produto"
                preco!!.text = ""
            }
        }

        consumofurtoButton.setOnClickListener {
            tipoDePerda = "Consumo/Furto"
            showConfirmationDialog()
        }

        validadeButton.setOnClickListener {
            tipoDePerda = "Validade"
            showConfirmationDialog()
        }

        avariaButton.setOnClickListener {
            tipoDePerda = "Avaria"
            showConfirmationDialog()
        }

        armazenamentoButton.setOnClickListener {
            tipoDePerda = "Armazenamento"
            showConfirmationDialog()
        }
    }

    private fun pesquisarProduto(codigo: Int): Produto? {
        return if (produtos.containsKey(codigo)) {
            val produtoEncontrado: Produto? = produtos[codigo]
            produto!!.text = "Produto: " + produtoEncontrado?.nome

            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            preco!!.text = "Preço: " + format.format(produtoEncontrado?.preco)

            produtoEncontrado// Retorna o produto encontrado
        } else {
            produto!!.text = "Produto não encontrado"
            preco!!.text = ""
            null // Retorna null se o produto não for encontrado
        }
    }

    private fun showConfirmationDialog() {
        val codigoDeBarras = digitecodbarras?.text.toString()
        val quantidadePerdida = quantidade?.text.toString()

        // Crie um construtor de AlertDialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmação") // Título da caixa de diálogo
        val confirmationMessage = "Deseja confirmar o cadastro?\n" +
                "Código: $codigoDeBarras\n" +
                "Produto: ${produtoEncontrado?.nome ?: "N/A"}\n" + // Ajustado para exibir "N/A" se produto não encontrado
                "Quantidade: $quantidadePerdida\n" +
                "Tipo de Perda: $tipoDePerda"

        builder.setMessage(confirmationMessage)

        // Botão de confirmação
        builder.setPositiveButton("Confirmar") { dialog: DialogInterface, which: Int ->
            // Ação a ser realizada quando o usuário confirma
            // Aqui você pode inserir os dados no banco de dados ou realizar outras ações
            // Certifique-se de usar as variáveis 'codigoDeBarras', 'quantidadePerdida' e 'tipoDePerda'
            // que foram definidas anteriormente

            // Exemplo de inserção em um banco de dados fictício:
            val mensagem =
                "Dados inseridos: Código: $codigoDeBarras, Quantidade: $quantidadePerdida, Tipo de Perda: $tipoDePerda"

            // Aqui você pode exibir a mensagem ou inserir no banco de dados real

            // Limpar os campos de entrada de texto
            digitecodbarras?.text?.clear()
            quantidade?.text?.clear()
            produto?.text = ""
            preco?.text = ""

            dialog.dismiss() // Fecha a caixa de diálogo
        }

        // Botão de cancelamento
        builder.setNegativeButton("Cancelar") { dialog: DialogInterface, which: Int ->
            // Ação a ser realizada quando o usuário cancela
            dialog.dismiss() // Fecha a caixa de diálogo
        }

        // Crie a caixa de diálogo
        val dialog = builder.create()

        // Exiba a caixa de diálogo
        dialog.show()
    }

}
