package pt.ulusofona.deisi.cm2122.g21903016_21903361.data

import pt.ulusofona.deisi.cm2122.g21903016_21903361.models.Fire

object DataSource {
    var fires: ArrayList<Fire> = arrayListOf(
        Fire(
            "Filipe Coutinho",
            "12345678",
            "S. Magos",
            "S. Magos",
            "S. Magos",
            "Por Confirmar",
            10, 20, 30,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Lisboa",
            "Alvalade",
            "S. Magos",
            "Por Confirmar",
            1, 1, 1,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Porto",
            "Gaia",
            "S. Magos",
            "Por Confirmar",
            observacoes = "Fogo urgente venham"
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Lisboa",
            "VF Xira",
            "S. Magos",
            "Por Confirmar",
            3, 4, 5,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Lisboa",
            "Marvila",
            "S. Magos",
            "Por Confirmar",
            1, 2, 3,
            "Fogo urgente venham "
        ),
        Fire(
            "Filipe Coutinho",
            "12345678",
            "Porto",
            "Vizela",
            "S. Magos",
            "Por Confirmar",
            8, 9, 10,
            "Fogo urgente venham "
        ),
    )

    fun addNewFire(fire: Fire) {
        fires.add(fire)
    }
}