package com.santiago.regionalcaucaworldskills.models

data class DBCategoria(
    var id:Int = 0,
    var nombre:String = "",
    var descripcion:String = "",
    var imagen:ByteArray = byteArrayOf()
)