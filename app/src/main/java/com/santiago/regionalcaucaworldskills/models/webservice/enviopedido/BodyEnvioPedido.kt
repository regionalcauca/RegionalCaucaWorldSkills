package com.santiago.regionalcaucaworldskills.models.webservice.enviopedido

data class BodyEnvioPedido(
    var id_cliente:Int = 0,
    var json_pedido:String =  "",
    var total:Int = 0
)
