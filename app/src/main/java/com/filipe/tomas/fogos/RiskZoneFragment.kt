package com.filipe.tomas.fogos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.filipe.tomas.fogos.R
import com.filipe.tomas.fogos.databinding.FragmentRiskZoneBinding
import com.filipe.tomas.fogos.models.Risk

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_RISKZONE = "param1"

class RiskZoneFragment : Fragment() {
    // TODO: Rename and change types of parameters
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RiskZoneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(risk: String) =
            RiskZoneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_RISKZONE, risk)
                }
            }
    }



}