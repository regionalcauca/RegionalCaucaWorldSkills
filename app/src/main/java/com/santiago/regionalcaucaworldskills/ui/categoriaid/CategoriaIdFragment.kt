package com.santiago.regionalcaucaworldskills.ui.categoriaid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.regionalcaucaworldskills.databinding.FragmentCategoriaIdBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.webservice.categoriaid.ResponseCategoriaId
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.ResponseCategorias
import com.santiago.regionalcaucaworldskills.ui.CategoriaIdAdapter
import com.santiago.regionalcaucaworldskills.ui.CategoriasAdapter
import com.santiago.regionalcaucaworldskills.ui.home.HomeViewModel
import kotlinx.coroutines.flow.collect


class CategoriaIdFragment : Fragment() {
    private val viewModel: CategoriaIdViewModel by viewModels()
    private var _binding: FragmentCategoriaIdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentCategoriaIdBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launchWhenStarted {
            viewModel.getCategoriaId(Constants.ID_CATEGORIA).collect {
                when(it){
                    is ResponseCategoriaId ->{
                        binding.txtNombre.text = it.nombre
                        binding.txtDescripcion.text = it.descripcion
                        binding.rv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CategoriaIdAdapter(it.productos)
                        binding.rv.adapter = adapter
                        binding.carga.visibility = View.GONE
                        binding.contenedor.visibility = View.VISIBLE
                    }
                }
            }
        }

//
//        val dbManager = DBManager(requireActivity())
//        val lista = dbManager.listCategoriaId(Constants.ID_CATEGORIA)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}