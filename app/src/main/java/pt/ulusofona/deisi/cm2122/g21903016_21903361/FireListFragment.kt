package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.content.Context
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.adapters.FireListAdapter
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireListBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.Filter
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

class FireListFragment : Fragment(), OnLocationChangedListener {
    private lateinit var binding: FragmentFireListBinding
    private lateinit var viewModel: FireViewModel
    private var adapter = FireListAdapter(::onFireClick)
    private val TAG = FireListFragment::class.java.simpleName


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //set screen name
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fire_list)

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fire_list, container, false)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        binding = FragmentFireListBinding.bind(view)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onStart() {
        super.onStart()
        binding.rvFires.layoutManager = LinearLayoutManager(activity as Context)
        binding.rvFires.adapter = adapter
        FusedLocation.registerListener(this)

        viewModel.onGetFires {
            val d = filterByDistrict(it)
            updateFires(filterByRadius(d, lat, lng))
        }
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        lat = latitude
        lng = longitude
        Log.i(TAG, "OnLocationChanged: Companion $lat,$lng")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_filter -> {
            NavigationManager.goToFilterFragment(parentFragmentManager)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onFireClick(fireUi: FireUi) {
        NavigationManager.goToFireDetails(parentFragmentManager, fireUi)
    }


    private fun updateFires(fireList: List<FireUi>) {
        val fires = fireList
        CoroutineScope(Dispatchers.Main).launch {
            showFires(fireList.isNotEmpty())
            adapter.updateItems(fires)
        }
    }

    private fun showFires(show: Boolean) {
        if (show) {
            binding.tvErrorMessage.visibility = View.GONE
            binding.rvFires.visibility = View.VISIBLE
        } else {
            binding.rvFires.visibility = View.GONE
            binding.tvErrorMessage.visibility = View.VISIBLE
        }
    }

    private fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val results = FloatArray(10)
        Location.distanceBetween(lat1, lon1, lat2, lon2, results)
        return results[0].toDouble()
    }

    private fun filterByDistrict(fires: List<FireUi>): List<FireUi> {
        if (Filter.districtFilterIsSet()) {
            return fires.filter {
                it.district
                    .lowercase()
                    .replace("ç", "c")
                    .replace("é", "e") == Filter.district.lowercase()
            }
        }
        return fires
    }

    private fun filterByRadius(
        fires: List<FireUi>,
        latitude: Double?,
        longitude: Double?
    ): List<FireUi> {
        if (Filter.radiusFilterIsSet() && latitude != null && longitude != null) {
            val filtered =  fires.filter { f ->
                distance(latitude, longitude, f.lat, f.lng) <= Filter.radius * 1000
            }
            return filtered
        }
        return fires
    }

    companion object {
        var lat: Double? = null
        var lng: Double? = null
    }
}