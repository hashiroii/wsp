package kz.kbtu.wsp.feature.files

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.kbtu.wsp.core.ui.icons.WspIcons
import kz.kbtu.wsp.feature.files.resources.Res
import kz.kbtu.wsp.feature.files.resources.files_empty
import kz.kbtu.wsp.feature.files.resources.files_navigate_back
import kz.kbtu.wsp.feature.files.resources.files_title
import kz.kbtu.wsp.feature.files.resources.files_toggle_grid
import kz.kbtu.wsp.feature.files.resources.files_toggle_list
import org.jetbrains.compose.resources.stringResource

@Composable
fun FilesScreenContent(
    state: FilesState,
    onIntent: (FilesIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        FilesToolbar(
            title = state.currentFolderName ?: stringResource(Res.string.files_title),
            canGoBack = state.canGoBack,
            viewMode = state.viewMode,
            onBack = { onIntent(FilesIntent.NavigateBack) },
            onToggleView = { onIntent(FilesIntent.ToggleViewMode) }
        )

        if (state.backStack.isNotEmpty()) {
            FilesBreadcrumb(backStack = state.backStack)
        }

        if (state.contents.isEmpty) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(Res.string.files_empty),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            when (state.viewMode) {
                FileViewMode.List -> FilesListContent(
                    contents = state.contents,
                    onFolderClick = { onIntent(FilesIntent.OpenFolder(it)) }
                )
                FileViewMode.Grid -> FilesGridContent(
                    contents = state.contents,
                    onFolderClick = { onIntent(FilesIntent.OpenFolder(it)) }
                )
            }
        }
    }
}

// ── Toolbar ───────────────────────────────────────────────────────────────────

@Composable
private fun FilesToolbar(
    title: String,
    canGoBack: Boolean,
    viewMode: FileViewMode,
    onBack: () -> Unit,
    onToggleView: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        if (canGoBack) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = WspIcons.ArrowBack,
                    contentDescription = stringResource(Res.string.files_navigate_back)
                )
            }
        } else {
            Spacer(Modifier.width(48.dp))
        }

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
        )

        IconButton(onClick = onToggleView) {
            Icon(
                imageVector = if (viewMode == FileViewMode.List) WspIcons.GridView else WspIcons.ViewList,
                contentDescription = if (viewMode == FileViewMode.List)
                    stringResource(Res.string.files_toggle_grid)
                else
                    stringResource(Res.string.files_toggle_list)
            )
        }
    }
    HorizontalDivider()
}

// ── Breadcrumb ────────────────────────────────────────────────────────────────

@Composable
private fun FilesBreadcrumb(backStack: List<FilesNavEntry>) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = "Files",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        backStack.forEach { entry ->
            Text(
                text = " › ",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = entry.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    HorizontalDivider()
}

// ── List view ─────────────────────────────────────────────────────────────────

@Composable
private fun FilesListContent(
    contents: FolderContents,
    onFolderClick: (FilesFolder) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(contents.folders, key = { it.id }) { folder ->
            FolderListRow(folder = folder, onClick = { onFolderClick(folder) })
            HorizontalDivider(modifier = Modifier.padding(start = 56.dp))
        }
        if (contents.folders.isNotEmpty() && contents.files.isNotEmpty()) {
            item { HorizontalDivider(thickness = 4.dp, color = MaterialTheme.colorScheme.surfaceVariant) }
        }
        items(contents.files, key = { it.id }) { file ->
            FileListRow(file = file)
            HorizontalDivider(modifier = Modifier.padding(start = 56.dp))
        }
    }
}

@Composable
private fun FolderListRow(folder: FilesFolder, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = WspIcons.Folder,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = folder.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = WspIcons.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
private fun FileListRow(file: FilesFile) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        FileExtBadge(extension = file.extension, compact = false)
        Spacer(Modifier.width(16.dp))
        Text(
            text = file.name,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}

// ── Grid view ─────────────────────────────────────────────────────────────────

@Composable
private fun FilesGridContent(
    contents: FolderContents,
    onFolderClick: (FilesFolder) -> Unit
) {
    val allItems: List<Any> = contents.folders + contents.files

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(contents.folders, key = { it.id }) { folder ->
            FolderGridCard(folder = folder, onClick = { onFolderClick(folder) })
        }
        items(contents.files, key = { it.id }) { file ->
            FileGridCard(file = file)
        }
    }
}

@Composable
private fun FolderGridCard(folder: FilesFolder, onClick: () -> Unit) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Icon(
                imageVector = WspIcons.Folder,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(40.dp)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = folder.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun FileGridCard(file: FilesFile) {
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            FileExtBadge(extension = file.extension, compact = false, large = true)
            Spacer(Modifier.height(8.dp))
            Text(
                text = file.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// ── File extension badge ──────────────────────────────────────────────────────

@Composable
private fun FileExtBadge(
    extension: String,
    compact: Boolean,
    large: Boolean = false
) {
    val extType = extension.toFileExtType()
    val (bgColor, label) = when (extType) {
        FileExtType.PDF        -> Color(0xFFE53935) to "PDF"
        FileExtType.EXCEL      -> Color(0xFF43A047) to "XLS"
        FileExtType.WORD       -> Color(0xFF1E88E5) to "DOC"
        FileExtType.POWERPOINT -> Color(0xFFFF7043) to "PPT"
        FileExtType.ARCHIVE    -> Color(0xFF8E24AA) to "ZIP"
        FileExtType.UNKNOWN    -> Color(0xFF757575) to extension.uppercase().take(4).ifEmpty { "FILE" }
    }

    val size = if (large) 48.dp else 40.dp
    val fontSize = if (large) 11.sp else 9.sp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(6.dp))
            .background(bgColor)
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}