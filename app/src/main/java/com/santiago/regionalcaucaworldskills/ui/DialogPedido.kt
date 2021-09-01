package com.santiago.regionalcaucaworldskills.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.DialogPedidoBinding

class DialogPedido:DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_pedido,container,false)

        val binding = DialogPedidoBinding.inflate(inflater)



        binding.btnOk.setOnClickListener {
            val i = Intent(requireActivity(),MainActivity::class.java)
            startActivity(i)
            ActivityCompat.finishAffinity(requireActivity())
        }

        isCancelable = true

        return binding.root
    }
}