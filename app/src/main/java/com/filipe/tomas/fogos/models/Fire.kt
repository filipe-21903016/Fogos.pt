package com.filipe.tomas.fogos.models
import kotlinx.parcelize.Parcelize
import java.util.*

class Fire(
    val name: String,
    val cc: String,
    val district: String,
    val freguesia: String = "",
    val concelho: String = "",
    val status: String,
){
    val id: String = UUID.randomUUID().toString()
    val timestamp: Long = System.currentTimeMillis()

}