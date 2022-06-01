package pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces

import retrofit2.http.GET

class DateTimeResponse(val sec: Int)

data class GetActiveFiresResponse(
    val sucess: Boolean,
    val data: List<FireResponse>
)

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
    val lng: Double
)


interface FireManagerService {
    @GET("new/fires")
    suspend fun getActiveFires(): GetActiveFiresResponse
}