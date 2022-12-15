package com.gbilgeturk.pokemonapp.ui.screen.settings

import com.gbilgeturk.pokemonapp.domain.viewstate.IViewState

data class SettingsViewState(
    val isDark: Boolean = false,
) : IViewState