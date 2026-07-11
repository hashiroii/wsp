package kz.kbtu.wsp.feature.news

import kz.kbtu.wsp.core.domain.model.NewsComment
import kz.kbtu.wsp.core.domain.model.NewsItem

internal val MOCK_ALL_NEWS: List<NewsItem> = listOf(
    NewsItem(
        "n1", "KBTU Named Top University in Kazakhstan for 2025–2026",
        "According to the QS World University Rankings 2025–2026, KBTU has secured the top position among Kazakhstani universities for the third consecutive year. The ranking reflects the university's commitment to research excellence, international partnerships, and graduate employability. Dean of Academic Affairs expressed gratitude to students, faculty, and staff for their ongoing dedication.",
        "01.07.2026", 23, isPinned = true
    ),
    NewsItem(
        "n2", "Fall Semester 2026–2027 Registration Now Open",
        "The Academic Affairs Office announces that course registration for the upcoming Fall semester is now available in the student portal. Students are advised to consult with their academic advisors before finalising their course selections. Priority registration closes on July 20, 2026. Late registration will incur an administrative fee.",
        "25.06.2026", 11, isPinned = true
    ),
    NewsItem(
        "n3", "New Student Center Opening Ceremony — August 15",
        "After two years of construction, the KBTU Student Center is set to open its doors on August 15, 2026. The facility includes a food court, student club offices, a co-working space, and a wellness centre. All students and staff are welcome to attend the opening ceremony at 11:00.",
        "20.06.2026", 34, isPinned = true
    ),
    NewsItem(
        "n4", "Summer School 2026 Results",
        "The Summer School 2026 programme has concluded with outstanding participation from over 300 students across 14 disciplines. Certificates of completion were distributed at the closing ceremony. Top performers in each track received merit scholarships for the upcoming academic year.",
        "15.06.2026", 7
    ),
    NewsItem(
        "n5", "Library Extended Hours During Finals",
        "The KBTU Library will remain open 24 hours a day, 7 days a week during the final examination period from June 10 to June 28. Additional study rooms have been reserved on the third floor. Students are reminded to bring their student ID for after-hours entry.",
        "10.06.2026", 3
    ),
    NewsItem(
        "n6", "International Partnership with MIT Announced",
        "KBTU is proud to announce a new academic partnership with the Massachusetts Institute of Technology. Under the agreement, KBTU faculty will participate in joint research projects and students will have access to MIT OpenCourseWare resources through the university portal. An exchange programme is planned for Spring 2027.",
        "05.06.2026", 19
    ),
    NewsItem(
        "n7", "Campus Wi-Fi Upgrade Completed",
        "The IT department has successfully completed a major upgrade to the campus wireless network. Coverage has been extended to all outdoor areas and the new dormitory block. Average connection speeds have increased fivefold. Any connectivity issues should be reported to the IT helpdesk.",
        "01.06.2026", 2
    )
)

internal val MOCK_PINNED_NEWS: List<NewsItem> = MOCK_ALL_NEWS.filter { it.isPinned }
internal val MOCK_PAGED_NEWS: List<NewsItem> = MOCK_ALL_NEWS.filter { !it.isPinned }

internal val MOCK_COMMENTS: Map<String, List<NewsComment>> = mapOf(
    "n1" to listOf(
        NewsComment("c1", "Sultanov A. K.", "Congratulations to KBTU! This is a proud moment for all of us.", "01.07.2026 14:32"),
        NewsComment("c2", "Bekova M. S.", "Well deserved — faculty and students have worked incredibly hard.", "01.07.2026 15:45"),
        NewsComment("c3", "Nurmagambetov D. A.", "Great news. Looking forward to even better rankings next year.", "02.07.2026 09:12")
    ),
    "n2" to listOf(
        NewsComment("c4", "Akhmetova Z. T.", "Finally! I was waiting for registration to open.", "25.06.2026 11:20"),
        NewsComment("c5", "Serikbaev N. E.", "Is there any update on elective courses for this semester?", "25.06.2026 13:05")
    ),
    "n3" to listOf(
        NewsComment("c6", "Kalieva G. R.", "Cannot wait for this — the campus really needs a proper student centre.", "20.06.2026 16:00"),
        NewsComment("c7", "Dzhaksybekov A. M.", "Will there be a café inside?", "20.06.2026 17:30"),
        NewsComment("c8", "Mukhanova S. B.", "So excited after such a long wait!", "21.06.2026 08:45")
    ),
    "n6" to listOf(
        NewsComment("c9", "Rakhimov T. B.", "This is huge news for the engineering school.", "05.06.2026 10:00"),
        NewsComment("c10", "Ospanova A. D.", "Hope the exchange programme has spots for undergraduates too.", "05.06.2026 11:22")
    )
)