package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.location.Location

object Filter {
    var district: String = ""
    var radius: Int = 0
    fun reset() {
        district = ""
        radius = 0
    }

    fun districtFilterIsSet() = district != ""
    fun radiusFilterIsSet() = radius != 0


    fun filterByDistrict(fires: List<FireUi>): List<FireUi> {
        if (districtFilterIsSet()) {
            return fires.filter {
                it.district
                    .lowercase()
                    .replace("ç", "c") //remove special chars from districts received from api
                    .replace("é", "e") == Filter.district.lowercase()
            }
        }
        return fires
    }

    fun filterByRadius(
        fires: List<FireUi>,
        latitude: Double?,
        longitude: Double?
    ): List<FireUi> {
        if (radiusFilterIsSet() && latitude != null && longitude != null) {
            val filtered = fires.filter { f ->
                distance(latitude, longitude, f.lat, f.lng) <= radius * 1000
            }
            return filtered
        }
        return fires
    }

    fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val results = FloatArray(10)
        Location.distanceBetween(lat1, lon1, lat2, lon2, results)
        return results[0].toDouble()
    }
}