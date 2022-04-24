package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentRiskZoneBinding
import java.util.*

class RiskZoneFragment : Fragment() {
    private lateinit var binding: FragmentRiskZoneBinding
    private var timer = Timer()

    class RiskTimerTask(val textView: TextView, val getStr: (Int) -> String) : TimerTask() {
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
        timer.scheduleAtFixedRate(
            RiskTimerTask(binding.textViewRisk, ::getString),
            0,
            (20 * 1000).toLong()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}