package kz.kbtu.wsp.feature.grades

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AttestationScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(MOCK_ATTESTATION) { semester ->
            SemesterCard(semester)
        }
    }
}

@Composable
private fun SemesterCard(semester: SemesterRecord) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = semester.label,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)
            )
            HorizontalDivider()
            semester.entries.forEachIndexed { index, entry ->
                CourseRow(entry)
                if (index < semester.entries.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Composable
private fun CourseRow(entry: AttestationEntry) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = entry.code,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Spacer(Modifier.width(12.dp))
            GradeChip(entry.grade)
        }
        Spacer(Modifier.height(14.dp))
        ScoreRow(entry.at1, entry.at2, entry.exam)
    }
}

@Composable
private fun ScoreRow(at1: String, at2: String, exam: String) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScoreCell("Attestation 1", at1, modifier = Modifier.weight(1f))
            VerticalDivider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp)
            )
            ScoreCell("Attestation 2", at2, modifier = Modifier.weight(1f))
            VerticalDivider(
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.35f),
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp)
            )
            ScoreCell("Final Exam", exam, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun ScoreCell(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 14.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun GradeChip(grade: String) {
    Surface(
        color = gradeColor(grade),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            text = grade,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

private fun gradeColor(grade: String): Color = when (grade) {
    "A", "A-"         -> Color(0xFF2E7D32)
    "B+", "B", "B-"   -> Color(0xFF1565C0)
    "C+", "C", "C-"   -> Color(0xFFE65100)
    "D+", "D"         -> Color(0xFFC62828)
    "F"               -> Color(0xFF37474F)
    "FX"              -> Color(0xFF6A1B9A)
    "P"               -> Color(0xFF00695C)
    else              -> Color(0xFF757575)
}