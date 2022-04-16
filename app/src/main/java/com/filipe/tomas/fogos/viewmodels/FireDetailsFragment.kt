package com.filipe.tomas.fogos.viewmodels

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentFireDetailsBinding
import com.filipe.tomas.fogos.FireUi

private const val ARG_FIRE = "param1"

class FireDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFireDetailsBinding
    private var fireUi: FireUi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fireUi = it.getParcelable(ARG_FIRE)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.nome.text = "Nome: ${fireUi?.name}"
        binding.cc.text = "CC: ${fireUi?.cc}"
        binding.status.text = "Status: ${fireUi?.status}"
        binding.concelho.text = "Concelho: ${fireUi?.concelho}"
        binding.aereos.text = "Aereos: ${fireUi?.aereos.toString()}"
        binding.datetime.text = fireUi?.getDateTime()
        binding.freguesia.text = "Distrito: ${fireUi?.freguesia}"
        binding.operacionais.text = "Operacionais: ${fireUi?.operacionais.toString()}"
        binding.veiculos.text = "Veiculos: ${fireUi?.veiculos.toString()}"
       binding.distrito.text = "Distrito: ${fireUi?.district}"

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_fire_details, container, false)
        binding = FragmentFireDetailsBinding.bind(view)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(fireUi: FireUi) =
            FireDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FIRE, fireUi)
                }
            }
    }
}