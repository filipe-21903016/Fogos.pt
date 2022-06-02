package pt.ulusofona.deisi.cm2122.g21903016_21903361.models

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21903016_21903361.FireUi
import pt.ulusofona.deisi.cm2122.g21903016_21903361.interfaces.FireDao

class FireManagerRoom(private val dao: FireDao) : FireManager() {
    override fun insertFire(fireUi: FireUi, onFinished: () -> Unit) {
        val fire = FireRoom(
            id = fireUi.id,
            name = fireUi.name,
            cc = fireUi.cc,
            district = fireUi.district,
            freguesia = fireUi.freguesia,
            concelho = fireUi.concelho,
            status = fireUi.status,
            operacionais = fireUi.operacionais,
            aereos = fireUi.aereos,
            veiculos = fireUi.veiculos,
            observacoes = fireUi.observacoes,
            timestamp = fireUi.timestamp,
            picture = null,
            //TODO photo bitmap conversion
            lat = fireUi.lat,
            lng = fireUi.lng,
            statusColor = fireUi.statusColor
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
                    id = fireUi.id,
                    name = fireUi.name,
                    cc = fireUi.cc,
                    district = fireUi.district,
                    freguesia = fireUi.freguesia,
                    concelho = fireUi.concelho,
                    status = fireUi.status,
                    operacionais = fireUi.operacionais,
                    aereos = fireUi.aereos,
                    veiculos = fireUi.veiculos,
                    observacoes = fireUi.observacoes,
                    picture = null, //TODO photo bitmap conversion
                    timestamp = fireUi.timestamp,
                    lat = fireUi.lat,
                    lng = fireUi.lng,
                    statusColor = fireUi.statusColor
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
                        statusColor = it.statusColor,
                        picture = null, //TODO CHANGE
                        lat = it.lat,
                        lng = it.lng
                    )
                }
            )
        }
    }

    override fun deleteAllFires(onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
            onFinished()
        }
    }

    override fun getRiskForDistrict(district: String, onFinished: (String) -> Unit) {
        throw NotImplementedError()
    }


}