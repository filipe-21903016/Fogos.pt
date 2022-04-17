package com.filipe.tomas.fogos

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentRiskZoneBinding
import com.filipe.tomas.fogos.models.Risk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val ARG_RISKZONE = "param1"

class RiskZoneFragment : Fragment() {
    private lateinit var binding: FragmentRiskZoneBinding
    private var riskZone: String = Risk.Lower.value.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            riskZone = it.getString(ARG_RISKZONE).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_risk_zone, container, false)
        binding = FragmentRiskZoneBinding.bind(view)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding.textViewRisk.text = "${getString(R.string.risk)}: ${Risk.VeryHigh}"
    }

    companion object {

        @JvmStatic
        fun newInstance(risk: String) =
            RiskZoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RISKZONE, risk)
                }
            }
    }



}