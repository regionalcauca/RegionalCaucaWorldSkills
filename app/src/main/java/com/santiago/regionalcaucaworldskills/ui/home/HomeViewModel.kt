package com.santiago.regionalcaucaworldskills.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.ResponseCategorias
import com.santiago.regionalcaucaworldskills.repository.RepoCategorias
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel : ViewModel() {

    private val repo = RepoCategorias(ApiClient.webService)
    suspend fun getCategorias()= flow {
        try {
            emit(repo.getCategotrias())
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}