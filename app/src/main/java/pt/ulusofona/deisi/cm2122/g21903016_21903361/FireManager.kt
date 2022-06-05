package pt.ulusofona.deisi.cm2122.g21903016_21903361

import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.ActiveResources
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.TotalFires

abstract class FireManager {
    abstract fun insertFire(fireUi: FireUi, onFinished: () -> Unit)
    abstract fun insertFires(firesUi: List<FireUi>, onFinished: (List<FireUi>) -> Unit)
    abstract fun getAllFires(onFinished: (List<FireUi>) -> Unit)
    abstract fun deleteAllFires(onFinished: () -> Unit)
    abstract fun getRiskForDistrict(district: String, onFinished: (String) -> Unit)
    abstract fun getActiveResources(onFinished: (ActiveResources) -> Unit)
    abstract fun get7DaysTotalFires(onFinished: (List<TotalFires>) -> Unit)
}