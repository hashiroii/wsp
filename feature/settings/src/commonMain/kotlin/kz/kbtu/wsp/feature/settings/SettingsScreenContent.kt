package kz.kbtu.wsp.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kz.kbtu.wsp.core.ui.ThemePreference
import kz.kbtu.wsp.core.ui.icons.WspIcons
import kz.kbtu.wsp.feature.settings.resources.Res
import kz.kbtu.wsp.feature.settings.resources.settings_notifications_description
import kz.kbtu.wsp.feature.settings.resources.settings_notifications_open_button
import kz.kbtu.wsp.feature.settings.resources.settings_section_appearance
import kz.kbtu.wsp.feature.settings.resources.settings_section_notifications
import kz.kbtu.wsp.feature.settings.resources.settings_theme_dark
import kz.kbtu.wsp.feature.settings.resources.settings_theme_light
import kz.kbtu.wsp.feature.settings.resources.settings_theme_system
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreenContent(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        AppearanceSection(
            currentTheme = state.theme,
            onThemeSelected = { onIntent(SettingsIntent.SetTheme(it)) }
        )
        Spacer(Modifier.height(16.dp))
        NotificationsSection(
            onOpenSettings = { onIntent(SettingsIntent.OpenNotificationSettings) }
        )
    }
}

@Composable
private fun AppearanceSection(
    currentTheme: ThemePreference,
    onThemeSelected: (ThemePreference) -> Unit
) {
    SectionCard(
        title = stringResource(Res.string.settings_section_appearance),
        icon = WspIcons.Palette
    ) {
        val options = listOf(
            ThemePreference.System to stringResource(Res.string.settings_theme_system),
            ThemePreference.Light  to stringResource(Res.string.settings_theme_light),
            ThemePreference.Dark   to stringResource(Res.string.settings_theme_dark)
        )
        options.forEachIndexed { index, (pref, label) ->
            ThemeOptionRow(
                label = label,
                selected = currentTheme == pref,
                onClick = { onThemeSelected(pref) }
            )
            if (index < options.lastIndex) {
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
            }
        }
    }
}

@Composable
private fun ThemeOptionRow(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun NotificationsSection(onOpenSettings: () -> Unit) {
    SectionCard(
        title = stringResource(Res.string.settings_section_notifications),
        icon = WspIcons.Notifications
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            Text(
                text = stringResource(Res.string.settings_notifications_description),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = onOpenSettings,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.settings_notifications_open_button))
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    icon: ImageVector? = null,
    content: @Composable () -> Unit
) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            HorizontalDivider()
            content()
        }
    }
}