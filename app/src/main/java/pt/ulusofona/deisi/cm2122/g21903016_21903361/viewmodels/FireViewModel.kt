package pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.Geofence
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.*
import java.io.ByteArrayOutputStream
import java.util.*

class FireViewModel(application: Application) : AndroidViewModel(application) {
    private val model = FireRepository.getInstance()
    private val TAG = FireViewModel::class.java.simpleName

    fun onGetFires(onFinished: (List<FireUi>) -> Unit) {
        model.getAllFires(onFinished)
    }

    fun onNewRegistration(name: String, cc: String, district: String, picture: ByteArray?) {
        val districtEnum = District.valueOf(
            district.uppercase().replace(" ", "_")
        )
        val fire = FireUi(
            id = UUID.randomUUID().toString(),
            name = name,
            cc = cc,
            district = district,
            timestamp = System.currentTimeMillis(),
            picture = picture,
            lat = districtEnum.lat,
            lng = districtEnum.lng,
            concelho = district,
            freguesia = district
        )
        model.insertFire(fire) {
            Log.i(TAG, "Fire:$fire was inserted")
        }
    }

    fun onFireMarkerClick(latitute: Double, longitude: Double, onFinished: (FireUi?) -> Unit){
        onGetFires {
           val selectedFire = it.find {
                   fireUi -> fireUi.lat == latitute && fireUi.lng == longitude
           }
            onFinished(selectedFire)
        }
    }

    fun getDistrictByLatLng(latitute: Double, longitude: Double): String
    {
        val geocoder = Geocoder(this.getApplication(), Locale.getDefault())
        val address = geocoder.getFromLocation(latitute,longitude,1).first()
        return address.adminArea.removeSuffix("District")
    }

    fun onGetRisk(district: String, onFinished:(String) -> Unit){
        model.getRiskForDistrict(if (district == "Lisbon") "Lisboa" else district){
            onFinished(it)
        }
    }
}