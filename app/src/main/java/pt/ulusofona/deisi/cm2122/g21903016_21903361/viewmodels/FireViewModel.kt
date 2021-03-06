package pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels

import android.app.Application
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.cm2122.g21903016_21903361.District
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireRepository
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.ActiveResources
import java.util.*

class FireViewModel(application: Application) : AndroidViewModel(application) {
    private val model = FireRepository.getInstance()
    private val TAG = FireViewModel::class.java.simpleName
    private var risk = "Não disponível"

    fun onGetFires(onFinished: (List<FireUi>) -> Unit) {
        model.getAllFires(onFinished)
    }

    fun onNewRegistration(
        name: String,
        cc: String,
        district: String,
        picture: ByteArray?,
        observations: String?
    ) {
        val districtEnum = District.valueOf(
            district.uppercase().replace(" ", "_")
        )
        val timsetamp = System.currentTimeMillis()

        val fire = FireUi(
            id = UUID.randomUUID().toString(),
            name = name,
            cc = cc,
            district = district,
            timestamp = timsetamp,
            picture = picture,
            lat = districtEnum.lat,
            lng = districtEnum.lng,
            concelho = district,
            freguesia = district,
            observacoes = observations,
            updated = timsetamp
        )

        model.insertFire(fire) {
            Log.i(TAG, "Fire:$fire was inserted")
        }
    }

    fun onFireMarkerClick(latitute: Double, longitude: Double, onFinished: (FireUi?) -> Unit) {
        onGetFires {
            val selectedFire = it.find { fireUi ->
                fireUi.lat == latitute && fireUi.lng == longitude
            }
            onFinished(selectedFire)
        }
    }

    fun onGetRisk(district: String, onFinished: (String) -> Unit) {
        //Geocoder return "Lisboa" in english the other districts are in portuguese
        model.getRiskForDistrict(district) {
            onFinished(it)
        }
    }

    fun onGetActiveResources(onFinished: (ActiveResources) -> Unit) {
        model.getActiveResources(onFinished)
    }

    fun onGetWeekTotalFires(onFinished: (Int, Int) -> Unit) {
        model.get7DaysTotalFires {
            val week = it.sumOf { it.total }
            val yesterday = it.last().total
            onFinished(week,yesterday)
        }
    }
    fun onUpdatedRisk(risk1: String) {
        risk = risk1
    }

    fun onGetRisk() = risk
}