package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.graphics.Bitmap
import android.graphics.Picture
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
class FireUi(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "Informação não disponível",
    val cc: String = "Informação não disponível",
    val district: String = "Informação não disponível",
    val freguesia: String = "Informação não disponível",
    val concelho: String = "Informação não disponível",
    val status: String = "Informação não disponível",
    val timestamp: Long,
    val operacionais: Int = 0,
    val aereos: Int = 0,
    val veiculos: Int = 0,
    val observacoes: String = "Informação não disponível",
    val statusColor: String? = null,
    val picture: Bitmap? = null,
    val lat:Double,
    val lng:Double
) : Parcelable {
    fun getDateTime(): String {
        val formatter = SimpleDateFormat("dd-MM-yyyy hh:mm")
        return formatter.format(timestamp)
    }

    override fun toString(): String {
        return id
    }
}