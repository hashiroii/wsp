package kz.kbtu.wsp.core.domain.repository

import kz.kbtu.wsp.core.domain.model.FolderContents

interface FilesRepository {
    suspend fun getFolderContents(folderId: String?): FolderContents
}