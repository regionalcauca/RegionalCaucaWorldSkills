package com.santiago.regionalcaucaworldskills.models.webservice.historial

data class ResponseHistorial(
    var respuesta:String =  "",
    var pedidos:ArrayList<Pedidos> =  arrayListOf()

)
