package com.gbilgeturk.pokemonapp.ui.screen.search

import android.widget.SearchView
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewEvent
import com.gbilgeturk.pokemonapp.ui.component.PokemonSearchBar
import com.gbilgeturk.pokemonapp.ui.screen.pokemonlist.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    navigateToDetail: (Pokemon?) -> Unit
) {
    val viewModel = koinViewModel<SearchViewModel>()


    val scaffoldState = rememberScaffoldState()
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

    val viewState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    PokemonSearchBar(
        modifier = Modifier.padding(top = 15.dp),
        text = viewState.searchText.orEmpty(),
        onTextChange = {
            viewModel.searchText(it)
        },
        onClickSearch = {
            viewModel.onTriggerEvent(SearchViewEvent.NewSearchEvent)
            keyboardController?.hide()
        }
    )
}

