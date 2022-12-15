package com.gbilgeturk.pokemonapp.ui.screen.pokemonlist

import androidx.paging.PagingData
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

data class PokemonListViewState(
    val loading: Boolean = false,
    val pagedData: Flow<PagingData<Pokemon>>? = null,
    val pokemonList : List<Pokemon> ?= null
) : IViewState