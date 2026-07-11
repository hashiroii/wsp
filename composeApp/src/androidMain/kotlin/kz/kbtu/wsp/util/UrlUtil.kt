package kz.kbtu.wsp.util

import android.content.Intent
import android.net.Uri
import kz.kbtu.wsp.MainApplication

actual fun openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    MainApplication.instance.startActivity(intent)
}