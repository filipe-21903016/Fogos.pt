package com.filipe.tomas.fogos.viewmodels

import androidx.lifecycle.ViewModel
import com.filipe.tomas.fogos.data.DataSource
import com.filipe.tomas.fogos.views.FireUi

class FireViewModel : ViewModel() {
    fun getAllFires() : List<FireUi> {
        return DataSource.fires.map {
            FireUi(
                id = it.id,
                name = it.name,
                cc = it.cc,
                district = it.district,
                freguesia = it.freguesia,
                concelho = it.concelho,
                status = it.status,
                timestamp = it.timestamp
            )
        }.toList()
    }
}