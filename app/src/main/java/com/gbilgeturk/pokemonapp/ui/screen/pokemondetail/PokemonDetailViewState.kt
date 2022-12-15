package com.gbilgeturk.pokemonapp.ui.screen.pokemondetail

import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewState

data class PokemonDetailViewState(
    val isLoading: Boolean = false,
    val data: Pokemon? = null
) : IViewState