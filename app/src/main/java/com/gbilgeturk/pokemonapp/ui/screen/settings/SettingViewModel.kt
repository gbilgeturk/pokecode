package com.gbilgeturk.pokemonapp.ui.screen.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
import com.gbilgeturk.pokemonapp.core.PokeCodeApplication
import com.gbilgeturk.pokemonapp.core.base.BaseViewModel
import com.gbilgeturk.pokemonapp.domain.viewstate.IViewEvent
import kotlinx.coroutines.launch

class SettingViewModel(
    private val application: PokeCodeApplication
) : BaseViewModel<SettingsViewState, SettingsViewEvent>() {

    init {
        setState { currentState.copy(isDark = application.isDark.value) }
    }

    private fun onChangeTheme() {
        viewModelScope.launch {
            application.toggleTheme()
            setState { currentState.copy(isDark = application.isDark.value) }
        }
    }

    override fun onTriggerEvent(event: SettingsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SettingsViewEvent.OnChangeTheme -> onChangeTheme()
            }
        }
    }

    override fun createInitialState() = SettingsViewState()

}

sealed class SettingsViewEvent : IViewEvent {
    object OnChangeTheme : SettingsViewEvent()
}