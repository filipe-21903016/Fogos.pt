package pt.ulusofona.deisi.cm2122.g21903016_21903361.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fire")
data class FireRoom(
    @PrimaryKey val id: String,
    val name: String,
    val cc: String,
    val district: String,
    val freguesia: String,
    val concelho: String,
    val status: String,
    val operacionais : Int = 0,
    val aereos : Int = 0,
    val veiculos : Int = 0,
    val observacoes : String,
    var picture: String?,
    val timestamp: Long,
    val lng: Double,
    val lat: Double
)