package com.santiago.regionalcaucaworldskills.interfaces

import com.santiago.regionalcaucaworldskills.models.webservice.categoriaid.ResponseCategoriaId
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.ResponseCategorias
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.ResponseEnvioPedido
import com.santiago.regionalcaucaworldskills.models.webservice.especialidad.ResponseEspecialidad
import com.santiago.regionalcaucaworldskills.models.webservice.historial.ResponseHistorial
import com.santiago.regionalcaucaworldskills.models.webservice.registro.BodyRegistro
import com.santiago.regionalcaucaworldskills.models.webservice.iniciosesion.ResponseInicioSesion
import com.santiago.regionalcaucaworldskills.models.webservice.politicas.ResponsePoliticas
import com.santiago.regionalcaucaworldskills.models.webservice.producto.ResponseProducto
import com.santiago.regionalcaucaworldskills.models.webservice.registro.ResponseRegistro
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {


    @POST("pedidos")
    suspend fun postEnvioPedido(@Body bodyEnvioPedido: BodyEnvioPedido ): ResponseEnvioPedido

    @GET("pedidos")
    suspend fun getHistorial(@Query("cliente")cliente:String,@Query("token")token:String): ResponseHistorial

    @GET("productos/{idProductos}")
    suspend fun getProducto(@Path("idProductos")idProductos:Int): ResponseProducto

    @GET("categorias/{idCategoria}")
    suspend fun getCategoriaId(@Path("idCategoria")idCategoria:Int): ResponseCategoriaId

    @GET("categorias")
    suspend fun getCategorias(): ResponseCategorias

    @GET("especialidad")
    suspend fun getEspecialidad(): ResponseEspecialidad

    @GET("politicas?ver")
    suspend fun getPoliticas(): ResponsePoliticas

    @POST("clientes")
    suspend fun postRegistro(@Body bodyRegistro: BodyRegistro): ResponseRegistro

    @GET("clientes")
    suspend fun getInicioSesion(@Query("correo")correo:String,@Query("contrasena")contrasena:String): ResponseInicioSesion

}
object  ApiClient{
    val webService by lazy {
        Retrofit.Builder()
            .baseUrl("https://wsc.fabricasoftware.co/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}