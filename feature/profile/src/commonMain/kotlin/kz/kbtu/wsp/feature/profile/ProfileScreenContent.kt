package kz.kbtu.wsp.feature.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kz.kbtu.wsp.core.ui.icons.WspIcons
import kz.kbtu.wsp.feature.profile.resources.Res
import kz.kbtu.wsp.feature.profile.resources.profile_field_birth_date
import kz.kbtu.wsp.feature.profile.resources.profile_field_category
import kz.kbtu.wsp.feature.profile.resources.profile_field_citizenship
import kz.kbtu.wsp.feature.profile.resources.profile_field_dorm
import kz.kbtu.wsp.feature.profile.resources.profile_field_email
import kz.kbtu.wsp.feature.profile.resources.profile_field_email_kbtu
import kz.kbtu.wsp.feature.profile.resources.profile_field_entrance_year
import kz.kbtu.wsp.feature.profile.resources.profile_field_first_name
import kz.kbtu.wsp.feature.profile.resources.profile_field_iin
import kz.kbtu.wsp.feature.profile.resources.profile_field_last_name
import kz.kbtu.wsp.feature.profile.resources.profile_field_login
import kz.kbtu.wsp.feature.profile.resources.profile_field_marital_status
import kz.kbtu.wsp.feature.profile.resources.profile_field_middle_name
import kz.kbtu.wsp.feature.profile.resources.profile_field_nationality
import kz.kbtu.wsp.feature.profile.resources.profile_field_phone
import kz.kbtu.wsp.feature.profile.resources.profile_field_resident
import kz.kbtu.wsp.feature.profile.resources.profile_field_sex
import kz.kbtu.wsp.feature.profile.resources.profile_field_student_id
import kz.kbtu.wsp.feature.profile.resources.profile_field_study_form
import kz.kbtu.wsp.feature.profile.resources.profile_nav_addresses
import kz.kbtu.wsp.feature.profile.resources.profile_nav_documents
import kz.kbtu.wsp.feature.profile.resources.profile_nav_edu_direction
import kz.kbtu.wsp.feature.profile.resources.profile_nav_education
import kz.kbtu.wsp.feature.profile.resources.profile_nav_main_info
import kz.kbtu.wsp.feature.profile.resources.profile_nav_medical
import kz.kbtu.wsp.feature.profile.resources.profile_nav_other_docs
import kz.kbtu.wsp.feature.profile.resources.profile_nav_parents
import kz.kbtu.wsp.feature.profile.resources.profile_nav_unt
import kz.kbtu.wsp.feature.profile.resources.profile_no
import kz.kbtu.wsp.feature.profile.resources.profile_section_academic
import kz.kbtu.wsp.feature.profile.resources.profile_section_contact
import kz.kbtu.wsp.feature.profile.resources.profile_section_more
import kz.kbtu.wsp.feature.profile.resources.profile_section_personal
import kz.kbtu.wsp.feature.profile.resources.profile_yes
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

// label / primary value / optional EN translation / optional dimmed secondary line / bold flag
private data class FieldData(
    val label: String,
    val value: String,
    val valueEn: String? = null,
    val secondary: String? = null,
    val bold: Boolean = false
)

private data class SectionItem(
    val section: ProfileSection,
    val icon: ImageVector,
    val label: String,
    val isActive: Boolean = false
)

private fun formatEntranceYear(year: String): String =
    if (year.length == 8 && year.all { it.isDigit() }) "${year.take(4)}-${year.drop(4)}" else year

