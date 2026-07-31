package kz.kbtu.wsp.feature.files

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.kbtu.wsp.core.domain.repository.FilesRepository

class FilesViewModel(
    private val filesRepository: FilesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FilesState())
    val state: StateFlow<FilesState> = _state.asStateFlow()

    private var loadJob: Job? = null

    init {
        loadFolder(null)
    }

    fun onIntent(intent: FilesIntent) {
        when (intent) {
            is FilesIntent.OpenFolder -> {
                val entry = FilesNavEntry(intent.folder.id, intent.folder.name)
                val newStack = _state.value.backStack + entry
                _state.update { it.copy(backStack = newStack) }
                loadFolder(intent.folder.id)
            }
            FilesIntent.NavigateBack -> {
                val newStack = _state.value.backStack.dropLast(1)
                _state.update { it.copy(backStack = newStack) }
                loadFolder(newStack.lastOrNull()?.id)
            }
            FilesIntent.ToggleViewMode -> {
                _state.update {
                    it.copy(viewMode = if (it.viewMode == FileViewMode.List) FileViewMode.Grid else FileViewMode.List)
                }
            }
        }
    }

    private fun loadFolder(folderId: String?) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            val contents = filesRepository.getFolderContents(folderId)
            _state.update { it.copy(contents = contents) }
        }
    }
}