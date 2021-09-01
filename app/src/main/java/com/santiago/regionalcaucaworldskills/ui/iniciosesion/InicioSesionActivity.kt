package com.santiago.regionalcaucaworldskills.ui.iniciosesion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.santiago.regionalcaucaworldskills.databinding.ActivityInicioSesionBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.ui.especialidad.EspecialidadActivity
import com.santiago.regionalcaucaworldskills.ui.registro.RegistroActivity

class InicioSesionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInicioSesionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioSesionBinding.inflate(layoutInflater)
        this.supportActionBar?.hide()
        setContentView(binding.root)
        loadData()
        btnRegistro()
        btnInicioSesion()
    }


    private fun btnInicioSesion() {
        binding.btnInicioSesion.setOnClickListener {
            if (binding.etCorreo.text.toString().isEmpty() || binding.etCorreo.text.toString()
                    .isEmpty()
            ) {
                Snackbar.make(binding.root, "debe tener los campos completos", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                val dbManager = DBManager(applicationContext)
                val list = dbManager.listRegistro(binding.etCorreo.text.toString())
                Log.e("lista",list.toString())
                if (list.isEmpty()) {
                    Snackbar.make(binding.root, "no existe el usuario", Snackbar.LENGTH_LONG).show()
                } else {
                    if (list[0].contrasena == binding.etContrasena.text.toString()) {
                        saveData()
                        saveStatus()
                        val i = Intent(applicationContext, EspecialidadActivity::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        Snackbar.make(binding.root, "los datos no coinciden", Snackbar.LENGTH_LONG)
                            .show()
                    }
                }
            }
        }
    }

    private fun saveStatus() {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = shared.edit()
        edit.apply {
            putBoolean(Constants.KEY_STATUS,true)
        }.apply()
    }

    private fun loadData() {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val correo = shared.getString(Constants.KEY_CORREO,null)
        val contrasena = shared.getString(Constants.KEY_CONTRASENA,null)
        val recordar = shared.getBoolean(Constants.KEY_RECORDAR,false)
        binding.etCorreo.setText(correo)
        binding.etContrasena.setText(contrasena)
        if (recordar)binding.cbRecordar.isChecked = true
    }

    private fun saveData() {
        val shared = getSharedPreferences("inicioSesion",Context.MODE_PRIVATE)
        val edit = shared.edit()
        if (binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,binding.etCorreo.text.toString())
                putString(Constants.KEY_CONTRASENA,binding.etContrasena.text.toString())
                putBoolean(Constants.KEY_RECORDAR,true)
            }.apply()
        }else if(!binding.cbRecordar.isChecked){
            edit.apply {
                putString(Constants.KEY_CORREO,"")
                putString(Constants.KEY_CONTRASENA,"")
                putBoolean(Constants.KEY_RECORDAR,false)
            }.apply()
        }
    }

    private fun btnRegistro() {
        binding.btnRegistro.setOnClickListener {
            val i = Intent(applicationContext, RegistroActivity::class.java)
            startActivity(i)
        }
    }
}