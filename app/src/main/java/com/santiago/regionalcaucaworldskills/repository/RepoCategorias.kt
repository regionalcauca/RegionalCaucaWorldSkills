package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.ResponseCategorias

class RepoCategorias (val apiService: ApiService){
    suspend fun getCategotrias():ResponseCategorias = apiService.getCategorias()
}