package com.gbilgeturk.pokemonapp.ui.screen.search

import com.gbilgeturk.pokemonapp.core.base.BaseViewModel
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewEvent

class SearchViewModel(): BaseViewModel<SearchViewState, SearchViewEvent>() {
    override fun createInitialState()= SearchViewState()

    override fun onTriggerEvent(event: SearchViewEvent) {

    }

    fun searchText(value: String?) {
        setState { currentState.copy(searchText = value) }
    }
}

sealed class SearchViewEvent : IViewEvent {
    object NewSearchEvent : SearchViewEvent()
    class UpdateFavorite(val dto: Pokemon) : SearchViewEvent()
}