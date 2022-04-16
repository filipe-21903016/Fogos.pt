package com.filipe.tomas.fogos.data

import com.filipe.tomas.fogos.models.Fire

object DataSource {
    var fires: ArrayList<Fire> = arrayListOf(
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            10, 20, 30,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            1, 1, 1,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            observacoes = "Fogo urgente venham"
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            3, 4, 5,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            1, 2, 3,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Salvaterra de Magos",
            "Por Confirmar",
            8, 9, 10,
            "Fogo urgente venham "
        ),
    )

    fun addNewFire(fire: Fire) {
        fires.add(fire)
    }
}