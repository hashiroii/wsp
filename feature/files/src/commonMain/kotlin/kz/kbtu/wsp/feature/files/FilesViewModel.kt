 package kz.kbtu.wsp.feature.files

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FilesViewModel : ViewModel() {

    private val _state = MutableStateFlow(
        FilesState(isLoading = false, contents = mockContents(null))
    )
    val state: StateFlow<FilesState> = _state.asStateFlow()

    fun onIntent(intent: FilesIntent) {
        when (intent) {
            is FilesIntent.OpenFolder -> {
                val entry = FilesNavEntry(intent.folder.id, intent.folder.name)
                _state.update {
                    it.copy(
                        backStack = it.backStack + entry,
                        contents = mockContents(intent.folder.id)
                    )
                }
            }
            FilesIntent.NavigateBack -> {
                val newStack = _state.value.backStack.dropLast(1)
                _state.update {
                    it.copy(
                        backStack = newStack,
                        contents = mockContents(newStack.lastOrNull()?.id)
                    )
                }
            }
            FilesIntent.ToggleViewMode -> {
                _state.update {
                    it.copy(viewMode = if (it.viewMode == FileViewMode.List) FileViewMode.Grid else FileViewMode.List)
                }
            }
        }
    }
}

// ── Mock data ─────────────────────────────────────────────────────────────────

private val MOCK_SCHOOLS = listOf(
    FilesFolder("school_business",  "Business School"),
    FilesFolder("school_math",      "School of Applied Mathematics"),
    FilesFolder("school_it",        "School of Information Technology and Engineering"),
    FilesFolder("school_materials", "School of Materials Science and Green Technologies"),
    FilesFolder("school_social",    "School of Social Sciences")
)

// Professors are defined once — they can appear in multiple schools
private val PROF_ABYLKHOZHIN  = FilesFolder("prof_abylkhozhin",   "Abylkhozhin Zhulduzbek Bekmuhamedovich")
private val PROF_CHALOVA      = FilesFolder("prof_chalova",        "Chalova Aida")
private val PROF_KOBZHANOVA   = FilesFolder("prof_kobzhanova",     "Kobzhanova Ainura")
private val PROF_MOLDAGALIYEVA = FilesFolder("prof_moldagaliyeva", "Moldagaliyeva Aizhan")
private val PROF_OSPANOV      = FilesFolder("prof_ospanov",        "Ospanov Tauyekel")
private val PROF_RYSKILDINOVA = FilesFolder("prof_ryskildinova",   "Ryskildinova Dinara")

