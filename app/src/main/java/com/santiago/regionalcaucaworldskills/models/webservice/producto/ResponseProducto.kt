package com.santiago.regionalcaucaworldskills.models.webservice.producto

import com.santiago.regionalcaucaworldskills.models.webservice.categoriaid.Productos

data class ResponseProducto(
    var respuesta:String =  "",
    var productos:Productos =  Productos()
)
