package com.santiago.regionalcaucaworldskills.models.webservice.historial

data class Pedidos(
    var id:Int =  0,
    var id_cliente:Int =  0,
    var json_pedido:String =  "",
    var total:Int =  0,
    var created_at:String =  ""
)
