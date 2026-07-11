package kz.kbtu.wsp.core.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp

object WspIcons {

    val Folder: ImageVector by lazy {
        ImageVector.Builder(
            name = "Folder",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes("M10 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V8c0-1.1-.9-2-2-2h-8l-2-2z"),
            fill = SolidColor(Color(0xFF000000))
        ).build()
    }

    val Language: ImageVector by lazy {
        ImageVector.Builder(
            name = "Language",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes(
                "M11.99 2C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2z" +
                "m6.93 6h-2.95c-.32-1.25-.78-2.45-1.38-3.56 1.84.63 3.37 1.9 4.33 3.56z" +
                "M12 4.04c.83 1.2 1.48 2.53 1.91 3.96h-3.82c.43-1.43 1.08-2.76 1.91-3.96z" +
                "M4.26 14C4.1 13.36 4 12.69 4 12s.1-1.36.26-2h3.38c-.08.66-.14 1.32-.14 2 0 .68.06 1.34.14 2H4.26z" +
                "m.82 2h2.95c.32 1.25.78 2.45 1.38 3.56-1.84-.63-3.37-1.9-4.33-3.56z" +
                "m2.95-8H5.08c.96-1.66 2.49-2.93 4.33-3.56C8.81 5.55 8.35 6.75 8.03 8z" +
                "M12 19.96c-.83-1.2-1.48-2.53-1.91-3.96h3.82c-.43 1.43-1.08 2.76-1.91 3.96z" +
                "M14.34 14H9.66c-.09-.66-.16-1.32-.16-2 0-.68.07-1.35.16-2h4.68c.09.65.16 1.32.16 2 0 .68-.07 1.34-.16 2z" +
                "m.25 5.56c.6-1.11 1.06-2.31 1.38-3.56h2.95c-.96 1.66-2.49 2.93-4.33 3.56z" +
                "M16.36 14c.08-.66.14-1.32.14-2 0-.68-.06-1.34-.14-2h3.38c.16.64.26 1.31.26 2s-.1 1.36-.26 2h-3.38z"
            ),
            fill = SolidColor(Color(0xFF000000))
        ).build()
    }

    val AccountBalance: ImageVector by lazy {
        ImageVector.Builder(
            name = "AccountBalance",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).addPath(
            pathData = addPathNodes("M4 10v7h3v-7H4zm6 0v7h3v-7h-3zM2 22h19v-3H2v3zm14-12v7h3v-7h-3zM11.5 1L2 6v2h19V6l-9.5-5z"),
            fill = SolidColor(Color(0xFF000000))
        ).build()
    }

