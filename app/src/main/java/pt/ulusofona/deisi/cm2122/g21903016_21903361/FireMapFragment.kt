package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireMapBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import java.util.*


class FireMapFragment : Fragment(), OnLocationChangedListener, GoogleMap.OnMarkerClickListener,
    OnMapReadyCallback {
    private lateinit var viewModel: FireViewModel
    private lateinit var binding: FragmentFireMapBinding
    private lateinit var geocoder: Geocoder
    private var map: GoogleMap? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(R.string.fire_map)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fire_map, container, false)
        binding = FragmentFireMapBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        geocoder = Geocoder(context, Locale.getDefault())
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync {
            this.map = it
            FusedLocation.registerListener(this)
            onMapReady(it)
        }
        return binding.root
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onResume() {
        super.onResume()
        binding.map.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun onPause() {
        super.onPause()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        placeCamera(latitude, longitude)
    }

    private fun placeCamera(latitude: Double, longitude: Double) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(10f)
            .build()
        map?.animateCamera(
            CameraUpdateFactory.newCameraPosition(cameraPosition)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener(this)
    }

    private fun createMarker(map: GoogleMap, fireUi: FireUi) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(fireUi.lat, fireUi.lng))
        )
        map.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var consumed = false
        viewModel.onFireMarkerClick(marker.position.latitude, marker.position.longitude){
            if (it != null) {
                consumed = true
                NavigationManager.goToFireDetails(parentFragmentManager, it)
            }
        }
        return consumed
    }

    override fun onMapReady(map: GoogleMap) {
        viewModel.onGetFires {
            it.forEach { fireUi ->
                CoroutineScope(Dispatchers.Main).launch {
                    createMarker(map, fireUi)
                }
            }
        }
    }

}