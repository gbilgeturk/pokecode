package com.gbilgeturk.pokemonapp.ui.screen.pokemondetail

import androidx.lifecycle.SavedStateHandle
import com.gbilgeturk.pokemonapp.core.base.BaseViewModel
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewEvent

class PokemonDetailViewModel(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PokemonDetailViewState, PokemonDetailViewEvent>(){


    init {
        savedStateHandle.get<String>("characterDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Pokemon(name = it)) }
        } ?: kotlin.run {
            setEvent(PokemonDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() =  PokemonDetailViewState()

    override fun onTriggerEvent(event: PokemonDetailViewEvent) {

    }
}

sealed class PokemonDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : PokemonDetailViewEvent()
}