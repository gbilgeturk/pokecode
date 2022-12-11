package com.gbilgeturk.pokemonapp.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gbilgeturk.pokemonapp.ui.component.PokemonBottomAppBar
import com.gbilgeturk.pokemonapp.ui.component.PokemonFloatingActionBar
import com.gbilgeturk.pokemonapp.ui.component.PokemonScaffold
import com.gbilgeturk.pokemonapp.ui.screen.favorite.FavoriteScreen
import com.gbilgeturk.pokemonapp.ui.screen.pokemondetail.PokemonDetailScreen
import com.gbilgeturk.pokemonapp.ui.screen.pokemonlist.PokemonListScreen
import com.gbilgeturk.pokemonapp.ui.screen.search.SearchScreen
import com.gbilgeturk.pokemonapp.ui.screen.settings.SettingsScreen
import com.gbilgeturk.pokemonapp.utils.Utility.toJson
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(startDestination: String = NavigationScreen.Pokemons.route) {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    PokemonScaffold(
        bottomBar = {
            BottomNavigation.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    PokemonBottomAppBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        },
        floatingActionButton = {
            BottomNavigation.values().forEach { navItem ->
                if (navItem.route == currentRoute) {
                    PokemonFloatingActionBar(
                        navController = navController,
                    )
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background,
    ) {innerPadding ->
        AnimatedNavHost(
            navController = navController,
            startDestination = startDestination,
            Modifier.padding(innerPadding)
        ) {
            composable(NavigationScreen.Pokemons.route) {
                PokemonListScreen(
                    navigateToDetail = {
                        navController.navigate(NavigationScreen.PokemonDetail.route.plus("?characterDetail=${it.toJson()}"))
                    }
                )
            }

            composable(NavigationScreen.Episodes.route) {

            }
            composable(
                NavigationScreen.PokemonDetail.route.plus("?characterDetail={characterDetail}"),
                content = {
                    PokemonDetailScreen(
                        navigateToBack = {
                            navController.popBackStack()
                        }
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            )



            composable(NavigationScreen.Search.route) {
                SearchScreen(
                    navigateToDetail = {
                        navController.navigate(NavigationScreen.PokemonDetail.route.plus("?characterDetail=${it.toJson()}"))
                    }
                )
            }

            composable(NavigationScreen.Settings.route) {
                SettingsScreen(
                )
            }

            composable(
                NavigationScreen.Favorites.route,
                content = {
                    FavoriteScreen(
                        navigateCharacterDetail = {
                            navController.navigate(NavigationScreen.PokemonDetail.route.plus("?characterDetail=${it.toJson()}"))
                        }
                    )
                },
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                popExitTransition = {
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                }
            )
        }

    }
}