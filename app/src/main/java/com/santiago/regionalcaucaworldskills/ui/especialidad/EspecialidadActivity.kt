package com.santiago.regionalcaucaworldskills.ui.especialidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.ActivityEspecialidadBinding
import com.santiago.regionalcaucaworldskills.databinding.ActivityRegistroBinding
import com.santiago.regionalcaucaworldskills.ui.MainActivity

class EspecialidadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEspecialidadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.actionBar?.hide()
        btnContinuar()
    }

    private fun btnContinuar() {
        binding.btnContinuar.setOnClickListener {
            val i  = Intent(applicationContext,MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}