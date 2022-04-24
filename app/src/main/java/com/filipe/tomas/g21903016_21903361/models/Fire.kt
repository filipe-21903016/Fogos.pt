package com.filipe.tomas.g21903016_21903361.models
import java.util.*

class Fire(
    val name: String = "Informação não disponível",
    val cc: String = "Informação não disponível",
    val district: String,
    val freguesia: String = "Informação não disponível",
    val concelho: String = "Informação não disponível",
    val status: String,
    val operacionais : Int = 0,
    val aereos : Int = 0,
    val veiculos : Int = 0,
    val observacoes : String = "Informação não disponível",
    var picture: ByteArray? = null
){
    val id: String = UUID.randomUUID().toString()
    val timestamp: Long = System.currentTimeMillis()

}