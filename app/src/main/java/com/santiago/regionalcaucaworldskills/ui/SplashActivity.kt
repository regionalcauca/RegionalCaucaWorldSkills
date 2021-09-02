package com.santiago.regionalcaucaworldskills.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.models.*
import com.santiago.regionalcaucaworldskills.models.local.bd.DBHelper
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.local.DBCategoria
import com.santiago.regionalcaucaworldskills.models.local.DBCategoriaId
import com.santiago.regionalcaucaworldskills.ui.iniciosesion.InicioSesionActivity
import java.io.ByteArrayOutputStream

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        this.supportActionBar?.hide()
        val dbHelper  = DBHelper(applicationContext)
        dbHelper.writableDatabase

        saveDataPoliticas()
        //insertarCategorias()
        //insertarCategoriaId()
        startTimer()

    }


    private fun insertarCategoriaId() {
        val imgBitmap = BitmapFactory.decodeResource(resources,R.drawable.parriladagrande)

        val dbManager = DBManager(applicationContext)
        val res = dbManager.insertCategoriaId(DBCategoriaId(0,"Parrillada Grande","200g de solomito, 220g de punta de anca, 120g de carne de res, 120g de pechuga de pollo, chicharrón, mini chorizo, mini morcilla, arepa, medallones de plátano maduro y papas a la francesa.",88300,codeBitmap(imgBitmap),3))
        if(res>0){
            Toast.makeText(applicationContext, "enviado", Toast.LENGTH_SHORT).show()
        }

    }
    private fun insertarCategorias() {
        val imgBitmap = BitmapFactory.decodeResource(resources,R.drawable.bebida)

        val dbManager = DBManager(applicationContext)
        val res = dbManager.insertCategoria(DBCategoria(0,"Bebidas","En esta categoría encontrará las bebidas",codeBitmap(imgBitmap)))

    }
    private fun codeBitmap(bitmap: Bitmap):ByteArray{
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,stream)
        return stream.toByteArray()

    }

    private fun saveDataPoliticas() {
        val shared = getSharedPreferences("politicas",Context.MODE_PRIVATE)
        val edit  = shared.edit()
        edit.apply {
            putString(Constants.KEY_POLITICAS,"Entiendo que, en consecuencia, el Restaurante es responsable por asegurar la concordancia entre los datos que le han sido suministrado y los que registra/divulga, pero no tiene ninguna responsabilidad por la calidad y veracidad de los datos reportados.Es claro para mí que, por medio de esta consulta de datos, el Restaurante pone a mi alcance los mecanismos necesarios para que pueda ejercer el derecho de habeas data, de acuerdo con lo establecido en el artículo 15 de la Constitución Política de Colombia, la Ley 1581 de 2012 y de acuerdo con la doctrina de la Corte Constitucional de Colombia.Puede consultar nuestra Política de Privacidad y Protección de Datos Personales")
        }.apply()
    }

    private fun startTimer() {
        object : CountDownTimer(2000, 100) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
                val status = shared.getBoolean(Constants.KEY_STATUS,false)
                if (status){
                    val i  = Intent(applicationContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                }else{
                    val i  = Intent(applicationContext, InicioSesionActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }.start()
    }
}