package pt.ulusofona.deisi.cm2122.g21903016_21903361.models

import android.util.DisplayMetrics
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.FireManagerService
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.FireResponse
import retrofit2.HttpException
import retrofit2.Retrofit

class FireManagerRetrofit(retrofit: Retrofit): FireManager() {
    private val TAG = FireManagerRetrofit::class.java.simpleName
    private val service = retrofit.create(FireManagerService::class.java)

    override fun getAllFires(onFinished: (List<FireUi>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getActiveFires()
                val fires = response.data

                onFinished(fires.map {
                    FireUi(
                        id = it.id,
                        timestamp = it.dateTime.sec * 1000L,
                        aereos = it.aerial,
                        veiculos = it.terrain,
                        operacionais = it.man,
                        district = it.district,
                        freguesia = it.freguesia,
                        concelho = it.concelho,
                        status = it.status,
                        statusColor = it.statusColor,
                        lat = it.lat,
                        lng = it.lng
                    )
                })
            } catch (ex: HttpException)
            {
                Log.e(TAG, ex.message())
            }
        }
    }

    override fun getRiskForDistrict(district: String, onFinished: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getRiskForDistrict(district)
                val risk = response.data.split("\r\n").get(1).split("-").get(1).trim().replace(",","")
                Log.e(TAG, "Risk in $district: $risk")
                onFinished(risk)
            }catch (ex: HttpException){
                Log.e(TAG, ex.message())
            }
        }
    }

    override fun deleteAllFires(onFinished: () -> Unit) {
        throw NotImplementedError()
    }

    override fun insertFire(fireUi: FireUi, onFinished: () -> Unit) {
        throw NotImplementedError()
    }

    override fun insertFires(firesUi: List<FireUi>, onFinished: (List<FireUi>) -> Unit) {
        throw NotImplementedError()
    }
}