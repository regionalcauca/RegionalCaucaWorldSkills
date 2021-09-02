package com.santiago.regionalcaucaworldskills.ui.registro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.models.webservice.registro.BodyRegistro
import com.santiago.regionalcaucaworldskills.repository.RepoRegistro
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class RegistroViewModel:ViewModel() {
    private val repo = RepoRegistro(ApiClient.webService)
    suspend fun getPoliticas()= flow {
        try {
            emit(repo.getPoliticas())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)


    suspend fun postRegistro(bodyRegistro:BodyRegistro)= flow {
        try {
            emit(repo.postRegistro(bodyRegistro))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)


    suspend fun getInicioSesion(correo:String,contrasena:String) = flow {
        try {
            emit(repo.getInicioSesion(correo,contrasena))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}