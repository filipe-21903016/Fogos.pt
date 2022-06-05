package pt.ulusofona.deisi.cm2122.g21903016_21903361

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fire")
data class FireRoom(
    @PrimaryKey val id: String,
    val name: String? = null,
    val cc: String? = null,
    val district: String,
    val freguesia: String? = null,
    val concelho: String? = null,
    val status: String? = null,
    val operacionais: Int = 0,
    val aereos: Int = 0,
    val veiculos: Int = 0,
    val observacoes: String? = null,
    var picture: String?,
    val timestamp: Long,
    val updated: Long,
    val statusColor: String,
    val lng: Double,
    val lat: Double
)