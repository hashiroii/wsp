package kz.kbtu.wsp.feature.grades

internal data class AttestationEntry(
    val code: String,
    val name: String,
    val at1: String,
    val at2: String,
    val exam: String,
    val grade: String
)

internal data class SemesterRecord(
    val label: String,
    val entries: List<AttestationEntry>
)

internal val MOCK_ATTESTATION: List<SemesterRecord> = listOf(
    SemesterRecord("2025–2026 · Spring", listOf(
        AttestationEntry("INFT3137", "Android Advanced", "38.0", "22.0", "40.0", "A"),
        AttestationEntry("INFT4111", "Cyber Security Fundamentals", "28.0", "28.0", "36.0", "A-"),
        AttestationEntry("INFT3210", "Field Projects for Information Systems", "19.0", "38.4", "36.0", "A-"),
        AttestationEntry("INFS1201", "Introduction to Open Source", "30.0", "26.7", "40.0", "A"),
        AttestationEntry("HUM1102", "Philosophy", "28.0", "30.0", "38.0", "A"),
        AttestationEntry("INFS1202", "Responsible Digital Entrepreneurship", "27.5", "30.0", "40.0", "A"),
    )),
    SemesterRecord("2025–2026 · Fall", listOf(
        AttestationEntry("INFT3135", "Android Development", "25.0", "33.0", "40.0", "A"),
        AttestationEntry("INFT3107", "Basics of Information Systems", "22.0", "24.0", "38.0", "B"),
        AttestationEntry("INFT3106", "Fundamentals of Business for IS", "27.0", "28.5", "26.0", "B"),
        AttestationEntry("HUM1101", "History of Kazakhstan", "13.5", "28.0", "30.0", "C+"),
        AttestationEntry("INFT1101", "Information and Communication Technologies", "28.5", "28.0", "28.0", "B+"),
        AttestationEntry("CSCI2208", "Software Engineering", "29.0", "31.0", "40.0", "A"),
    )),
    SemesterRecord("2024–2025 · Spring", listOf(
        AttestationEntry("INFT3212", "Blockchain Technology and Applications", "28.0", "28.0", "40.0", "A"),
        AttestationEntry("CSCI2109", "Computer Networks and Architecture", "7.8", "35.2", "26.3", "C"),
        AttestationEntry("INFT2204", "Introduction to Business Management", "24.2", "31.8", "39.0", "A"),
        AttestationEntry("PHE102", "Physical Education II", "27.0", "21.0", "32.0", "P"),
        AttestationEntry("LAN1118", "Professional Kazakh Language (C2)", "24.1", "28.1", "28.3", "B"),
        AttestationEntry("INFT2205", "Web Development", "20.1", "33.9", "22.0", "B-"),
    )),
    SemesterRecord("2024–2025 · Fall", listOf(
        AttestationEntry("CSCI2105", "Algorithms and Data Structures", "28.5", "29.0", "29.0", "B+"),
        AttestationEntry("CSCI3115", "Computer Architecture", "29.0", "29.0", "35.0", "A-"),
        AttestationEntry("CSCI2104", "Databases", "26.0", "25.5", "38.0", "A-"),
        AttestationEntry("CSCI2106", "Object-Oriented Programming and Design", "30.0", "29.5", "40.0", "A"),
        AttestationEntry("PHE101", "Physical Education I", "20.0", "18.0", "35.0", "P"),
        AttestationEntry("LAN1119", "Russian Language", "24.0", "20.6", "30.5", "B-"),
    )),
    SemesterRecord("2023–2024 · Spring", listOf(
        AttestationEntry("MATH1202", "Calculus II", "21.3", "25.2", "33.5", "B"),
        AttestationEntry("MATH1203", "Linear Algebra for Engineers", "22.5", "9.5", "20.0", "D"),
        AttestationEntry("HUM1137", "Merging Personal and Global Evolution", "22.0", "32.0", "35.0", "B+"),
        AttestationEntry("FUN1105", "Physics I", "23.7", "28.7", "38.0", "A-"),
        AttestationEntry("CSCI1204", "Programming Principles II", "15.0", "42.0", "33.0", "A-"),
        AttestationEntry("STAT2201", "Statistics", "19.0", "28.0", "22.0", "C"),
    )),
    SemesterRecord("2023–2024 · Fall", listOf(
        AttestationEntry("MATH1102", "Calculus I", "26.0", "21.0", "23.0", "C+"),
        AttestationEntry("CSCI1102", "Discrete Structures", "16.0", "30.5", "29.0", "B-"),
        AttestationEntry("LAN1182", "English Pre-Intermediate (A2)", "30.0", "29.0", "37.0", "A"),
        AttestationEntry("CSCI1103", "Programming Principles I", "24.0", "36.0", "40.0", "A"),
    )),
)