package com.filipe.tomas.fogos.viewmodels

import androidx.lifecycle.ViewModel
import com.filipe.tomas.fogos.data.DataSource
import com.filipe.tomas.fogos.FireUi
import com.filipe.tomas.fogos.models.Fire

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
                timestamp = it.timestamp
            )
        }.toList()
    }

    fun onNewRegistration(name: String, cc:String, district:String){
        DataSource.addNewFire(
            Fire(
                name = name,
                cc = cc,
                district = district,
                status = "Por Confirmar"
                // todo add photo
            )
        )
    }
}