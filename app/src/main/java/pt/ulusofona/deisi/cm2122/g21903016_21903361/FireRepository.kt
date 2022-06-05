package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.ActiveResources
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.TotalFires
import java.lang.IllegalStateException

class FireRepository(
    private val context: Context,
    private val local: FireManager,
    private val remote: FireManager
) {
    fun getAllFires(onFinished: (List<FireUi>) -> Unit) {
        if (ConectivityUtil.isOnline(context)) {
            local.getAllFires { lFires ->
                remote.getAllFires { rFires ->
                    local.deleteAllFires {
                        //filter out previous remote fires
                        val allFires = lFires.filter { it.cc != null } + rFires
                        local.insertFires(allFires, onFinished)
                    }
                }
            }
        } else {
            local.getAllFires(onFinished)
        }
    }

    fun insertFire(fireUi: FireUi, onFinished: () -> Unit) {
        local.insertFire(fireUi, onFinished)
    }

    fun getRiskForDistrict(district: String, onFinished: (String) -> Unit) {
        if (ConectivityUtil.isOnline(context)) {
            remote.getRiskForDistrict(district) {
                onFinished(it)
            }
        }
    }

    fun getActiveResources(onFinished: (ActiveResources) -> Unit) {
        if (ConectivityUtil.isOnline(context)) {
            remote.getActiveResources { onFinished(it) }
        }

    }

    fun get7DaysTotalFires(onFinished: (List<TotalFires>) -> Unit) {
        if (ConectivityUtil.isOnline(context)) {
            remote.get7DaysTotalFires {
                onFinished(it)
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: FireRepository? = null

        fun init(context: Context, local: FireManager, remote: FireManager) {
            synchronized(this) {
                if (instance == null) {
                    instance = FireRepository(context, local, remote)
                }
            }
        }

        fun getInstance(): FireRepository {
            return instance ?: throw IllegalStateException("Repository not initialized")
        }
    }
}