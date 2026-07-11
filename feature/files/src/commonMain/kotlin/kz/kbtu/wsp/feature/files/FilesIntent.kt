package kz.kbtu.wsp.feature.files

sealed interface FilesIntent {
    data class OpenFolder(val folder: FilesFolder) : FilesIntent
    data object NavigateBack : FilesIntent
    data object ToggleViewMode : FilesIntent
}