package com.santiago.regionalcaucaworldskills.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.ItemPedidoBinding
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.local.DBPedido

class PedidoAdapter(val pedido: List<DBPedido>) :
    RecyclerView.Adapter<PedidoAdapter.PedidoHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PedidoHolder(layoutInflater.inflate(R.layout.item_pedido, parent, false),parent.context)
    }

    override fun onBindViewHolder(holder: PedidoHolder, position: Int) {
        holder.bind(pedido[position])
    }

    override fun getItemCount(): Int = pedido.size
    class PedidoHolder(val view: View,val context: Context) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPedidoBinding.bind(view)
        val dbManager = DBManager(context)
        fun bind(pedido: DBPedido) {
            if (pedido.cantidad>1)binding.imgRes.setImageResource(R.drawable.ic_baseline_horizontal_rule_24)
            binding.txtPrecioUnidad.text = "Precio Unidad : $ " + pedido.precioUnidad
            binding.txtPrecioTotal.text = "Precio Total : $ " + pedido.precioTotal
            binding.txtCantidad.text = pedido.cantidad.toString()
            //binding.img.setImageBitmap(decodeBitmap(pedido.imagen))
            Glide.with(binding.root).load(pedido.imagen).into(binding.img)
            binding.imgSum.setOnClickListener {
                val res =  dbManager.updatePedido(pedido.id,pedido.precioUnidad*(pedido.cantidad+1),pedido.cantidad+1)
                if (res>0){
                    Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                }
            }
            binding.imgRes.setOnClickListener {
                if (pedido.cantidad==1){
                    val res =  dbManager.deleteId(pedido.id)
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }else{
                    val res =  dbManager.updatePedido(pedido.id,pedido.precioUnidad*(pedido.cantidad-1),pedido.cantidad-1)
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }
            }
        }
    }

}

private fun decodeBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}