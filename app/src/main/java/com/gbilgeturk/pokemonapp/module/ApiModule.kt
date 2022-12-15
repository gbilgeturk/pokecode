package com.gbilgeturk.pokemonapp.module

import com.gbilgeturk.pokemonapp.service.api.PokemonApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun providePokemonApi(retrofit: Retrofit) : PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    single { providePokemonApi(get()) }
}