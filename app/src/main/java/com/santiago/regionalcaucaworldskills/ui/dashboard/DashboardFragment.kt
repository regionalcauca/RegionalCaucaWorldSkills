package com.santiago.regionalcaucaworldskills.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.regionalcaucaworldskills.databinding.FragmentDashboardBinding
import com.santiago.regionalcaucaworldskills.models.bd.DBManager
import com.santiago.regionalcaucaworldskills.ui.DialogPedido
import com.santiago.regionalcaucaworldskills.ui.PedidoAdapter

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dbManager = DBManager(requireActivity())
        val lista = dbManager.listPedido()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PedidoAdapter(lista)
        binding.rv.adapter = adapter


        val total = dbManager.sumTotal()
        binding.txtTotal.text = "Total : $ "+total
        binding.txtTotalIva.text = "Total : $ "+(total*1.19).toInt()


        binding.btnFinalizar.setOnClickListener{

            val res = dbManager.deleteAll()
            if (res>0){
                val dialog = DialogPedido()
                dialog.show(requireActivity().supportFragmentManager,"pedido")
            }
        }
    }

    private fun totales() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}