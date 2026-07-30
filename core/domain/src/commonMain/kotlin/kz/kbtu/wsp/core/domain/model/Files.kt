package kz.kbtu.wsp.core.domain.model

data class FilesFolder(val id: String, val name: String)

data class FilesFile(
    val id: String,
    val name: String,
    val extension: String  // "pdf", "xlsx", "docx", etc. — empty string if unknown
)

data class FolderContents(
    val folders: List<FilesFolder> = emptyList(),
    val files: List<FilesFile> = emptyList()
) {
    val isEmpty: Boolean get() = folders.isEmpty() && files.isEmpty()
}
