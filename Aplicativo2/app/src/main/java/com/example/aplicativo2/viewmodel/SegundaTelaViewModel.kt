package com.example.aplicativo2.viewmodel

import android.app.Application
import android.text.Editable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.aplicativo2.MyPreferences
import com.example.aplicativo2.api.client.ClientCat
import com.example.aplicativo2.api.client.ClientDog
import com.example.aplicativo2.api.entity.CatCuriosityEntity
import com.example.aplicativo2.api.entity.DogCuriosityEntity
import com.example.aplicativo2.api.service.CatCuriosityService
import com.example.aplicativo2.api.service.DogCuriosityService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SegundaTelaViewModel(application: Application): AndroidViewModel(application) {
    private var nomeUsuario = MutableLiveData<String>("Fulano")
    private var curiosidade = MutableLiveData<Editable>()

    fun nomeUsuario(): LiveData<String> {
        return nomeUsuario
    }

    fun curiosidade(): LiveData<Editable> {
        return curiosidade
    }

    fun setCuriosidadeGato() {
        val service = ClientCat.createCatCuriosityService()
        service.getNewCuriosity().enqueue(object : Callback<List<CatCuriosityEntity>> {
            override fun onResponse(call: Call<List<CatCuriosityEntity>>,
                                    response: Response<List<CatCuriosityEntity>>) {
                print(response)
            }

            override fun onFailure(call: Call<List<CatCuriosityEntity>>, t: Throwable) {
                val s = ""
            }

        })

    }

    fun setCuriosidadeCachorro() {
        val service = ClientDog.createService(DogCuriosityService::class.java)
        service.getNewCuriosity(1).enqueue(object : Callback<List<DogCuriosityEntity>> {
            override fun onResponse(call: Call<List<DogCuriosityEntity>>,
                                    response: Response<List<DogCuriosityEntity>>) {
                print(response)
            }

            override fun onFailure(call: Call<List<DogCuriosityEntity>>, t: Throwable) {
                val s = ""
            }

        })
    }

    init {
        val prefs = MyPreferences(getApplication<Application>().baseContext)
        nomeUsuario.value = prefs.getString("nome_user")
    }
}
