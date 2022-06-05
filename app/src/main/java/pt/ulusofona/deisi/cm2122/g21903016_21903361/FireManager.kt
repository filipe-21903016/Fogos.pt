package pt.ulusofona.deisi.cm2122.g21903016_21903361

abstract class FireManager {
    abstract fun insertFire(fireUi: FireUi, onFinished: () -> Unit)
    abstract fun insertFires(firesUi: List<FireUi>, onFinished: (List<FireUi>) -> Unit)
    abstract fun getAllFires(onFinished: (List<FireUi>) -> Unit)
    abstract fun deleteAllFires(onFinished: () -> Unit)
    abstract fun getRiskForDistrict(district: String, onFinished: (String) -> Unit)
}