@Composable
fun ProfileScreenContent(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    if (state.showPhotoPreview) {
        PhotoPreviewDialog(
            profile = state.profile,
            onDismiss = { onIntent(ProfileIntent.DismissPhotoPreview) }
        )
    }

    val profile = state.profile
    val yes = stringResource(Res.string.profile_yes)
    val no = stringResource(Res.string.profile_no)
    val resident = stringResource(Res.string.profile_field_resident)
    val dorm = stringResource(Res.string.profile_field_dorm)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            ProfileHeader(
                profile = profile,
                onPhotoClick = { onIntent(ProfileIntent.ShowPhotoPreview) }
            )
        }

        item {
            ProfileInfoSection(
                title = stringResource(Res.string.profile_section_personal),
                fields = buildList {
                    add(FieldData(
                        stringResource(Res.string.profile_field_first_name),
                        profile.firstName,
                        profile.firstNameEn.takeIf { it.isNotEmpty() }
                    ))
                    add(FieldData(
                        stringResource(Res.string.profile_field_last_name),
                        profile.lastName,
                        profile.lastNameEn.takeIf { it.isNotEmpty() }
                    ))
                    profile.middleName?.takeIf { it.isNotEmpty() }?.let {
                        add(FieldData(
                            stringResource(Res.string.profile_field_middle_name),
                            it,
                            profile.middleNameEn
                        ))
                    }
                    add(FieldData(stringResource(Res.string.profile_field_iin), profile.iin))
                    add(FieldData(stringResource(Res.string.profile_field_birth_date), profile.birthDate))
                    add(FieldData(stringResource(Res.string.profile_field_sex), profile.sex))
                    add(FieldData(stringResource(Res.string.profile_field_marital_status), profile.maritalStatus))
                }
            )
        }

        item {
            ProfileInfoSection(
                title = stringResource(Res.string.profile_section_academic),
                fields = listOf(
                    FieldData(stringResource(Res.string.profile_field_student_id), profile.studentId, bold = true),
                    FieldData(stringResource(Res.string.profile_field_login), profile.login, bold = true),
                    FieldData(stringResource(Res.string.profile_field_nationality), profile.nationality),
                    FieldData(
                        label = stringResource(Res.string.profile_field_citizenship),
                        value = profile.citizenship,
                        secondary = "$resident: ${if (profile.isResident) yes else no}"
                    ),
                    FieldData(
                        label = stringResource(Res.string.profile_field_category),
                        value = profile.category,
                        secondary = "$dorm: ${if (profile.needsDorm) yes else no}"
                    ),
                    FieldData(stringResource(Res.string.profile_field_entrance_year), formatEntranceYear(profile.entranceYear)),
                    FieldData(
                        label = stringResource(Res.string.profile_field_study_form),
                        value = profile.studyType,
                        secondary = profile.studyForm.takeIf { it.isNotEmpty() },
                        bold = true
                    )
                )
            )
        }

        item {
            ProfileInfoSection(
                title = stringResource(Res.string.profile_section_contact),
                fields = listOf(
                    FieldData(stringResource(Res.string.profile_field_email), profile.email),
                    FieldData(stringResource(Res.string.profile_field_email_kbtu), profile.emailKbtu, bold = true),
                    FieldData(stringResource(Res.string.profile_field_phone), profile.mobilePhone, bold = true)
                )
            )
        }

        item {
            Column {
                Text(
                    text = stringResource(Res.string.profile_section_more),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, bottom = 10.dp)
                )
                ProfileSectionGrid(
                    sections = listOf(
                        SectionItem(ProfileSection.MainInfo, WspIcons.Person, stringResource(Res.string.profile_nav_main_info), isActive = true),
                        SectionItem(ProfileSection.EducationDirection, WspIcons.School, stringResource(Res.string.profile_nav_edu_direction)),
                        SectionItem(ProfileSection.Education, WspIcons.Book, stringResource(Res.string.profile_nav_education)),
                        SectionItem(ProfileSection.Documents, WspIcons.Description, stringResource(Res.string.profile_nav_documents)),
                        SectionItem(ProfileSection.Addresses, WspIcons.LocationOn, stringResource(Res.string.profile_nav_addresses)),
                        SectionItem(ProfileSection.MedicalExam, WspIcons.LocalHospital, stringResource(Res.string.profile_nav_medical)),
                        SectionItem(ProfileSection.Unt, WspIcons.Assignment, stringResource(Res.string.profile_nav_unt)),
                        SectionItem(ProfileSection.ParentsInfo, WspIcons.Group, stringResource(Res.string.profile_nav_parents)),
                        SectionItem(ProfileSection.OtherDocuments, WspIcons.Folder, stringResource(Res.string.profile_nav_other_docs))
                    ),
                    onSectionClick = { onIntent(ProfileIntent.NavigateToSection(it)) }
                )
            }
        }
    }
}

