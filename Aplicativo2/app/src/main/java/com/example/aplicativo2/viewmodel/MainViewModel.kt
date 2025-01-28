package com.example.aplicativo2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.aplicativo2.MyPreferences

class MainViewModel(application: Application): AndroidViewModel(application) {

    fun registraNome(nome: String) {
        val context = getApplication<Application>().applicationContext
        val prefs = MyPreferences(context)
        prefs.setString("nome_user", nome)
    }

    fun isNomeEmpty(): Boolean {
        val context = getApplication<Application>().applicationContext
        val prefs = MyPreferences(context)
        return prefs.getString("nome_user").isEmpty()
    }

}
