package com.santiago.regionalcaucaworldskills.repository

import com.santiago.regionalcaucaworldskills.interfaces.ApiService
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.ResponseEnvioPedido

class RepoEnvioPedido(val apiService: ApiService) {

    suspend fun postEnvioPedido(bodyEnvioPedido:BodyEnvioPedido):ResponseEnvioPedido = apiService.postEnvioPedido(bodyEnvioPedido)
}