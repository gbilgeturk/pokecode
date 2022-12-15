package com.gbilgeturk.pokemonapp.ui.screen.favorite

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.gbilgeturk.pokemonapp.data.model.dto.Pokemon

@Composable
fun FavoriteScreen(
    navigateCharacterDetail: (Pokemon) -> Unit
) {
    Text(text = "favorite")
}