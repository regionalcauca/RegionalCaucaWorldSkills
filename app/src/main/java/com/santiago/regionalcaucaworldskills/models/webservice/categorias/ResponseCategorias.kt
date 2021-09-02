package com.santiago.regionalcaucaworldskills.models.webservice.categorias

data class ResponseCategorias(
    var respuesta:String =  "",
    var datos:ArrayList<Datos> = arrayListOf()
)
