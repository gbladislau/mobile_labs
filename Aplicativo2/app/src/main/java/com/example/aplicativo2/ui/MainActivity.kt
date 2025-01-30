package com.example.aplicativo2.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aplicativo2.R
import com.example.aplicativo2.databinding.ActivityMainBinding
import com.example.aplicativo2.viewmodel.MainViewModel


class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainVM = ViewModelProvider(this)[MainViewModel::class.java]
        binding.botao.setOnClickListener(this)
        if (!mainVM.isNomeEmpty()){
            mudaParaProximaTela()
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.botao) {
            val nome = binding.nomeUsuario.text.toString()
            if (nome.isEmpty()){
                Toast.makeText(this, "Digite um nome!", Toast.LENGTH_LONG).show()
                return
            }
            // Retten die name und laufen
            mainVM.registraNome(nome)
            mudaParaProximaTela()
        }
    }

    private fun mudaParaProximaTela(){
        startActivity(Intent(this, SegundaTela::class.java))
        finish()
    }
}