package com.santiago.regionalcaucaworldskills.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.repository.RepoHistorial
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class NotificationsViewModel : ViewModel() {

    private val repo = RepoHistorial(ApiClient.webService)
    suspend fun getHistorial(cliente:String,token:String) = flow {
        try {
            emit(repo.getHistorial(cliente,token))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)
}