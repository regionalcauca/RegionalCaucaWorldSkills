package com.santiago.regionalcaucaworldskills.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.FragmentDashboardBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.local.bd.DBManager
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.BodyEnvioPedido
import com.santiago.regionalcaucaworldskills.models.webservice.enviopedido.ResponseEnvioPedido
import com.santiago.regionalcaucaworldskills.ui.DialogPedido
import com.santiago.regionalcaucaworldskills.ui.PedidoAdapter
import kotlinx.coroutines.flow.collect

class DashboardFragment : Fragment() {

    private val dashboardViewModel: DashboardViewModel by viewModels()
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
        if (!lista.isEmpty()){
            binding.rv.layoutManager = LinearLayoutManager(requireContext())
            val adapter = PedidoAdapter(lista)
            binding.rv.adapter = adapter
            binding.contenedor.visibility = View.GONE
            binding.rv.visibility = View.VISIBLE
        }




        val total = dbManager.sumTotal()
        binding.txtTotal.text = "Total : $ "+total
        binding.txtTotalIva.text = "Total IVA : $ "+(total*1.19).toInt()


        binding.imgDelete.setOnClickListener {
            val res = dbManager.deleteAll()
            if (res>0){
                Navigation.findNavController(binding.root).navigate(R.id.navigation_dashboard)
            }
        }

        binding.btnFinalizar.setOnClickListener{

            val shared = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val id = shared.getString(Constants.KEY_ID_CLIENTE,null)
            val lista = dbManager.listEnvioPedido()
            val totalEnivio = dbManager.sumTotal()
            if (lista.isEmpty()){
                Snackbar.make(binding.root,"No se puede enviar un pedido vacio",Snackbar.LENGTH_LONG).show()
            }else{
                lifecycleScope.launchWhenStarted {
                    dashboardViewModel.postEnvioPedido(BodyEnvioPedido(id!!.toInt(),lista.toString(),totalEnivio)).collect {
                        when(it){
                            is ResponseEnvioPedido ->{
                                if (it.respuesta == "OK"){
                                    val res = dbManager.deleteAll()
                                    if (res>0){
                                        val dialog = DialogPedido()
                                        dialog.show(requireActivity().supportFragmentManager,"pedido")
                                    }
                                }else{
                                    Snackbar.make(binding.root,"No se pudo enviar su pedido",Snackbar.LENGTH_LONG).show()
                                }
                            }else ->Snackbar.make(binding.root,"Error al enviar el pedido",Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
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