package com.filipe.tomas.fogos.views

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
) : Parcelable {
    fun getDateTime(): String{
        val formatter = SimpleDateFormat("dd/MM/yyyy - hh:mm:ss")
        return formatter.format(timestamp)
    }
}