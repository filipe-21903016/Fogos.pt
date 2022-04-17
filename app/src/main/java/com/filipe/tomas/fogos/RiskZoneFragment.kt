package com.filipe.tomas.fogos

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.filipe.tomas.fogos.databinding.FragmentRiskZoneBinding
import java.util.*
import kotlin.concurrent.schedule

class RiskZoneFragment : Fragment() {
    private lateinit var binding: FragmentRiskZoneBinding
    class RiskTimerTask(val textView: TextView, val getStr:(Int) -> String) : TimerTask() {
        @SuppressLint("SetTextI18n")
        override fun run() {
            this.textView.text = "${getStr(R.string.risk)}: ${getStr(Risk.getRandomRisk())}"
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
        Timer().scheduleAtFixedRate(RiskTimerTask(binding.textViewRisk, ::getString), 0, (20 * 1000).toLong() )
    }
}