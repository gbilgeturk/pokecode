package com.gbilgeturk.pokemonapp.core

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.gbilgeturk.pokemonapp.module.apiModule
import com.gbilgeturk.pokemonapp.module.networkModule
import com.gbilgeturk.pokemonapp.module.repositoryModule
import com.gbilgeturk.pokemonapp.module.useCaseModule
import com.gbilgeturk.pokemonapp.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PokeCodeApplication: Application() {

    val isDark = mutableStateOf(false)

    fun toggleTheme() {
        isDark.value = !isDark.value
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()

    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@PokeCodeApplication)
            koin.loadModules(koinModules)
        }
    }

    private val koinModules =
        listOf(viewModelModule, networkModule, repositoryModule, apiModule, useCaseModule)
}