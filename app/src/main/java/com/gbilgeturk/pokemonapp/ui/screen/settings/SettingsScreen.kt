package com.gbilgeturk.pokemonapp.ui.screen.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SettingsScreen() {
    val viewModel = koinViewModel<SettingViewModel>()
    val viewState by viewModel.uiState.collectAsState()


    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Theme")
        Switch(checked = viewState.isDark, onCheckedChange = {
            viewModel.onTriggerEvent(SettingsViewEvent.OnChangeTheme)
        })
    }

}