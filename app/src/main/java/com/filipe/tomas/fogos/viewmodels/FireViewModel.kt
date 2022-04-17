package com.filipe.tomas.fogos.viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import androidx.lifecycle.ViewModel
import com.filipe.tomas.fogos.data.DataSource
import com.filipe.tomas.fogos.FireUi
import com.filipe.tomas.fogos.models.Fire
import java.io.ByteArrayOutputStream

class FireViewModel : ViewModel() {
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
                pictureBitmap = it.picture?.let { it1 ->
                    BitmapFactory.decodeByteArray(it.picture,0,
                        it1.size)
                }
            )
        }.sortedByDescending { it.timestamp }.toList()
    }

    fun onNewRegistration(name: String, cc:String, district:String, picture: Bitmap?){
        val fire = Fire(
            name = name,
            cc = cc,
            district = district,
            status = "Por Confirmar",
        )
        if (picture != null){
            val blob : ByteArrayOutputStream = ByteArrayOutputStream()
            picture.compress(Bitmap.CompressFormat.PNG, 0, blob)
            fire.picture = blob.toByteArray()
        }
        DataSource.addNewFire(fire)
    }
}