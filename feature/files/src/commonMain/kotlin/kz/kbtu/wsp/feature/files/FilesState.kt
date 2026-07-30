package kz.kbtu.wsp.feature.files

import kz.kbtu.wsp.core.domain.model.FolderContents

data class FilesState(
    // Stack of folders the user has opened. Empty = root (schools list).
    val backStack: List<FilesNavEntry> = emptyList(),
    val contents: FolderContents = FolderContents(),
    val viewMode: FileViewMode = FileViewMode.List
) {
    val currentFolderName: String? get() = backStack.lastOrNull()?.name
    val canGoBack: Boolean get() = backStack.isNotEmpty()
}