private fun mockContents(folderId: String?): FolderContents = when (folderId) {

    // Root — five schools
    null -> FolderContents(folders = MOCK_SCHOOLS)

    // Schools → their professors
    "school_business"  -> FolderContents(folders = listOf(PROF_CHALOVA, PROF_RYSKILDINOVA))
    "school_math"      -> FolderContents(folders = listOf(PROF_ABYLKHOZHIN, PROF_MOLDAGALIYEVA, PROF_OSPANOV))
    "school_it"        -> FolderContents(folders = listOf(PROF_ABYLKHOZHIN, PROF_CHALOVA, PROF_KOBZHANOVA))
    "school_materials" -> FolderContents(folders = listOf(PROF_KOBZHANOVA, PROF_OSPANOV))
    "school_social"    -> FolderContents(folders = listOf(PROF_MOLDAGALIYEVA, PROF_RYSKILDINOVA))

    // Abylkhozhin — normal case: has subject folders
    "prof_abylkhozhin" -> FolderContents(
        folders = listOf(
            FilesFolder("subj_calculus", "Calculus 2 FIT"),
            FilesFolder("subj_linalg",   "Linear Algebra for engineers")
        )
    )

    // Chalova — rare case: files appear directly without a subject layer
    "prof_chalova" -> FolderContents(
        folders = listOf(
            FilesFolder("chalova_assignments", "Assignments"),
            FilesFolder("chalova_materials",   "Course Materials (Books, etc.)"),
            FilesFolder("chalova_exams",       "Exams")
        ),
        files = listOf(FilesFile("chalova_syllabus", "Syllabus.pdf", "pdf"))
    )

    "prof_kobzhanova" -> FolderContents(
        folders = listOf(
            FilesFolder("subj_discrete",    "Discrete Mathematics"),
            FilesFolder("subj_algorithms",  "Algorithms and Data Structures")
        )
    )

    "prof_moldagaliyeva" -> FolderContents(
        folders = listOf(
            FilesFolder("mold_assignments", "Assignments"),
            FilesFolder("mold_exams",       "Exams")
        ),
        files = listOf(
            FilesFile("mold_syllabus", "Syllabus Spring 2026.pdf", "pdf"),
            FilesFile("mold_slides",   "Slides Week 1.pptx", "pptx")
        )
    )

    "prof_ospanov" -> FolderContents(
        folders = listOf(
            FilesFolder("subj_physics", "Physics for Engineers"),
            FilesFolder("subj_thermo",  "Thermodynamics")
        )
    )

    "prof_ryskildinova" -> FolderContents(
        folders = listOf(
            FilesFolder("rys_materials", "Course Materials"),
            FilesFolder("rys_exams",     "Exams")
        ),
        files = listOf(FilesFile("rys_schedule", "Class Schedule.xlsx", "xlsx"))
    )

    // Subject level — category folders + individual files
    "subj_calculus" -> FolderContents(
        folders = listOf(
            FilesFolder("calc_assignments", "Assignments"),
            FilesFolder("calc_materials",   "Course Materials (Books, etc.)"),
            FilesFolder("calc_exams",       "Exams"),
            FilesFolder("calc_learning",    "Learning resource"),
            FilesFolder("calc_lecture",     "Lecture support material"),
            FilesFolder("calc_syllabus",    "Syllabus")
        ),
        files = listOf(FilesFile("calc_book", "Книга stver.xlsx", "xlsx"))
    )

    "subj_linalg" -> FolderContents(
        folders = listOf(
            FilesFolder("la_assignments", "Assignments"),
            FilesFolder("la_exams",       "Exams"),
            FilesFolder("la_syllabus",    "Syllabus"),
            FilesFolder("la_lecture",     "Lecture support material")
        )
    )

    "subj_discrete" -> FolderContents(
        folders = listOf(
            FilesFolder("disc_assignments", "Assignments"),
            FilesFolder("disc_exams",       "Exams")
        ),
        files = listOf(
            FilesFile("disc_notes", "Lecture Notes.pdf",      "pdf"),
            FilesFile("disc_book",  "Discrete Math Book.pdf", "pdf")
        )
    )

    "subj_algorithms" -> FolderContents(
        folders = listOf(
            FilesFolder("algo_assignments", "Assignments"),
            FilesFolder("algo_materials",   "Course Materials")
        )
    )

    "subj_physics" -> FolderContents(
        folders = listOf(
            FilesFolder("phys_labs",     "Lab Reports"),
            FilesFolder("phys_lectures", "Lectures"),
            FilesFolder("phys_exams",    "Exams")
        ),
        files = listOf(FilesFile("phys_syllabus", "Physics Syllabus.pdf", "pdf"))
    )

    "subj_thermo" -> FolderContents(
        folders = listOf(
            FilesFolder("thermo_assignments", "Assignments"),
            FilesFolder("thermo_exams",       "Exams")
        )
    )

    // Leaf folders — just return sample files
    else -> FolderContents(
        files = listOf(
            FilesFile("f1", "Lecture 1.pdf",        "pdf"),
            FilesFile("f2", "Lecture 2.pdf",        "pdf"),
            FilesFile("f3", "Homework 1.docx",      "docx"),
            FilesFile("f4", "Sample Exam 2025.pdf", "pdf")
        )
    )
}
