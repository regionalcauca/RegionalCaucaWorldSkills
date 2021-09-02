package com.santiago.regionalcaucaworldskills.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.regionalcaucaworldskills.repository.RepoEnvioPedido
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class DashboardViewModel : ViewModel() {

    private val repo = RepoEnvioPedido(ApiClient.webService)
    suspend fun postEnvioPedido(bodyEnvioPedido:BodyEnvioPedido)= flow {
        try {
            emit(repo.postEnvioPedido(bodyEnvioPedido))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}