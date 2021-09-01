package com.santiago.regionalcaucaworldskills.ui.producto

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.FragmentProductoBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.DBPedido
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.ui.home.HomeViewModel


class ProductoFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
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

        val dbManager = DBManager(requireActivity())
        val listaBind = dbManager.listProducto(Constants.ID_PRODUCTO)
        binding.img.setImageBitmap(decodeBitmap(listaBind[0].imagen))
        binding.txtDescripcion.text = listaBind[0].descripcion
        binding.txtNombre.text = listaBind[0].nombre
        binding.txtPrecio.text = "$"+listaBind[0].precio

        binding.btnAgregar.setOnClickListener{
            val lista = dbManager.listAcumulacion(listaBind[0].id)
            if (lista.isEmpty()){
                val res = dbManager.insertPedido(
                    DBPedido(0,listaBind[0].id,listaBind[0].nombre,listaBind[0].descripcion,
                    listaBind[0].imagen,listaBind[0].precio, listaBind[0].precio,1)
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




    }

    private fun decodeBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}