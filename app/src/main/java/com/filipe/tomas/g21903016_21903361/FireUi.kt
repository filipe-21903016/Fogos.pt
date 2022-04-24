package com.filipe.tomas.g21903016_21903361

import android.graphics.Bitmap
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
    val pictureBitmap: Bitmap?
) : Parcelable {
    fun getDateTime(): String{
        val formatter = SimpleDateFormat("dd-MM-yyyy - hh:mm")
        return formatter.format(timestamp)
    }
}