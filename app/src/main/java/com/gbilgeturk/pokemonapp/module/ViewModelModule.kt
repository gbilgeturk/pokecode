package com.gbilgeturk.pokemonapp.module

import com.gbilgeturk.pokemonapp.core.PokeCodeApplication
import com.gbilgeturk.pokemonapp.ui.screen.pokemondetail.PokemonDetailViewModel
import com.gbilgeturk.pokemonapp.ui.screen.pokemonlist.PokemonListViewModel
import com.gbilgeturk.pokemonapp.ui.screen.settings.SettingViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { SettingViewModel(application = androidApplication() as PokeCodeApplication) }
    viewModel { PokemonListViewModel(get())}
    viewModel { PokemonDetailViewModel(get())}

}