package com.santiago.regionalcaucaworldskills.ui.categoriaid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.regionalcaucaworldskills.databinding.FragmentCategoriaIdBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.ui.CategoriaIdAdapter
import com.santiago.regionalcaucaworldskills.ui.home.HomeViewModel


class CategoriaIdFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
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

        val dbManager = DBManager(requireActivity())
        val lista = dbManager.listCategoriaId(Constants.ID_CATEGORIA)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CategoriaIdAdapter(lista)
        binding.rv.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}