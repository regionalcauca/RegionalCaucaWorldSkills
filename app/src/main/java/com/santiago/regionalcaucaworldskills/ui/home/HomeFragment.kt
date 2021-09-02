package com.santiago.regionalcaucaworldskills.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.regionalcaucaworldskills.databinding.FragmentHomeBinding
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.webservice.categorias.ResponseCategorias
import com.santiago.regionalcaucaworldskills.ui.CategoriasAdapter
import kotlinx.coroutines.flow.collect

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val dbManager = DBManager(requireActivity())
//        val lista = dbManager.listCategorias()

        lifecycleScope.launchWhenStarted {
            homeViewModel.getCategorias().collect {
                when(it){
                    is ResponseCategorias ->{
                        binding.rv.layoutManager = LinearLayoutManager(requireContext())
                        val adapter = CategoriasAdapter(it.datos)
                        binding.rv.adapter = adapter
                        binding.carga.visibility = View.GONE
                        binding.rv.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}