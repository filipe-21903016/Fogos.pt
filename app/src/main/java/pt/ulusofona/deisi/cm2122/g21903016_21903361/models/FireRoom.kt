package pt.ulusofona.deisi.cm2122.g21903016_21903361.models
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fire")
data class FireRoom(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val name: String = "Informação não disponível",
    val cc: String = "Informação não disponível",
    val district: String,
    val freguesia: String = "Informação não disponível",
    val concelho: String = "Informação não disponível",
    val status: String,
    val operacionais : Int = 0,
    val aereos : Int = 0,
    val veiculos : Int = 0,
    val observacoes : String = "Informação não disponível",
    var picture: String? = null,
    val timestamp: Long = System.currentTimeMillis()
){


}