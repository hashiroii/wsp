package kz.kbtu.wsp.feature.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kz.kbtu.wsp.core.ui.theme.WspTheme
import kz.kbtu.wsp.feature.schedule.resources.Res
import kz.kbtu.wsp.feature.schedule.resources.tab_exam
import kz.kbtu.wsp.feature.schedule.resources.tab_student
import kz.kbtu.wsp.feature.schedule.resources.tab_subject
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val ScheduleTab.labelRes: StringResource
    get() = when (this) {
        ScheduleTab.Student -> Res.string.tab_student
        ScheduleTab.Exam -> Res.string.tab_exam
        ScheduleTab.Subject -> Res.string.tab_subject
    }

@Composable
fun ScheduleScreenContent(
    state: ScheduleState,
    onIntent: (ScheduleIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = state.activeTab.ordinal,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[state.activeTab.ordinal]),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        ) {
            ScheduleTab.entries.forEach { tab ->
                Tab(
                    selected = state.activeTab == tab,
                    onClick = { onIntent(ScheduleIntent.SelectTab(tab)) },
                    text = {
                        Text(
                            text = stringResource(tab.labelRes),
                            fontWeight = if (state.activeTab == tab) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedContentColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                )
            }
        }

        when (state.activeTab) {
            ScheduleTab.Student -> StudentScheduleContent()
            ScheduleTab.Exam -> ExamScheduleContent()
            ScheduleTab.Subject -> SubjectScheduleContent()
        }
    }
}

@Composable
private fun StudentScheduleContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Student schedule — coming soon")
    }
}

@Composable
private fun ExamScheduleContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Exam schedule — coming soon")
    }
}

@Composable
private fun SubjectScheduleContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize().padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text("Subject schedule — coming soon")
    }
}

@Preview
@Composable
private fun ScheduleScreenContentPreview() {
    WspTheme {
        ScheduleScreenContent(
            state = ScheduleState(),
            onIntent = {}
        )
    }
}