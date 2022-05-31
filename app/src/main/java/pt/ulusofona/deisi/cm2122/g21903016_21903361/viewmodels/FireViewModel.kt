package pt.ulusofona.deisi.cm2122.g21903016_21903361.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import pt.ulusofona.deisi.cm2122.g21903016_21903361.data.DataSource
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.FireDatabase
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.FireManagerRoom
import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.FireRoom
import java.io.ByteArrayOutputStream
import java.util.*

class FireViewModel(application: Application) : AndroidViewModel(application) {
    private val model = FireManagerRoom(FireDatabase.getInstance(application).fireDao())

    fun getAllFires(): List<FireUi> {
        return DataSource.fires.map {
            FireUi(
                id = it.id,
                name = it.name,
                cc = it.cc,
                district = it.district,
                freguesia = it.freguesia,
                concelho = it.concelho,
                status = it.status,
                aereos = it.aereos,
                operacionais = it.operacionais,
                veiculos = it.veiculos,
                observacoes = it.observacoes,
                timestamp = it.timestamp,
                picture = null
            )
        }.sortedByDescending { it.timestamp }.toList()
    }

    fun onNewRegistration(name: String, cc: String, district: String, picture: Bitmap?) {
        val fire = FireUi(
            id = UUID.randomUUID().toString(),
            name = name,
            cc = cc,
            district = district,
            freguesia = "Informação não disponível",
            concelho = "Informação não disponível",
            status = "Informação não disponível",
            aereos = 0,
            operacionais = 0,
            veiculos = 0,
            observacoes = "Informação não disponível",
            timestamp = System.currentTimeMillis(),
            picture = null
        )
        model.insertFire(fire) {}
    }
}