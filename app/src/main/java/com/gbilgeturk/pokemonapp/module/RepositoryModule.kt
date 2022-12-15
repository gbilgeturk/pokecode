package com.gbilgeturk.pokemonapp.module

import android.content.Context
import com.gbilgeturk.pokemonapp.data.repo.PokemonRepo
import com.gbilgeturk.pokemonapp.data.repo.PokemonRepoImpl
import com.gbilgeturk.pokemonapp.service.api.PokemonApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun providePokemonRepository(
        context: Context,
        api: PokemonApi
    ): PokemonRepo {
        return PokemonRepoImpl(context, api)
    }

    single { providePokemonRepository(androidContext(), get()) }

}