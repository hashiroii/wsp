package kz.kbtu.wsp.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Navy30,
    onPrimary = Grey99,
    primaryContainer = Navy90,
    onPrimaryContainer = Navy10,
    secondary = Teal40,
    onSecondary = Grey99,
    secondaryContainer = Teal90,
    onSecondaryContainer = Teal10,
    tertiary = Amber40,
    onTertiary = Grey99,
    tertiaryContainer = Amber90,
    onTertiaryContainer = Amber10,
    error = Red40,
    onError = Grey99,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey99,
    onBackground = Grey10,
    surface = Grey99,
    onSurface = Grey10,
    surfaceVariant = BlueGrey90,
    onSurfaceVariant = BlueGrey30,
    outline = BlueGrey50,
    outlineVariant = BlueGrey80,
)

private val DarkColorScheme = darkColorScheme(
    primary = Navy80,
    onPrimary = Navy20,
    primaryContainer = Navy30,
    onPrimaryContainer = Navy90,
    secondary = Teal80,
    onSecondary = Teal20,
    secondaryContainer = Teal30,
    onSecondaryContainer = Teal90,
    tertiary = Amber80,
    onTertiary = Amber20,
    tertiaryContainer = Amber10,
    onTertiaryContainer = Amber90,
    error = Red80,
    onError = Red10,
    errorContainer = Red40,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Grey90,
    surface = Grey10,
    onSurface = Grey90,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey50,
    outlineVariant = BlueGrey30,
)

@Composable
fun WspTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}