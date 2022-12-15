package com.gbilgeturk.pokemonapp.data.model

import kotlin.random.Random

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val experience: Int,
    val types: List<TypeResponse>,
    val hp: Int = Random.nextInt(maxHp),
    val attack: Int = Random.nextInt(maxAttack),
    val defense: Int = Random.nextInt(maxDefense),
    val speed: Int = Random.nextInt(maxSpeed),
    val exp: Int = Random.nextInt(maxExp)
) {

    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = " $hp/$maxHp"
    fun getAttackString(): String = " $attack/$maxAttack"
    fun getDefenseString(): String = " $defense/$maxDefense"
    fun getSpeedString(): String = " $speed/$maxSpeed"
    fun getExpString(): String = " $exp/$maxExp"


    data class TypeResponse(
        val slot: Int,
        val type: Type
    )

    data class Type(
        val name: String
    )

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }
}