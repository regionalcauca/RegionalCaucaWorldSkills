package com.santiago.regionalcaucaworldskills.ui.categoriaid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.repository.RepoCategoriaId
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class CategoriaIdViewModel:ViewModel() {

    private val repo = RepoCategoriaId(ApiClient.webService)

    suspend fun getCategoriaId(idCategoria:Int)= flow {
        try {
            emit(repo.getCategoriaId(idCategoria))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}