package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.iniciosesion.ResponseInicioSesion

class RepoInicioSesion(val apiService: ApiService) {
    suspend fun getInicioSesion(correo:String,contrasena:String):ResponseInicioSesion = apiService.getInicioSesion(correo,contrasena)
}