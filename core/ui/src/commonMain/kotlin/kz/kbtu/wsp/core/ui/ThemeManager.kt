package kz.kbtu.wsp.core.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ThemeManager {
    var preference by mutableStateOf(ThemePreference.System)
}