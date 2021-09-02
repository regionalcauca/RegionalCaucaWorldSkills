package com.santiago.regionalcaucaworldskills.ui.notifications

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.santiago.regionalcaucaworldskills.R
import com.santiago.regionalcaucaworldskills.databinding.FragmentNotificationsBinding
import com.santiago.regionalcaucaworldskills.models.Constants
import com.santiago.regionalcaucaworldskills.models.webservice.historial.ResponseHistorial
import com.santiago.regionalcaucaworldskills.ui.HistorialAdapter
import kotlinx.coroutines.flow.collect

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by viewModels()
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


            val shared = requireActivity().getSharedPreferences("inicioSesion", Context.MODE_PRIVATE)
            val id = shared.getString(Constants.KEY_ID_CLIENTE,null)
            val token = shared.getString(Constants.KEY_TOKEN,null)
        lifecycleScope.launchWhenStarted {
            notificationsViewModel.getHistorial(id.toString(),token.toString()).collect {
                Log.e("historial",it.toString())
                when(it){
                    is ResponseHistorial ->{
                        if(it.respuesta == "OK"){
                            binding.rv.layoutManager = LinearLayoutManager(requireContext())
                            val adapter = HistorialAdapter(it.pedidos)
                            binding.rv.adapter = adapter
                            binding.carga.visibility = View.GONE
                            binding.rv.visibility = View.VISIBLE
                        }
                    }else-> Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}