package com.santiago.regionalcaucaworldskills.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.ItemCategoriaIdBinding
import com.santiago.regionalcaucaworldskills.models.Constants

import com.santiago.regionalcaucaworldskills.models.DBCategoriaId
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.DBPedido

class CategoriaIdAdapter (val categorias : List<DBCategoriaId>): RecyclerView.Adapter<CategoriaIdAdapter.CategoriaIdHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaIdHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriaIdHolder(layoutInflater.inflate(R.layout.item_categoria_id,parent,false),parent.context)
    }

    override fun onBindViewHolder(holder: CategoriaIdHolder, position: Int) {
        holder.bind(categorias[position])
    }

    override fun getItemCount(): Int = categorias.size
    class CategoriaIdHolder(val view : View,val context: Context): RecyclerView.ViewHolder(view){
        private val binding = ItemCategoriaIdBinding.bind(view)
        val dbManager = DBManager(context)
        fun bind(categoriaId: DBCategoriaId){
            binding.txtNombre.text = categoriaId.nombre
            binding.txtDescripcion.text = categoriaId.descripcion
            binding.txtPrecio.text = "$"+categoriaId.precio
            binding.imageView3.setImageBitmap(decodeBitmap(categoriaId.imagen))
            binding.root.setOnClickListener {
                Constants.ID_PRODUCTO = categoriaId.id
                Navigation.findNavController(binding.root).navigate(R.id.navigation_producto)
            }
            binding.btnAgregar.setOnClickListener{
                val lista = dbManager.listAcumulacion(categoriaId.id)
                if (lista.isEmpty()){
                    val res = dbManager.insertPedido(DBPedido(0,categoriaId.id,categoriaId.nombre,categoriaId.descripcion,
                        categoriaId.imagen,categoriaId.precio, categoriaId.precio,1))
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }else{
                    val res =  dbManager.updatePedido(lista[0].id,lista[0].precioUnidad*(lista[0].cantidad+1),lista[0].cantidad+1)
                    if (res>0){
                        Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
                    }
                }
            }
        }
    }

}
private fun decodeBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
}