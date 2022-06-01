package pt.ulusofona.deisi.cm2122.g21903016_21903361.models

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.FireDao

@Database(entities = [FireRoom::class], version = 3)
abstract class FireDatabase : RoomDatabase() {
    abstract fun fireDao(): FireDao

    companion object {
        private var instance: FireDatabase? = null

        fun getInstance(applicationContext: Context): FireDatabase {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        FireDatabase::class.java,
                        "fire_db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance as FireDatabase
            }
        }
    }
}