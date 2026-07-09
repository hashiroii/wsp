package kz.kbtu.wsp

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kz.kbtu.wsp.core.ui.icons.WspIcons
import kz.kbtu.wsp.core.ui.theme.WspTheme
import kz.kbtu.wsp.feature.files.FilesScreen
import kz.kbtu.wsp.feature.financial.FinancialScreen
import kz.kbtu.wsp.feature.grades.AttestationScreen
import kz.kbtu.wsp.feature.grades.TranscriptScreen
import kz.kbtu.wsp.feature.home.HomeIntent
import kz.kbtu.wsp.feature.home.HomeScreen
import kz.kbtu.wsp.feature.profile.ProfileScreen
import kz.kbtu.wsp.feature.registration.AddDropScreen
import kz.kbtu.wsp.feature.registration.CourseRegistrationScreen
import kz.kbtu.wsp.feature.registration.FxRegistrationScreen
import kz.kbtu.wsp.feature.schedule.ScheduleScreen
import kz.kbtu.wsp.feature.settings.SettingsScreen
import kz.kbtu.wsp.navigation.Route
import kz.kbtu.wsp.resources.Res
import kz.kbtu.wsp.resources.cd_language
import kz.kbtu.wsp.resources.cd_profile
import kz.kbtu.wsp.resources.cd_settings
import kz.kbtu.wsp.resources.nav_files
import kz.kbtu.wsp.resources.nav_home
import kz.kbtu.wsp.resources.nav_schedule
import kz.kbtu.wsp.resources.top_bar_files
import kz.kbtu.wsp.resources.top_bar_home
import kz.kbtu.wsp.resources.top_bar_schedule
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    WspTheme {
        val navController = rememberNavController()
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDest = backStackEntry?.destination

        val title = when {
            currentDest?.hasRoute(Route.Home::class) == true -> stringResource(Res.string.top_bar_home)
            currentDest?.hasRoute(Route.Schedule::class) == true -> stringResource(Res.string.top_bar_schedule)
            currentDest?.hasRoute(Route.Files::class) == true -> stringResource(Res.string.top_bar_files)
            currentDest?.hasRoute(Route.Settings::class) == true -> "Settings"
            currentDest?.hasRoute(Route.Profile::class) == true -> "Profile"
            currentDest?.hasRoute(Route.Financial::class) == true -> "Financial"
            currentDest?.hasRoute(Route.Attestation::class) == true -> "Attestation"
            currentDest?.hasRoute(Route.Transcript::class) == true -> "Transcript"
            currentDest?.hasRoute(Route.FxRegistration::class) == true -> "FX Registration"
            currentDest?.hasRoute(Route.CourseRegistration::class) == true -> "Course Registration"
            currentDest?.hasRoute(Route.AddDrop::class) == true -> "Add / Drop"
            else -> stringResource(Res.string.top_bar_home)
        }

        val showBottomBar = currentDest?.let {
            it.hasRoute(Route.Home::class) ||
            it.hasRoute(Route.Schedule::class) ||
            it.hasRoute(Route.Files::class)
        } ?: true

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    navigationIcon = {
                        if (!showBottomBar) {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(WspIcons.Language, contentDescription = stringResource(Res.string.cd_language))
                        }
                        IconButton(onClick = { navController.navigate(Route.Settings) }) {
                            Icon(Icons.Default.Settings, contentDescription = stringResource(Res.string.cd_settings))
                        }
                        IconButton(onClick = { navController.navigate(Route.Profile) }) {
                            Icon(Icons.Default.AccountCircle, contentDescription = stringResource(Res.string.cd_profile))
                        }
                    }
                )
            },
            bottomBar = {
                if (showBottomBar) {
                    NavigationBar {
                        NavigationBarItem(
                            selected = currentDest?.hasRoute(Route.Home::class) == true,
                            onClick = {
                                navController.navigate(Route.Home) {
                                    popUpTo(Route.Home) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.Home, contentDescription = null) },
                            label = { Text(stringResource(Res.string.nav_home)) }
                        )
                        NavigationBarItem(
                            selected = currentDest?.hasRoute(Route.Schedule::class) == true,
                            onClick = {
                                navController.navigate(Route.Schedule) {
                                    popUpTo(Route.Home) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                            label = { Text(stringResource(Res.string.nav_schedule)) }
                        )
                        NavigationBarItem(
                            selected = currentDest?.hasRoute(Route.Files::class) == true,
                            onClick = {
                                navController.navigate(Route.Files) {
                                    popUpTo(Route.Home) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(WspIcons.Folder, contentDescription = null) },
                            label = { Text(stringResource(Res.string.nav_files)) }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.Home,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Route.Home> {
                    HomeScreen(onNavigate = { intent ->
                        when (intent) {
                            HomeIntent.OnFinancialClick -> navController.navigate(Route.Financial)
                            HomeIntent.OnAttestationClick -> navController.navigate(Route.Attestation)
                            HomeIntent.OnTranscriptClick -> navController.navigate(Route.Transcript)
                            HomeIntent.OnFxRegistrationClick -> navController.navigate(Route.FxRegistration)
                            HomeIntent.OnCourseRegistrationClick -> navController.navigate(Route.CourseRegistration)
                            HomeIntent.OnAddDropClick -> navController.navigate(Route.AddDrop)
                            else -> {}
                        }
                    })
                }
                composable<Route.Schedule> { ScheduleScreen() }
                composable<Route.Files> { FilesScreen() }
                composable<Route.Settings> { SettingsScreen() }
                composable<Route.Profile> { ProfileScreen() }
                composable<Route.Financial> { FinancialScreen() }
                composable<Route.Attestation> { AttestationScreen() }
                composable<Route.Transcript> { TranscriptScreen() }
                composable<Route.FxRegistration> { FxRegistrationScreen() }
                composable<Route.CourseRegistration> { CourseRegistrationScreen() }
                composable<Route.AddDrop> { AddDropScreen() }
            }
        }
    }
}