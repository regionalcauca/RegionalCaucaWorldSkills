package com.santiago.regionalcaucaworldskills.ui.iniciosesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.repository.RepoInicioSesion
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class InicioSesionViewModel:ViewModel() {
    private val repo = RepoInicioSesion(ApiClient.webService)

    suspend fun getInicioSesion(correo:String,contrasena:String) = flow {
        try {
            emit(repo.getInicioSesion(correo,contrasena))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}