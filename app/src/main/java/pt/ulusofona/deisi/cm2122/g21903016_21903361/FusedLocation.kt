package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnLocationChangedListener
import java.util.*

@SuppressLint("MissingPermission")
class FusedLocation private constructor(context: Context) : LocationCallback() {
    private val TAG = FusedLocation::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 10 * 1000L
    private val geocoder: Geocoder

    @SuppressLint("VisibleForTests")
    private var client = FusedLocationProviderClient(context)

    private var locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = TIME_BETWEEN_UPDATES
    }

    fun getDistrictByLatLng(latitute: Double, longitude: Double): String {
        val address = geocoder.getFromLocation(latitute, longitude, 1).first()
        return address.adminArea.removeSuffix("district").let { if (it == "Lisbon") "Lisboa" else it }
    }

    override fun onLocationResult(locationResult: LocationResult) {
        Log.i(TAG, "Got Location Result")
        lat = locationResult.lastLocation.latitude
        lng = locationResult.lastLocation.longitude
        district = getDistrictByLatLng(lat, lng)
        Log.i(TAG, district)

        Log.i(TAG, "Will notify")
        notifyListeners(locationResult)
    }

    init {
        geocoder = Geocoder(context, Locale.getDefault())
        //instancia objecto que permite definir as configs
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        //aplicar as configs ao servico de localizacao
        LocationServices.getSettingsClient(context)
            .checkLocationSettings(locationSettingsRequest)

        client.requestLocationUpdates(locationRequest, this, Looper.getMainLooper())
    }

    companion object {
        private var listeners: MutableList<OnLocationChangedListener> = mutableListOf()
        private var instance: FusedLocation? = null
        var lat: Double = District.LISBOA.lat
        var lng: Double = District.LISBOA.lng
        var district : String = "Lisboa"


        fun registerListener(listener: OnLocationChangedListener) {
            Log.i(FusedLocation::class.java.simpleName, "Listener Registered")
            listeners.add(listener)
        }

        fun unregisterListener(listener: OnLocationChangedListener) {
            listeners.remove(listener)
            Log.i(FusedLocation::class.java.simpleName, "Listener Removed")
        }

        fun notifyListeners(locationResult: LocationResult) {
            val location = locationResult.lastLocation
            listeners.forEach { it.onLocationChanged(location.latitude, location.longitude) }
        }

        fun start(context: Context) {
            instance = if (instance == null) FusedLocation(context) else instance
        }
    }
}