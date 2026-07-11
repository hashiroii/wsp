package kz.kbtu.wsp.feature.schedule

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.kbtu.wsp.core.ui.icons.WspIcons
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
        ScheduleTab.Exam    -> Res.string.tab_exam
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
            ScheduleTab.Student -> StudentScheduleContent(state = state, onIntent = onIntent)
            ScheduleTab.Exam    -> ExamScheduleContent(state = state, onIntent = onIntent)
            ScheduleTab.Subject -> SubjectScheduleContent()
        }
    }
}

// ── Student schedule ──────────────────────────────────────────────────────────

@Composable
private fun StudentScheduleContent(
    state: ScheduleState,
    onIntent: (ScheduleIntent) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ScheduleFilterRow(state = state, onIntent = onIntent)
        HorizontalDivider()
        DaySelector(
            selectedDay = state.selectedDay,
            onDaySelect = { onIntent(ScheduleIntent.SelectDay(it)) }
        )
        HorizontalDivider()
        val dayEntries = state.studentEntries.filter { it.day == state.selectedDay }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            items(SCHEDULE_HOURS, key = { it }) { hour ->
                val entry = dayEntries.find { it.startTime == hour }
                HourRow(hour = hour, entry = entry)
            }
        }
    }
}

// ── Exam schedule ─────────────────────────────────────────────────────────────

@Composable
private fun ExamScheduleContent(
    state: ScheduleState,
    onIntent: (ScheduleIntent) -> Unit
) {
    val examDates = state.examEntries.map { it.date }.distinct()

    Column(modifier = Modifier.fillMaxSize()) {
        ScheduleFilterRow(state = state, onIntent = onIntent)
        HorizontalDivider()

        if (examDates.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                examDates.forEach { date ->
                    FilterChip(
                        selected = date == state.selectedExamDate,
                        onClick = { onIntent(ScheduleIntent.SelectExamDate(date)) },
                        label = { Text(date) }
                    )
                }
            }
            HorizontalDivider()
        }

        val visibleExams = if (state.selectedExamDate != null)
            state.examEntries.filter { it.date == state.selectedExamDate }
        else
            state.examEntries

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(visibleExams, key = { it.id }) { exam ->
                ExamCard(exam = exam)
            }
        }
    }
}

// ── Subject schedule (stub) ───────────────────────────────────────────────────

@Composable
private fun SubjectScheduleContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Subject schedule — coming soon")
    }
}

// ── Shared filter row ─────────────────────────────────────────────────────────

@Composable
private fun ScheduleFilterRow(
    state: ScheduleState,
    onIntent: (ScheduleIntent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ScheduleDropdown(
            label = "Year",
            selected = state.selectedYear,
            displayName = { it.label },
            options = availableAcademicYears(),
            onSelect = { onIntent(ScheduleIntent.SelectYear(it)) },
            modifier = Modifier.weight(1f)
        )
        ScheduleDropdown(
            label = "Term",
            selected = state.selectedTerm,
            displayName = { it.displayName },
            options = AcademicTerm.entries,
            onSelect = { onIntent(ScheduleIntent.SelectTerm(it)) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun <T> ScheduleDropdown(
    label: String,
    selected: T,
    displayName: (T) -> String,
    options: List<T>,
    onSelect: (T) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        OutlinedCard(
            onClick = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = displayName(selected),
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Icon(
                    imageVector = WspIcons.ChevronRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(18.dp)
                        .rotate(if (expanded) 270f else 90f)
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(displayName(option)) },
                    onClick = { onSelect(option); expanded = false },
                    leadingIcon = if (option == selected) {
                        {
                            Icon(
                                WspIcons.School,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    } else null
                )
            }
        }
    }
}

// ── Day selector ──────────────────────────────────────────────────────────────

@Composable
private fun DaySelector(
    selectedDay: ScheduleDay,
    onDaySelect: (ScheduleDay) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        ScheduleDay.entries.forEach { day ->
            FilterChip(
                selected = day == selectedDay,
                onClick = { onDaySelect(day) },
                label = { Text(day.name) }
            )
        }
    }
}

// ── Hour row ──────────────────────────────────────────────────────────────────

@Composable
private fun HourRow(hour: String, entry: ClassEntry?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = hour,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .width(48.dp)
                .padding(end = 8.dp, top = 6.dp),
        )
        if (entry != null) {
            ClassCard(entry = entry, modifier = Modifier.weight(1f))
        } else {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                    thickness = 0.5.dp
                )
            }
        }
    }
}

@Composable
private fun ClassCard(entry: ClassEntry, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(containerColor = entry.type.containerColor),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Text(
                text = entry.subject,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black.copy(alpha = 0.87f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(4.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TypeLabel(type = entry.type)
                Text(
                    text = "· ${entry.room}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black.copy(alpha = 0.55f)
                )
            }
            Text(
                text = entry.professor,
                style = MaterialTheme.typography.labelSmall,
                color = entry.type.accentColor,
                modifier = Modifier.padding(top = 2.dp)
            )
        }
    }
}

@Composable
private fun TypeLabel(type: ClassType) {
    Text(
        text = type.name,
        style = MaterialTheme.typography.labelSmall,
        color = type.accentColor,
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(type.accentColor.copy(alpha = 0.15f))
            .padding(horizontal = 6.dp, vertical = 2.dp)
    )
}

// ── Exam card ─────────────────────────────────────────────────────────────────

@Composable
private fun ExamCard(exam: ExamEntry) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.35f)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = exam.subject,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = WspIcons.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = exam.room,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Spacer(Modifier.width(8.dp))
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = exam.startTime,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.error
                )
                Text(
                    text = exam.endTime,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview
@Composable
private fun ScheduleScreenContentPreview() {
    WspTheme {
        ScheduleScreenContent(
            state = ScheduleState(
                selectedYear = AcademicYear(2025),
                selectedTerm = AcademicTerm.Spring,
                selectedDay = ScheduleDay.Mon,
                studentEntries = listOf(
                    ClassEntry("p1", ScheduleDay.Mon, "08:00", "Calculus 2", ClassType.Lecture, "B3-101", "Abylkhozhin Z."),
                    ClassEntry("p2", ScheduleDay.Mon, "10:00", "Linear Algebra", ClassType.Seminar, "B2-214", "Moldagaliyeva A.")
                )
            ),
            onIntent = {}
        )
    }
}