package kz.kbtu.wsp.feature.files

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

// Breadcrumb entry — one step in the navigation stack
data class FilesNavEntry(val id: String, val name: String)

enum class FileViewMode { List, Grid }

enum class FileExtType { PDF, EXCEL, WORD, POWERPOINT, ARCHIVE, UNKNOWN }

fun String.toFileExtType(): FileExtType = when (lowercase()) {
    "pdf"            -> FileExtType.PDF
    "xlsx", "xls"   -> FileExtType.EXCEL
    "doc", "docx"   -> FileExtType.WORD
    "ppt", "pptx"   -> FileExtType.POWERPOINT
    "zip", "rar", "7z" -> FileExtType.ARCHIVE
    else             -> FileExtType.UNKNOWN
}
