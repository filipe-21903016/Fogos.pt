package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.databinding.FragmentFireMapBinding
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels.FireViewModel
import java.util.*


class FireMapFragment : Fragment(), OnLocationChangedListener, GoogleMap.OnMarkerClickListener,
    OnMapReadyCallback {
    private var TAG = FireMapFragment::class.java.simpleName
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
        Log.wtf(TAG, FusedLocation.district)
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

    override fun onStart() {
        super.onStart()
        binding.fabAddFire.setOnClickListener{
            NavigationManager.goToFireRegistrationFragment(parentFragmentManager)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener(this)
    }

    private fun createMarker(map: GoogleMap, fireUi: FireUi) {
        map.addMarker(
            MarkerOptions()
                .position(LatLng(fireUi.lat, fireUi.lng))
                .icon(
                    vectorToBitmap(R.drawable.ic_logo, Color.parseColor("#ff${fireUi.statusColor}"))
                )
        )
        map.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        var consumed = false
        viewModel.onFireMarkerClick(marker.position.latitude, marker.position.longitude) {
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

    private fun vectorToBitmap(@DrawableRes id: Int, @ColorInt color: Int): BitmapDescriptor {
        val vectorDrawable = ResourcesCompat.getDrawable(
            resources, id, null
        )
        val bitmap = Bitmap.createBitmap(
            150,
            150, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        var paint = Paint()
        paint.color = color
        paint.isAntiAlias = true
        canvas.drawCircle(canvas.width / 2f, canvas.height / 2f,canvas.width / 2f, paint)

        val quarterVertical = canvas.height / 4
        val quarterHorizontal = canvas.width / 4

        vectorDrawable!!.setBounds(quarterHorizontal, quarterVertical, quarterHorizontal * 3,  quarterVertical * 3)

        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

}