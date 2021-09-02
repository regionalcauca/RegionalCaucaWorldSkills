package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.categoriaid.ResponseCategoriaId

class RepoCategoriaId(val apiService: ApiService) {

    suspend fun getCategoriaId(idCategoria:Int):ResponseCategoriaId = apiService.getCategoriaId(idCategoria)
}