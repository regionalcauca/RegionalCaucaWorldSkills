package com.santiago.regionalcaucaworldskills.ui.especialidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.ActivityEspecialidadBinding
import com.santiago.regionalcaucaworldskills.databinding.ActivityRegistroBinding
import com.santiago.regionalcaucaworldskills.models.webservice.especialidad.ResponseEspecialidad
import com.santiago.regionalcaucaworldskills.ui.MainActivity
import kotlinx.coroutines.flow.collect

class EspecialidadActivity : AppCompatActivity() {
    private val viewModel : EspecialidadViewModel by viewModels()
    private lateinit var binding: ActivityEspecialidadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.actionBar?.hide()
        btnContinuar()
        referenciar()
    }

    private fun referenciar() {
        lifecycleScope.launchWhenStarted {
            viewModel.getEspecialidad().collect {
                when(it){
                    is ResponseEspecialidad ->{
                        binding.txtNombre.text = it.datos.nombre
                        binding.txtDescripcion.text = it.datos.descripcion
                        binding.txtPrecio.text = "$"+it.datos.precio
                        Glide.with(binding.root).load(it.datos.url_foto).into(binding.img)
                        binding.carga.visibility = View.GONE
                        binding.contenedor.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun btnContinuar() {
        binding.btnContinuar.setOnClickListener {
            val i  = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}