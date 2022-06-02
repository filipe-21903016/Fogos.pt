package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentRiskZoneBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import java.util.*
import kotlin.concurrent.timerTask

class RiskZoneFragment : Fragment(), OnLocationChangedListener{
    private lateinit var binding: FragmentRiskZoneBinding
    private lateinit var viewModel: FireViewModel
    private var risk: String = "Máximo"

    private var timer = Timer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_risk_zone, container, false)
        binding = FragmentRiskZoneBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        FusedLocation.registerListener(this)
        timer.scheduleAtFixedRate(
            timerTask {
                updateRisk()
            },
            0,
            20 * 1000L
        )
    }

    @SuppressLint("SetTextI18n")
    private fun updateRisk() {
        binding.textViewRisk.text = "${getString(R.string.risk)}: $risk"
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        val district = viewModel.getDistrictByLatLng(latitude,longitude)
        viewModel.onGetRisk(district){
            risk = it
        }
    }
}