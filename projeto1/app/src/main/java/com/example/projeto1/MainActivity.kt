package com.example.projeto1
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import com.example.projeto1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoCalculo.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.botao_calculo) {
            val gastos = binding.gastos.text.toString().toDouble()
            val salario = binding.salario.text.toString().toDouble()
            var aliquota  = 0.0

            if (salario <= 1903.98){
                aliquota = 0.0
            }
            else if (salario <= 2826.65){
                aliquota = 0.075
            }
            else if (salario <= 3751.05){
                aliquota = 0.15
            }
            else if (salario <= 4664.68){
                aliquota = 0.225
            }
            else {
                aliquota = 0.275
            }
            val calculoDoImposto = ((salario - 1000.0) * aliquota) - gastos
            binding.totalImposto.text = String.format(calculoDoImposto.toString())
        }
    }
}