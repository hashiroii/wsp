package kz.kbtu.wsp.feature.files

import kz.kbtu.wsp.core.domain.model.FilesFolder

sealed interface FilesIntent {
    data class OpenFolder(val folder: FilesFolder) : FilesIntent
    data object NavigateBack : FilesIntent
    data object ToggleViewMode : FilesIntent
}