package kz.kbtu.wsp.feature.settings

import kz.kbtu.wsp.core.ui.ThemePreference

data class SettingsState(
    val theme: ThemePreference = ThemePreference.System
)