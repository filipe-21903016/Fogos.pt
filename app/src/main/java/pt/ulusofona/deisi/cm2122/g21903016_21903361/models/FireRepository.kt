package pt.ulusofona.deisi.cm2122.g21903016_21903361.models

import android.annotation.SuppressLint
import android.content.Context
import pt.ulusofona.deisi.cm2122.g21903016_21903361.ConectivityUtil
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
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
                        val allFires = (lFires + rFires).distinctBy { it.id }.toList()
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