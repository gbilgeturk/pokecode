package com.gbilgeturk.pokemonapp.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.gbilgeturk.pokemonapp.core.PokeCodeApplication
import com.gbilgeturk.pokemonapp.core.navigation.NavGraph
import com.gbilgeturk.pokemonapp.ui.theme.PokemonappTheme
import org.koin.android.ext.android.inject
import org.koin.dsl.koinApplication
import org.koin.java.KoinJavaComponent.inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonappTheme(darkTheme = (LocalContext.current.applicationContext as PokeCodeApplication).isDark.value) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}