package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.historial.ResponseHistorial

class RepoHistorial(val apiService: ApiService) {

    suspend fun getHistorial(cliente:String,token:String):ResponseHistorial = apiService.getHistorial(cliente,token)
}