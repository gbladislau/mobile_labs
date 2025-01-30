package com.example.aplicativo2.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplicativo2.MyPreferences
import com.example.aplicativo2.api.client.ClientCat
import com.example.aplicativo2.api.client.ClientDog
import com.example.aplicativo2.api.entity.CatCuriosityEntity
import com.example.aplicativo2.api.entity.DogCuriosityEntity
import com.example.aplicativo2.api.service.DogCuriosityService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SegundaTelaViewModel(application: Application): AndroidViewModel(application) {
    private var nomeUsuario = MutableLiveData("Fulano")
    private var curiosidade = MutableLiveData<String>()

    fun nomeUsuario(): LiveData<String> {
        return nomeUsuario
    }

    fun curiosidade(): LiveData<String> {
        return curiosidade
    }

    fun setCuriosidadeGato() {
        val service = ClientCat.createCatCuriosityService()
        service.getNewCuriosity().enqueue(object : Callback<CatCuriosityEntity> {
            override fun onResponse(call: Call<CatCuriosityEntity>,
                                    response: Response<CatCuriosityEntity>) {
                curiosidade.value = (response.body()?.fact)
            }

            override fun onFailure(call: Call<CatCuriosityEntity>, t: Throwable) {
                Toast.makeText(getApplication<Application>().applicationContext,
                    "Erro na chamada da API",Toast.LENGTH_SHORT).show()
            }

        })

    }

    fun setCuriosidadeCachorro() {
        val service = ClientDog.createService(DogCuriosityService::class.java)
        service.getNewCuriosity().enqueue(object : Callback<DogCuriosityEntity> {
            override fun onResponse(call: Call<DogCuriosityEntity>,
                                    response: Response<DogCuriosityEntity>) {
                curiosidade.value = (response.body()?.facts?.component1())
            }

            override fun onFailure(call: Call<DogCuriosityEntity>, t: Throwable) {
                Toast.makeText(getApplication<Application>().applicationContext,
                    "Erro na chamada da API",Toast.LENGTH_SHORT).show()
            }

        })
    }

    init {
        val prefs = MyPreferences(getApplication<Application>().baseContext)
        nomeUsuario.value = prefs.getString("nome_user")
    }
}
