package com.gbilgeturk.pokemonapp.data.model

import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>
)