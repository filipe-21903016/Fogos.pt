package pt.ulusofona.deisi.cm2122.g21903016_21903361

object Filter{
    var district: String = ""
    var radius: Int = 0
    fun isSet(): Boolean = district != "" || radius != 0
    fun reset() {
        district = ""
        radius = 0
    }
    fun districtFilterIsSet() = district != ""
    fun radiusFilterIsSet() = radius != 0
}