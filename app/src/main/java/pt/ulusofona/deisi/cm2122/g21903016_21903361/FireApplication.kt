package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.app.Application

class FireApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FireRepository.init(
            this,
            FireManagerRoom(FireDatabase.getInstance(this).fireDao()),
            FireManagerRetrofit(RetrofitBuilder.getInstance("https://api-dev.fogos.pt"))
        )
        FusedLocation.start(this)
        Battery.start(this)
    }
}