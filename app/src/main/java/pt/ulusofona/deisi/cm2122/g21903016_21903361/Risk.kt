package pt.ulusofona.deisi.cm2122.g21903016_21903361
object Risk{
    fun getLowerRisk() = R.string.lower
    fun getModeratedRisk() = R.string.moderated
    fun getHighRisk() = R.string.high
    fun getVeryHighRisk() = R.string.veryHigh
    fun getMaxRisk() = R.string.max

    fun getRandomRisk() : Int{
        val randomNumber = (0..4).random()
        when(randomNumber){
            0 -> return getLowerRisk()
            1 -> return getModeratedRisk()
            2 -> return getHighRisk()
            3 -> return getVeryHighRisk()
            else -> return getMaxRisk()
        }
    }
}