    val Person: ImageVector by lazy {
        ImageVector.Builder("Person", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val School: ImageVector by lazy {
        ImageVector.Builder("School", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M5 13.18v4L12 21l7-3.82v-4L12 17l-7-3.82zM12 3L1 9l11 6 9-4.91V17h2V9L12 3z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Book: ImageVector by lazy {
        ImageVector.Builder("Book", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M18 2H6c-1.1 0-2 .9-2 2v16c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V4c0-1.1-.9-2-2-2zM6 4h5v8l-2.5-1.5L6 12V4z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Description: ImageVector by lazy {
        ImageVector.Builder("Description", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M14 2H6c-1.1 0-1.99.9-1.99 2L4 20c0 1.1.89 2 1.99 2H18c1.1 0 2-.9 2-2V8l-6-6zm2 16H8v-2h8v2zm0-4H8v-2h8v2zm-3-5V3.5L18.5 9H13z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val LocationOn: ImageVector by lazy {
        ImageVector.Builder("LocationOn", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val LocalHospital: ImageVector by lazy {
        ImageVector.Builder("LocalHospital", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M19 3H5c-1.1 0-1.99.9-1.99 2L3 19c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-1 11h-4v4h-4v-4H6v-4h4V6h4v4h4v4z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Assignment: ImageVector by lazy {
        ImageVector.Builder("Assignment", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M19 3h-4.18C14.4 1.84 13.3 1 12 1c-1.3 0-2.4.84-2.82 2H5c-1.1 0-2 .9-2 2v14c0 1.1.9 2 2 2h14c1.1 0 2-.9 2-2V5c0-1.1-.9-2-2-2zm-7 0c.55 0 1 .45 1 1s-.45 1-1 1-1-.45-1-1 .45-1 1-1zm2 14H7v-2h7v2zm3-4H7v-2h10v2zm0-4H7V7h10v2z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Group: ImageVector by lazy {
        ImageVector.Builder("Group", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M16 11c1.66 0 2.99-1.34 2.99-3S17.66 5 16 5c-1.66 0-3 1.34-3 3s1.34 3 3 3zm-8 0c1.66 0 2.99-1.34 2.99-3S9.66 5 8 5C6.34 5 5 6.34 5 8s1.34 3 3 3zm0 2c-2.33 0-7 1.17-7 3.5V19h14v-2.5c0-2.33-4.67-3.5-7-3.5zm8 0c-.29 0-.62.02-.97.05 1.16.84 1.97 1.97 1.97 3.45V19h6v-2.5c0-2.33-4.67-3.5-7-3.5z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val ViewList: ImageVector by lazy {
        ImageVector.Builder("ViewList", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M3 13h2v-2H3v2zm0 4h2v-2H3v2zm0-8h2V7H3v2zm4 4h14v-2H7v2zm0 4h14v-2H7v2zM7 7v2h14V7H7z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val GridView: ImageVector by lazy {
        ImageVector.Builder("GridView", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M3 3v8h8V3H3zm6 6H5V5h4v4zm-6 4v8h8v-8H3zm6 6H5v-4h4v4zm4-16v8h8V3h-8zm6 6h-4V5h4v4zm-6 4v8h8v-8h-8zm6 6h-4v-4h4v4z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Palette: ImageVector by lazy {
        ImageVector.Builder("Palette", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M12 3c-4.97 0-9 4.03-9 9s4.03 9 9 9c.83 0 1.5-.67 1.5-1.5 0-.39-.15-.74-.39-1.01-.23-.26-.38-.61-.38-.99 0-.83.67-1.5 1.5-1.5H16c2.76 0 5-2.24 5-5 0-4.42-4.03-8-9-8zm-5.5 9c-.83 0-1.5-.67-1.5-1.5S5.67 9 6.5 9 8 9.67 8 10.5 7.33 12 6.5 12zm3-4C8.67 8 8 7.33 8 6.5S8.67 5 9.5 5s1.5.67 1.5 1.5S10.33 8 9.5 8zm5 0c-.83 0-1.5-.67-1.5-1.5S13.67 5 14.5 5s1.5.67 1.5 1.5S15.33 8 14.5 8zm3 4c-.83 0-1.5-.67-1.5-1.5S16.67 9 17.5 9s1.5.67 1.5 1.5-.67 1.5-1.5 1.5z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Send: ImageVector by lazy {
        ImageVector.Builder("Send", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M2.01 21L23 12 2.01 3 2 10l15 2-15 2z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Search: ImageVector by lazy {
        ImageVector.Builder("Search", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91 3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6 0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val ArrowBack: ImageVector by lazy {
        ImageVector.Builder("ArrowBack", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M20 11H7.83l5.59-5.59L12 4l-8 8 8 8 1.41-1.41L7.83 13H20v-2z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val ChevronRight: ImageVector by lazy {
        ImageVector.Builder("ChevronRight", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M10 6L8.59 7.41 13.17 12l-4.58 4.59L10 18l6-6z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }

    val Notifications: ImageVector by lazy {
        ImageVector.Builder("Notifications", 24.dp, 24.dp, 24f, 24f)
            .addPath(
                addPathNodes("M12 22c1.1 0 2-.9 2-2h-4c0 1.1.9 2 2 2zm6-6v-5c0-3.07-1.64-5.64-4.5-6.32V4c0-.83-.67-1.5-1.5-1.5s-1.5.67-1.5 1.5v.68C7.63 5.36 6 7.92 6 11v5l-2 2v1h16v-1l-2-2z"),
                fill = SolidColor(Color(0xFF000000))
            ).build()
    }
}