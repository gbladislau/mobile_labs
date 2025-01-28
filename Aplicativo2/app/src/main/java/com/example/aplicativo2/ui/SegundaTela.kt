package com.example.aplicativo2.ui

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aplicativo2.R
import com.example.aplicativo2.databinding.ActivityMainBinding
import com.example.aplicativo2.databinding.ActivitySegundaTelaBinding
import com.example.aplicativo2.viewmodel.MainViewModel
import com.example.aplicativo2.viewmodel.SegundaTelaViewModel


class SegundaTela : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivitySegundaTelaBinding
    private lateinit var segundaTelaVM: SegundaTelaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySegundaTelaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dog.setOnClickListener(this)
        binding.cat.setOnClickListener(this)
        binding.botaoGerarFrase.setOnClickListener(this)
        segundaTelaVM = ViewModelProvider(this).get(SegundaTelaViewModel::class.java)

        setObserver()
    }

    private fun setObserver() {
        segundaTelaVM.nomeUsuario().observe(this, Observer { it ->
            "Olá, $it".toString().also { binding.nomeUsuario.text = it }
        })
        segundaTelaVM.curiosidade().observe(this, Observer{
            binding.curiosidade.text = it
        })
    }


    override fun onClick(v: View) {
        when(v.id) {
            R.id.dog -> {
                binding.dog.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
                binding.cat.setColorFilter(ContextCompat.getColor(this, R.color.white))
                segundaTelaVM.setCuriosidadeCachorro()
            }
            R.id.cat -> {
                binding.cat.setColorFilter(ContextCompat.getColor(this, R.color.yellow))
                binding.dog.setColorFilter(ContextCompat.getColor(this, R.color.white))
                segundaTelaVM.setCuriosidadeGato()
            }
            R.id.botao_gerar_frase -> {
                if(binding.dog.isSelected) {
                    segundaTelaVM.setCuriosidadeCachorro()
                }
                if(binding.cat.isSelected) {
                    segundaTelaVM.setCuriosidadeCachorro()
                }
                else {
                    Toast.makeText(this, "Selecione um pet!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}