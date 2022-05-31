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
    val district: String,
    val freguesia: String = "Informação não disponível",
    val concelho: String = "Informação não disponível",
    val status: String,
    val timestamp: Long,
    val operacionais : Int,
    val aereos : Int,
    val veiculos : Int,
    val observacoes : String = "Informação não disponível",
    val picture: Bitmap?
) : Parcelable {
    fun getDateTime(): String{
        val formatter = SimpleDateFormat("dd-MM-yyyy - hh:mm")
        return formatter.format(timestamp)
    }
}