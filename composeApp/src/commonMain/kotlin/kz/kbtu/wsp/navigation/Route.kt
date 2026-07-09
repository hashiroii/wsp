package kz.kbtu.wsp.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object Home : Route
    @Serializable data object Schedule : Route
    @Serializable data object Files : Route
    @Serializable data object Settings : Route
    @Serializable data object Profile : Route
    @Serializable data object Financial : Route
    @Serializable data object Attestation : Route
    @Serializable data object Transcript : Route
    @Serializable data object FxRegistration : Route
    @Serializable data object CourseRegistration : Route
    @Serializable data object AddDrop : Route
}