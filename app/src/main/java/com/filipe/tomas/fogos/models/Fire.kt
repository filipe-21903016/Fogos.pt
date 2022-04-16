package com.filipe.tomas.fogos.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Fire(
    val id: String,
    val name: String,
    val cc: String,
    val district: String,
    val status: String,
    val timestamp: Long = System.currentTimeMillis(),
) : Parcelable {

}