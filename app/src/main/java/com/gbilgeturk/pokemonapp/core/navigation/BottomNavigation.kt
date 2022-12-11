package com.gbilgeturk.pokemonapp.core.navigation

import com.gbilgeturk.pokemonapp.R
import com.gbilgeturk.pokemonapp.utils.Constants

enum class BottomNavigation(
    val route: String,
    val iconId: Int,
    val screenName: String
) {
    POKEMONS(
        NavigationScreen.Pokemons.route,
        R.drawable.ic_pokemon,
        Constants.SCREEN_NAME_POKEMONS
    ),
    EPISODES(
        NavigationScreen.Favorites.route,
        R.drawable.ic_pokemon,
        Constants.SCREEN_NAME_EPISODES
    ),
    FAVORITES(
        NavigationScreen.Favorites.route,
        R.drawable.ic_pokemon,
        Constants.SCREEN_NAME_FAVORITES
    ),
    SEARCH(
        NavigationScreen.Search.route,
        R.drawable.ic_pokemon,
        Constants.SCREEN_NAME_SEARCH
    ),
    SETTINGS(
        NavigationScreen.Settings .route,
        R.drawable.ic_pokemon,
        Constants.SCREEN_NAME_SETTINGS
    )
}