package com.gbilgeturk.pokemonapp.ui.screen.search

import android.os.Parcelable
import androidx.paging.PagingData
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

data class SearchViewState(
    val searchText: String? = null,
    val pagedData: Flow<PagingData<Pokemon>>? = null,

    val isLoading: Boolean = false
) : IViewState
