package kz.kbtu.wsp.feature.settings

import kz.kbtu.wsp.core.ui.ThemePreference

sealed interface SettingsIntent {
    data class SetTheme(val preference: ThemePreference) : SettingsIntent
    data object OpenNotificationSettings : SettingsIntent
}