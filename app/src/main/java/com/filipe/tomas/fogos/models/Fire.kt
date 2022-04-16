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
    val operacionais : Int = 0,
    val aereos : Int = 0,
    val veiculos : Int = 0,
    val observacoes : String = ""
){
    val id: String = UUID.randomUUID().toString()
    val timestamp: Long = System.currentTimeMillis()

}