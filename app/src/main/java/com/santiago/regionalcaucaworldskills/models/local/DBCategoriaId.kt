package com.santiago.regionalcaucaworldskills.models.local

data class DBCategoriaId(
    var id:Int = 0,
    var nombre:String = "",
    var descripcion:String = "",
    var precio:Int = 0,
    var imagen:ByteArray = byteArrayOf(),
    var tipoCategoria : Int = 0
)
