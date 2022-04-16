package com.filipe.tomas.fogos.models
import kotlinx.parcelize.Parcelize

class Fire(
    val id: String,
    val name: String,
    val cc: String,
    val district: String,
    val freguesia: String = "",
    val concelho: String = "",
    val status: String,
    val timestamp: Long = System.currentTimeMillis(),
){
}