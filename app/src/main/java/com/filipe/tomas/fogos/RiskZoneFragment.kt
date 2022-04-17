package com.filipe.tomas.fogos

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.filipe.tomas.fogos.databinding.FragmentRiskZoneBinding
import java.util.*
import kotlin.concurrent.schedule


private const val ARG_RISKZONE = "param1"



class RiskZoneFragment : Fragment() {
    private lateinit var binding: FragmentRiskZoneBinding
    //private var riskZone: String = Risk.Lower.value.toString()

    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            riskZone = it.getString(ARG_RISKZONE).toString()
        }
    }
     */


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
        loopRisk()
        loopRisk()
    }

    @SuppressLint("SetTextI18n")
     fun loopRisk() {
        binding.textViewRisk.text = "${getString(R.string.risk)}: ${getString(Risk.getLowerRisk())}"
        Timer().scheduleAtFixedRate(,0,2000){
            binding.textViewRisk.text = "${getString(R.string.risk)}: ${getString(Risk.getModeratedRisk())}"
        }
        Timer().scheduleAtFixedRate(4000){
            binding.textViewRisk.text = "${getString(R.string.risk)}: ${getString(Risk.getHighRisk())}"
        }
        Timer().scheduleAtFixedRate(6000){
            binding.textViewRisk.text = "${getString(R.string.risk)}: ${getString(Risk.getVeryHighRisk())}"
        }
        Timer().scheduleAtFixedRate(8000){
            binding.textViewRisk.text = "${getString(R.string.risk)}: ${getString(Risk.getMaxRisk())}"
        }

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