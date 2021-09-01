package com.santiago.regionalcaucaworldskills.ui.registro

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.santiago.regionalcaucaworldskills.databinding.ActivityRegistroBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.DBRegistro
import com.santiago.regionalcaucaworldskills.ui.especialidad.EspecialidadActivity
import com.santiago.regionalcaucaworldskills.ui.iniciosesion.InicioSesionActivity

class RegistroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()


        btnRegistro()
        btnInicioSesion()


    }

    private fun btnInicioSesion() {
        binding.btnInicioSesion.setOnClickListener {
            val i = Intent(applicationContext, InicioSesionActivity::class.java)
            startActivity(i)
        }
    }

    private fun btnRegistro() {
        binding.btnRegistro.setOnClickListener {
            if (binding.etNombre.text.toString().isEmpty() || binding.etCiudad.text.toString()
                    .isEmpty() || binding.etCorreo.text.toString()
                    .isEmpty() || binding.etContrasena.text.toString().isEmpty()){
                Snackbar.make(binding.root,"debe tener los campos completos",Snackbar.LENGTH_LONG).show()
            }else{
                val shared = getSharedPreferences("politicas", Context.MODE_PRIVATE)
                    val poli = shared.getString(Constants.KEY_POLITICAS,null)
                val dialog = AlertDialog.Builder(this@RegistroActivity)
                    .setTitle("Politicas de privacidad y proteccion de datos")
                    .setMessage(poli)
                    .setNegativeButton("cancelar"){Dialog,with->
                        Snackbar.make(binding.root,"no se creo la cuenta",Snackbar.LENGTH_LONG).show()
                    }
                    .setPositiveButton("Aceptar"){Dialog,with->
                        val dbManager = DBManager(applicationContext)
                        val lista = dbManager.listRegistro(binding.etCorreo.text.toString())
                        if (lista.isEmpty()){
                            val res = dbManager.insertRegistro(DBRegistro(0,binding.etNombre.text.toString(), binding.etCiudad.text.toString(), binding.etCorreo.text.toString(), binding.etContrasena.text.toString()))
                            if (res>0){
                                val i = Intent(applicationContext, EspecialidadActivity::class.java)
                                startActivity(i)
                                finish()
                            }
                        }else{
                            Snackbar.make(binding.root,"ya se encuentra registrado",Snackbar.LENGTH_LONG).show()
                        }

                    }
                    .setCancelable(false)
                    .create()
                dialog.show()
            }
        }
    }
}