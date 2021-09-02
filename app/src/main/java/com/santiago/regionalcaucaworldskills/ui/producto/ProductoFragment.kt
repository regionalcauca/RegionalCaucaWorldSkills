package com.santiago.regionalcaucaworldskills.ui.producto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.FragmentProductoBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.local.DBPedido
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.webservice.producto.ResponseProducto
import com.santiago.regionalcaucaworldskills.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collect


class ProductoFragment : Fragment() {
    private val viewModel: ProductoViewModel by viewModels()
    private var _binding: FragmentProductoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentProductoBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val dbManager = DBManager(requireActivity())
//        val listaBind = dbManager.listProducto(Constants.ID_PRODUCTO)

        lifecycleScope.launchWhenStarted {
            viewModel.getProducto(Constants.ID_PRODUCTO).collect { data ->
                when(data){
                    is ResponseProducto ->{
                        binding.txtDescripcion.text = data.productos.descripcion
                        binding.txtNombre.text = data.productos.nombre
                        binding.txtPrecio.text = "$"+data.productos.precio
                        Glide.with(binding.root).load(data.productos.url_imagen).into(binding.img)
                        binding.btnAgregar.setOnClickListener{
                            val dbManager = DBManager(requireActivity())
                            val lista = dbManager.listAcumulacion(data.productos.id)
                            if (lista.isEmpty()){
                                val res = dbManager.insertPedido(
                                    DBPedido(0,data.productos.id,data.productos.nombre,data.productos.descripcion, data.productos.url_imagen,data.productos.precio, data.productos.precio,1)
                                )
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
                        binding.carga.visibility = View.GONE
                        binding.contenedor.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun decodeBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}