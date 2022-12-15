package com.gbilgeturk.pokemonapp.module

import com.gbilgeturk.pokemonapp.service.ApiFactory
import org.koin.dsl.module

val networkModule = module {
    single { ApiFactory.provideHttpClient() }
    single { ApiFactory.provideRetrofit(get()) }
}