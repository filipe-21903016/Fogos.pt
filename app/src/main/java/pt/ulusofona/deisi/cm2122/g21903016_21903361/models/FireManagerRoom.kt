package pt.ulusofona.deisi.cm2122.g21903016_21903361.models

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.FireDao

class FireManagerRoom(private val dao: FireDao): FireManager() {
    override fun insertFire(fireUi: FireUi, onFinished: () -> Unit) {
        val fire = FireRoom(
            name = fireUi.name,
            cc = fireUi.cc,
            district = fireUi.district,
            freguesia = fireUi.freguesia,
            concelho = fireUi.concelho,
            status = fireUi.status,
            operacionais = fireUi.operacionais,
            aereos = fireUi.aereos,
            veiculos = fireUi.veiculos,
            observacoes = fireUi.observacoes
            //TODO photo bitmap conversion
        )
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(fire)
            onFinished()
        }
    }

    override fun insertFires(firesUi: List<FireUi>, onFinished: (List<FireUi>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val fires = firesUi.map { fireUi ->
                FireRoom(
                    name = fireUi.name,
                    cc = fireUi.cc,
                    district = fireUi.district,
                    freguesia = fireUi.freguesia,
                    concelho = fireUi.concelho,
                    status = fireUi.status,
                    operacionais = fireUi.operacionais,
                    aereos = fireUi.aereos,
                    veiculos = fireUi.veiculos,
                    observacoes = fireUi.observacoes
                    //TODO photo bitmap conversion
                )
            }
            dao.insertAll(fires)
            onFinished(firesUi)
        }
    }

    override fun getAllFires(onFinished: (List<FireUi>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val fires = dao.getAll()
            onFinished(
                fires.map {
                    FireUi(
                        id = it.id,
                        name = it.name,
                        cc = it.cc,
                        district = it.district,
                        freguesia = it.freguesia,
                        concelho = it.concelho,
                        status = it.status,
                        operacionais = it.operacionais,
                        aereos = it.aereos,
                        veiculos = it.veiculos,
                        observacoes = it.observacoes,
                        timestamp = it.timestamp,
                        picture = null //TODO CHANGE
                    )
                }
            )
        }
    }

}