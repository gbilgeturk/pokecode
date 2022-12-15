package com.gbilgeturk.pokemonapp.module

import com.gbilgeturk.pokemonapp.domain.usecase.GetPokemonListUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetPokemonListUseCase(get()) }

}