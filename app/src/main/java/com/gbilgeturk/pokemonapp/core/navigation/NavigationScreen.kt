package com.gbilgeturk.pokemonapp.core.navigation

sealed class NavigationScreen(val route: String) {
    object Settings : NavigationScreen("settings")
    object Pokemons : NavigationScreen("pokemons")
    object PokemonDetail : NavigationScreen("pokemon_detail")
    object Favorites : NavigationScreen("favorites")
    object Search : NavigationScreen("search")
    object Episodes : NavigationScreen("episodes")
}