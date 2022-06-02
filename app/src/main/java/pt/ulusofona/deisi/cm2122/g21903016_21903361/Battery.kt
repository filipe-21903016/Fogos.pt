package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.annotation.SuppressLint
import android.content.Context
import android.os.BatteryManager
import android.os.Handler
import android.util.Log
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.OnBatteryCurrentListener

class Battery private constructor(val context: Context) : Runnable {
    private val TAG = Battery::class.java.simpleName
    private val TIME_BETWEEN_UPDATES = 20 * 1000L

    private fun start() {
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    private fun getBatteryCurrentNow(): Double {
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager

        //consumo instantaneo em mA
        val value = manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)

        //mA to A
        return if (value != 0 && value != Int.MIN_VALUE) value.toDouble() / 1000000 else 0.0
    }

    private fun getBatteryCapacityNow(): Int {
        val manager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        return manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    /*private fun onBatteryCurrentChanged(current: Double) {
        notifyListeners(current)
    }*/

    private fun onBatteryCapacityChanged(capacity: Int){
        notifyListeners(capacity)
    }

    override fun run() {
        val current = getBatteryCurrentNow()
        val capacity = getBatteryCapacityNow()
        Log.i(TAG, "Current:$current%")
        Log.i(TAG, "Capacity:$capacity%")
        //onBatteryCurrentChanged(current)
        onBatteryCapacityChanged(capacity)
        handler.postDelayed(this, TIME_BETWEEN_UPDATES)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: Battery? = null
        private var listeners: MutableList<OnBatteryCurrentListener> = mutableListOf()
        val handler = Handler()

        fun registerListener(listener: OnBatteryCurrentListener) {
            listeners.add(listener)
            Log.i(Battery::class.java.simpleName, "Listener registered: $listener")
        }

        fun unregisterListener(listener: OnBatteryCurrentListener) {
            listeners.remove(listener)
            Log.i(Battery::class.java.simpleName, "Listener removed: $listener")
        }

        /*fun notifyListeners(current: Double) {
            listeners.forEach { it.onCurrentChanged(current) }
        }*/

        fun notifyListeners(capacity: Int){
            listeners.forEach { it.onCapacityChanged(capacity) }
        }

        fun start(context: Context) {
            instance = if (instance == null) Battery(context) else instance
            instance?.start()
        }
    }
}