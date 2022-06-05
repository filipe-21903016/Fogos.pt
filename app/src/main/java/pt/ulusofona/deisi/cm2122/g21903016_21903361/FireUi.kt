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
    val name: String? = null,
    val cc: String? = null,
    val district: String,
    val freguesia: String? = null,
    val concelho: String? = null,
    val status: String? = null,
    val timestamp: Long,
    val updated: Long,
    val operacionais: Int = 0,
    val aereos: Int = 0,
    val veiculos: Int = 0,
    val observacoes: String? = null,
    val statusColor: String = "848484",
    val picture: ByteArray? = null,
    val lat:Double,
    val lng:Double
) : Parcelable {
    fun getDateTime(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return formatter.format(timestamp)
    }

    fun lastUpdatedTime(): String{
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm")
        return formatter.format(updated)
    }

    override fun toString(): String {
        return id
    }

    fun missingInfoString() = "Informação não disponível"
}