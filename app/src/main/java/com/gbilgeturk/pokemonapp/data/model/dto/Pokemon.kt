package com.gbilgeturk.pokemonapp.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pokemon(
    var page: Int = 0,
    val name: String ?= null,
    val url: String ?= null
) : Parcelable {

    fun getImageUrl(): String {
        val index = url?.split("/".toRegex())?.dropLast(1)?.last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}