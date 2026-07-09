package kz.kbtu.wsp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import kz.kbtu.wsp.core.ui.theme.WspTheme
import kz.kbtu.wsp.feature.home.HomeScreen
import kz.kbtu.wsp.resources.Res
import kz.kbtu.wsp.resources.cd_profile
import kz.kbtu.wsp.resources.cd_settings
import kz.kbtu.wsp.resources.nav_files
import kz.kbtu.wsp.resources.nav_home
import kz.kbtu.wsp.resources.nav_schedule
import kz.kbtu.wsp.resources.top_bar_files
import kz.kbtu.wsp.resources.top_bar_home
import kz.kbtu.wsp.resources.top_bar_schedule
import kz.kbtu.wsp.ui.files.FilesScreen
import kz.kbtu.wsp.ui.schedule.ScheduleScreen
import org.jetbrains.compose.resources.stringResource

enum class BottomTab { Home, Schedule, Files }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    WspTheme {
        var selectedTab by remember { mutableStateOf(BottomTab.Home) }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = when (selectedTab) {
                                BottomTab.Home -> stringResource(Res.string.top_bar_home)
                                BottomTab.Schedule -> stringResource(Res.string.top_bar_schedule)
                                BottomTab.Files -> stringResource(Res.string.top_bar_files)
                            }
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    actions = {
                        // Language switcher — shows current code; switching implemented later
                        IconButton(onClick = {}) {
                            Text(
                                text = "EN",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Settings, contentDescription = stringResource(Res.string.cd_settings))
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.AccountCircle, contentDescription = stringResource(Res.string.cd_profile))
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Home,
                        onClick = { selectedTab = BottomTab.Home },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text(stringResource(Res.string.nav_home)) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Schedule,
                        onClick = { selectedTab = BottomTab.Schedule },
                        icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                        label = { Text(stringResource(Res.string.nav_schedule)) }
                    )
                    NavigationBarItem(
                        selected = selectedTab == BottomTab.Files,
                        onClick = { selectedTab = BottomTab.Files },
                        icon = { Icon(Icons.Default.Star, contentDescription = null) },
                        label = { Text(stringResource(Res.string.nav_files)) }
                    )
                }
            }
        ) { innerPadding ->
            when (selectedTab) {
                BottomTab.Home -> HomeScreen(modifier = Modifier.padding(innerPadding))
                BottomTab.Schedule -> ScheduleScreen(modifier = Modifier.padding(innerPadding))
                BottomTab.Files -> FilesScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}