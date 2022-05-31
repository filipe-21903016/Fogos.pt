package pt.ulusofona.deisi.cm2122.g21903016_21903361

import android.graphics.Bitmap
import android.graphics.Picture
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat

@Parcelize
class FireUi(
    val id: String,
    val name: String,
    val cc: String,
    val district: String,
    val freguesia: String,
    val concelho: String,
    val status: String,
    val timestamp: Long,
    val operacionais : Int,
    val aereos : Int,
    val veiculos : Int,
    val observacoes : String,
    val picture: Bitmap?
) : Parcelable {
    fun getDateTime(): String{
        val formatter = SimpleDateFormat("dd-MM-yyyy - hh:mm")
        return formatter.format(timestamp)
    }
}