package com.santiago.regionalcaucaworldskills.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.ItemCategoriasBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.local.DBCategoria
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.Datos

class CategoriasAdapter(val categorias : List<Datos>):RecyclerView.Adapter<CategoriasAdapter.CategoriasHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriasHolder(layoutInflater.inflate(R.layout.item_categorias,parent,false))
    }

    override fun onBindViewHolder(holder: CategoriasHolder, position: Int) {
        holder.bind(categorias[position])
    }

    override fun getItemCount(): Int = categorias.size
    class CategoriasHolder(val view : View):RecyclerView.ViewHolder(view){
        private val binding = ItemCategoriasBinding.bind(view)
        fun bind(categorias: Datos){
            binding.txtNombre.text = categorias.nombre
            binding.txtDescripcion.text = categorias.descripcion
            //binding.imageView3.setImageBitmap(decodeBitmap(categorias.imagen))
            Glide.with(binding.root).load(categorias.url_imagen).into(binding.imageView3)
            binding.root.setOnClickListener {
                Constants.ID_CATEGORIA = categorias.id
                Navigation.findNavController(binding.root).navigate(R.id.navigation_categoria_id)
            }
        }
    }

}
private fun decodeBitmap(byteArray: ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
}