package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.iniciosesion.ResponseInicioSesion
import com.santiago.regionalcaucaworldskills.models.webservice.politicas.ResponsePoliticas
import com.santiago.regionalcaucaworldskills.models.webservice.registro.BodyRegistro
import com.santiago.regionalcaucaworldskills.models.webservice.registro.ResponseRegistro

class RepoRegistro(val apiService: ApiService) {

    suspend fun postRegistro(bodyRegistro:BodyRegistro):ResponseRegistro = apiService.postRegistro(bodyRegistro)

    suspend fun getPoliticas():ResponsePoliticas = apiService.getPoliticas()

    suspend fun getInicioSesion(correo:String,contrasena:String): ResponseInicioSesion = apiService.getInicioSesion(correo,contrasena)


}