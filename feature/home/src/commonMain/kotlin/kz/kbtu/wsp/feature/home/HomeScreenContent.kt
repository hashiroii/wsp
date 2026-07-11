package kz.kbtu.wsp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import kz.kbtu.wsp.core.ui.icons.WspIcons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kz.kbtu.wsp.core.domain.model.NewsItem
import kz.kbtu.wsp.feature.home.resources.Res
import kz.kbtu.wsp.feature.home.resources.attendance_active
import kz.kbtu.wsp.feature.home.resources.attendance_no_active
import kz.kbtu.wsp.feature.home.resources.gpa_overall
import kz.kbtu.wsp.feature.home.resources.grades_attestation
import kz.kbtu.wsp.feature.home.resources.grades_transcript
import kz.kbtu.wsp.feature.home.resources.quick_coming_soon
import kz.kbtu.wsp.feature.home.resources.quick_financial
import kz.kbtu.wsp.feature.home.resources.quick_lost_found
import kz.kbtu.wsp.feature.home.resources.quick_map
import kz.kbtu.wsp.feature.home.resources.reg_add_drop
import kz.kbtu.wsp.feature.home.resources.reg_disciplines
import kz.kbtu.wsp.feature.home.resources.reg_fx
import kz.kbtu.wsp.feature.home.resources.section_attendance
import kz.kbtu.wsp.feature.home.resources.section_grades
import kz.kbtu.wsp.feature.home.resources.current_week
import kz.kbtu.wsp.feature.home.resources.section_news
import kz.kbtu.wsp.feature.home.resources.section_registration
import kz.kbtu.wsp.core.ui.theme.WspTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreenContent(
    state: HomeState,
    onIntent: (HomeIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NewsCarousel(
            news = state.pinnedNews,
            onNewsClick = { onIntent(HomeIntent.OnNewsItemClick(it)) },
            onViewAll = { onIntent(HomeIntent.OnViewAllNewsClick) }
        )
        if (state.currentWeek > 0) {
            Text(
                text = stringResource(Res.string.current_week, state.currentWeek),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
        AttendanceCard(
            isActive = state.isAttendanceActive,
            onClick = { onIntent(HomeIntent.OnAttendanceClick) }
        )
        QuickAccessGrid(onIntent = onIntent)
        GradesSection(gpa = state.gpa, onIntent = onIntent)
        RegistrationSection(onIntent = onIntent)
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun NewsCarousel(
    news: List<NewsItem>,
    onNewsClick: (String) -> Unit,
    onViewAll: () -> Unit
) {
    if (news.isEmpty()) return
    val pagerState = rememberPagerState { news.size }

    LaunchedEffect(pagerState.pageCount) {
        while (true) {
            delay(4000L)
            val next = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(next)
        }
    }

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(Res.string.section_news),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            TextButton(onClick = onViewAll) {
                Text("See all", style = MaterialTheme.typography.labelMedium)
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            val item = news[page]
            Card(
                onClick = { onNewsClick(item.id) },
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "!",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.padding(end = 10.dp, top = 2.dp)
                    )
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(news.size) { index ->
                val selected = pagerState.currentPage == index
                Box(
                    modifier = Modifier
                        .padding(horizontal = 3.dp)
                        .size(if (selected) 8.dp else 6.dp)
                        .background(
                            color = if (selected) MaterialTheme.colorScheme.primary
                                    else MaterialTheme.colorScheme.outline,
                            shape = CircleShape
                        )
                )
            }
        }
    }
}

@Composable
private fun AttendanceCard(isActive: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(
                        color = if (isActive) MaterialTheme.colorScheme.error
                                else MaterialTheme.colorScheme.outline,
                        shape = CircleShape
                    )
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(Res.string.section_attendance),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = if (isActive) stringResource(Res.string.attendance_active)
                           else stringResource(Res.string.attendance_no_active),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            if (isActive) {
                Text(
                    text = "!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun QuickAccessGrid(onIntent: (HomeIntent) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuickAccessCard(
                title = stringResource(Res.string.quick_map),
                backgroundColor = Color(0xFF2E7D32),
                icon = Icons.Default.LocationOn,
                onClick = { onIntent(HomeIntent.OnMapClick) },
                modifier = Modifier.weight(1f)
            )
            QuickAccessCard(
                title = stringResource(Res.string.quick_lost_found),
                backgroundColor = Color(0xFF6A1B9A),
                icon = Icons.Default.Search,
                onClick = { onIntent(HomeIntent.OnLostFoundClick) },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            QuickAccessCard(
                title = stringResource(Res.string.quick_financial),
                backgroundColor = Color(0xFFF59E0B),
                icon = WspIcons.AccountBalance,
                onClick = { onIntent(HomeIntent.OnFinancialClick) },
                modifier = Modifier.weight(1f)
            )
            QuickAccessCard(
                title = stringResource(Res.string.quick_coming_soon),
                backgroundColor = Color(0xFF546E7A),
                icon = Icons.Default.MoreVert,
                onClick = {},
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun QuickAccessCard(
    title: String,
    backgroundColor: Color,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(110.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier.align(Alignment.TopStart),
                maxLines = 2
            )
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.4f),
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun GradesSection(gpa: String, onIntent: (HomeIntent) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionHeader(stringResource(Res.string.section_grades))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(Res.string.gpa_overall),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = gpa,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                HorizontalDivider()
                NavRow(
                    title = stringResource(Res.string.grades_attestation),
                    onClick = { onIntent(HomeIntent.OnAttestationClick) }
                )
                HorizontalDivider()
                NavRow(
                    title = stringResource(Res.string.grades_transcript),
                    onClick = { onIntent(HomeIntent.OnTranscriptClick) }
                )
            }
        }
    }
}

@Composable
private fun RegistrationSection(onIntent: (HomeIntent) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        SectionHeader(stringResource(Res.string.section_registration))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                NavRow(
                    title = stringResource(Res.string.reg_fx),
                    onClick = { onIntent(HomeIntent.OnFxRegistrationClick) }
                )
                HorizontalDivider()
                NavRow(
                    title = stringResource(Res.string.reg_disciplines),
                    onClick = { onIntent(HomeIntent.OnCourseRegistrationClick) }
                )
                HorizontalDivider()
                NavRow(
                    title = stringResource(Res.string.reg_add_drop),
                    onClick = { onIntent(HomeIntent.OnAddDropClick) }
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
private fun NavRow(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.35f)
        )
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    WspTheme {
        HomeScreenContent(
            state = HomeState(gpa = "3.75", isAttendanceActive = true),
            onIntent = {}
        )
    }
}