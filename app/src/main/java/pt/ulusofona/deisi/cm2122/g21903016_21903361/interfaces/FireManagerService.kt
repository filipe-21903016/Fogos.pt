package pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces

import okhttp3.Call
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

class DateTimeResponse(val sec: Int)

data class GetActiveFiresResponse(
    val sucess: Boolean,
    val data: List<FireResponse>
)

data class RiskResponse(val sucess: Boolean, val data: String)

class FireResponse(
    val id: String,
    val coords: Boolean,
    val dateTime: DateTimeResponse,
    val aerial: Int,
    val terrain: Int,
    val man: Int,
    val district: String,
    val concelho: String,
    val freguesia: String,
    val status: String,
    val statusColor: String,
    val lat: Double,
    val lng: Double,
    val updated: DateTimeResponse
)


interface FireManagerService {
    @GET("new/fires")
    suspend fun getActiveFires(): GetActiveFiresResponse

    @GET("v1/risk")
    suspend fun getRiskForDistrict(@Query(value = "concelho", encoded = true) concelho: String) : RiskResponse
}











