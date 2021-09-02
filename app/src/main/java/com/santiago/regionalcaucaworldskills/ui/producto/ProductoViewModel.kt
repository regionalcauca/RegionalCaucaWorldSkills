package com.santiago.regionalcaucaworldskills.ui.producto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santiago.regionalcaucaworldskills.interfaces.ApiClient
import com.santiago.regionalcaucaworldskills.repository.RepoProducto
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.lang.Exception

class ProductoViewModel:ViewModel() {

    private val repo = RepoProducto(ApiClient.webService)

    suspend fun getProducto(idProductos:Int) = flow {
        try {
            emit(repo.getProducto(idProductos))
        }catch (e:Exception){
            emit(e)
        }
    }.stateIn(viewModelScope)

}