package kz.kbtu.wsp.core.data.repository

import kz.kbtu.wsp.core.domain.model.ProfileData
import kz.kbtu.wsp.core.domain.repository.ProfileRepository

class FakeProfileRepository : ProfileRepository {

    override suspend fun getProfile(): ProfileData = MOCK_PROFILE

    private companion object {
        val MOCK_PROFILE = ProfileData(
            firstName = "Магжан",
            firstNameEn = "Magzhan",
            lastName = "Мубарак",
            lastNameEn = "Mubarak",
            middleName = null,
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
    }
}