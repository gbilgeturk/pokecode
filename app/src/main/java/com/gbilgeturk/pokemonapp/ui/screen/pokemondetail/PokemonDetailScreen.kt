package com.gbilgeturk.pokemonapp.ui.screen.pokemondetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.gbilgeturk.pokemonapp.ui.screen.pokemonlist.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailScreen(
    navigateToBack: () -> Unit
) {
    val viewModel = koinViewModel<PokemonDetailViewModel>()
    val viewState by viewModel.uiState.collectAsState()
    Text(text = viewState.data?.name?: "Error")
}