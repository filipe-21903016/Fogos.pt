package pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces

import okhttp3.Call
import okhttp3.ResponseBody
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.ActiveResources
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.TotalFires
import retrofit2.http.GET
import retrofit2.http.Query

class DateTimeResponse(val sec: Int)

data class GetActiveFiresResponse(
    val sucess: Boolean,
    val data: List<FireResponse>
)
data class TotalFiresResponse(
    val data: List<TotalFires>
)

data class ActiveResourcesResponse(val data: ActiveResources)

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

    @GET("v1/now")
    suspend fun getActiveResourcesCount(): ActiveResourcesResponse

    @GET("v1/stats/week")
    suspend fun get7DaysTotalFires(): TotalFiresResponse
}













