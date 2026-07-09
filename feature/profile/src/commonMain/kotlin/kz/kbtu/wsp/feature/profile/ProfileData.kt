package kz.kbtu.wsp.feature.profile

data class ProfileData(
    val photoUrl: String? = null,
    val firstName: String = "",
    val firstNameEn: String = "",
    val lastName: String = "",
    val lastNameEn: String = "",
    val middleName: String? = null,
    val middleNameEn: String? = null,
    val iin: String = "",
    val studentId: String = "",
    val login: String = "",
    val birthDate: String = "",
    val sex: String = "",
    val maritalStatus: String = "",
    val nationality: String = "",
    val citizenship: String = "",
    val isResident: Boolean = false,
    val email: String = "",
    val emailKbtu: String = "",
    val mobilePhone: String = "",
    val category: String = "",
    val needsDorm: Boolean = false,
    val entranceYear: String = "",
    val studyType: String = "",
    val studyForm: String = ""
)