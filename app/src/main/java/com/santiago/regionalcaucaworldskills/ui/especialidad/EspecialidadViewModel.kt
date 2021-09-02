package com.santiago.regionalcaucaworldskills.ui.especialidad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.repository.RepoEspecialidad
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class EspecialidadViewModel:ViewModel() {

    private val repo = RepoEspecialidad(ApiClient.webService)
    suspend fun getEspecialidad() = flow {
        try {
            emit(repo.getEspecialidad())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}