package com.santiago.regionalcaucaworldskills.models.local

data class DBRegistro(
    var id : Int = 0,
    var nombre : String = "",
    var ciudad : String = "",
    var correo : String = "",
    var contrasena : String = "",
)
