package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.NavigationManager
import pt.ulusofona.deisi.cm2122.g21903016_21903361.R
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentDashboardBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel


class DashboardFragment : Fragment(), OnLocationChangedListener {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: FireViewModel
    private var fires: List<FireUi>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set screen name
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.dashboard)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        binding = FragmentDashboardBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        FusedLocation.registerListener(this)
        viewModel.onGetActiveResources {
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvResourcesMan.text = it.man.toString()
                binding.tvResourcesAerial.text = it.aerial.toString()
                binding.tvResourcesTerrain.text = it.cars.toString()
            }
        }

        viewModel.onGetFires {
            fires = it
        }

        viewModel.onGetWeekTotalFires { week, yesterday ->
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvYesterdayFires.text = yesterday.toString()
                binding.tvWeekFires.text = week.toString()
            }
        }

        binding.fabAddFire.setOnClickListener {
            NavigationManager.goToFireRegistrationFragment(parentFragmentManager)
        }
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onPause() {
        super.onPause()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        binding.tvDistrict.text = FusedLocation.district
        binding.tvNFiresInDistrictLabel.text =
            "${getString(R.string.active_fires_in)} ${FusedLocation.district}"

        fires?.filter {
            Filter.stripSpecialChars(it.district) == Filter.stripSpecialChars(FusedLocation.district)
        }?.let {
            CoroutineScope(Dispatchers.Main).launch {
                binding.tvNumberFires.text = it?.count().toString()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener(this)
    }
}