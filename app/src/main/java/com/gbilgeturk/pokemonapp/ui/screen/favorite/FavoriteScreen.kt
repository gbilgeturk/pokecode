package com.gbilgeturk.pokemonapp.ui.screen.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.gbilgeturk.pokemonapp.data.model.dto.PokemonDto

@Composable
fun FavoriteScreen(
    navigateCharacterDetail: (PokemonDto) -> Unit
) {
    Text(text = "favorite")
}