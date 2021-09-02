package com.santiago.regionalcaucaworldskills.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.santiago.regionalcaucaworldskills.R

import com.santiago.regionalcaucaworldskills.databinding.ItemHistorialBinding

import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.Datos
import com.santiago.regionalcaucaworldskills.models.webservice.historial.Pedidos

class HistorialAdapter (val historial : List<Pedidos>): RecyclerView.Adapter<HistorialAdapter.HistorialHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialHolder(layoutInflater.inflate(R.layout.item_historial,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: HistorialHolder, position: Int) {
        holder.bind(historial[position])
    }

    override fun getItemCount(): Int = historial.size
    class HistorialHolder(val view : View,val context:Context): RecyclerView.ViewHolder(view){
        private val binding = ItemHistorialBinding.bind(view)
        fun bind(historial: Pedidos){
            val shared = context.getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val nombre = shared.getString(Constants.KEY_NOMBRE,null)
            binding.txtId.text = "Pedido # :"+historial.id
            binding.txtFecha.text = historial.created_at
            binding.txtNombre.text = "Cliente : "+nombre
            binding.txtTotal.text = "Total : $"+historial.total
        }
    }
}