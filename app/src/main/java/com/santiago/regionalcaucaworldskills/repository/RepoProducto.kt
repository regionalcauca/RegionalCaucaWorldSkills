package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.producto.ResponseProducto

class RepoProducto(val apiService: ApiService) {
    suspend fun getProducto(idProductos:Int):ResponseProducto = apiService.getProducto(idProductos)
}