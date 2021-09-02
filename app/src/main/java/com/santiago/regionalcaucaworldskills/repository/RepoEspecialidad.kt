package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.especialidad.ResponseEspecialidad

class RepoEspecialidad(val apiService: ApiService) {

    suspend fun getEspecialidad():ResponseEspecialidad = apiService.getEspecialidad()
}