@Composable
private fun ProfileHeader(
    profile: ProfileData,
    onPhotoClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initials = buildString {
        profile.firstName.firstOrNull()?.let { append(it.uppercaseChar()) }
        profile.lastName.firstOrNull()?.let { append(it.uppercaseChar()) }
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ProfileAvatar(
            initials = initials,
            size = 88.dp,
            textStyle = MaterialTheme.typography.headlineMedium,
            onClick = onPhotoClick
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${profile.lastName} ${profile.firstName}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
        if (profile.lastNameEn.isNotEmpty() || profile.firstNameEn.isNotEmpty()) {
            Text(
                text = "${profile.lastNameEn} ${profile.firstNameEn}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ProfileAvatar(
    initials: String,
    size: Dp,
    textStyle: TextStyle,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initials,
            color = MaterialTheme.colorScheme.onPrimary,
            style = textStyle,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfileInfoSection(
    title: String,
    fields: List<FieldData>,
    modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 14.dp, bottom = 10.dp)
            )
            HorizontalDivider()
            fields.forEachIndexed { i, field ->
                ProfileInfoField(field)
                if (i < fields.lastIndex) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
private fun ProfileInfoField(
    field: FieldData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = field.label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Start
        )
        Text(
            text = field.value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (field.bold) FontWeight.Bold else FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Start
        )
        if (field.valueEn != null) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 1.dp)
            ) {
                Text(
                    text = "EN",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 5.dp, vertical = 1.dp)
                )
                Text(
                    text = field.valueEn,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Start
                )
            }
        }
        if (field.secondary != null) {
            Text(
                text = field.secondary,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(top = 1.dp)
            )
        }
    }
}

@Composable
private fun ProfileSectionGrid(
    sections: List<SectionItem>,
    onSectionClick: (ProfileSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        sections.chunked(3).forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { item ->
                    ProfileSectionCard(
                        icon = item.icon,
                        label = item.label,
                        isActive = item.isActive,
                        onClick = { onSectionClick(item.section) },
                        modifier = Modifier.weight(1f)
                    )
                }
                repeat(3 - row.size) { Box(modifier = Modifier.weight(1f)) }
            }
        }
    }
}

@Composable
private fun ProfileSectionCard(
    icon: ImageVector,
    label: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val containerColor = if (isActive) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.surface
    val contentColor = if (isActive) MaterialTheme.colorScheme.onPrimary
    else MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isActive) 4.dp else 1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = contentColor,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun PhotoPreviewDialog(
    profile: ProfileData,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.88f))
                .clickable(onClick = onDismiss),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                val initials = buildString {
                    profile.firstName.firstOrNull()?.let { append(it.uppercaseChar()) }
                    profile.lastName.firstOrNull()?.let { append(it.uppercaseChar()) }
                }
                ProfileAvatar(
                    initials = initials,
                    size = 180.dp,
                    textStyle = MaterialTheme.typography.displayMedium
                )
                Text(
                    text = "${profile.lastName} ${profile.firstName}",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                if (profile.lastNameEn.isNotEmpty() || profile.firstNameEn.isNotEmpty()) {
                    Text(
                        text = "${profile.lastNameEn} ${profile.firstNameEn}",
                        color = Color.White.copy(alpha = 0.6f),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ProfileScreenContentPreview() {
    ProfileScreenContent(
        state = ProfileState(
            profile = ProfileData(
                firstName = "Магжан",
                firstNameEn = "Magzhan",
                lastName = "Мубарак",
                lastNameEn = "Mubarak",
                iin = "040101XXXXXX",
                studentId = "2023XXXX",
                login = "m.mubarak",
                birthDate = "01.01.2004",
                sex = "Мужской",
                maritalStatus = "не состоит в браке",
                nationality = "Казах",
                citizenship = "Казахстан",
                isResident = true,
                email = "jasanasxat2@gmail.com",
                emailKbtu = "m.mubarak@kbtu.kz",
                mobilePhone = "7XXXXXXXXXX",
                category = "Студент",
                needsDorm = false,
                entranceYear = "2023-2024",
                studyType = "Бакалавр",
                studyForm = "лет: 4, сем. оконч. 2"
            )
        ),
        onIntent = {}
    )
}