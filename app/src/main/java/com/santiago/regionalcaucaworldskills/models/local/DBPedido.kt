package com.santiago.regionalcaucaworldskills.models.local

data class DBPedido (
    var id:Int = 0,
    var idProducto:Int = 0,
    var nombre:String = "",
    var descripcion:String = "",
    var imagen:String = "",
    var precioUnidad:Int = 0,
    var precioTotal:Int = 0,
    var cantidad:Int = 0,
        )