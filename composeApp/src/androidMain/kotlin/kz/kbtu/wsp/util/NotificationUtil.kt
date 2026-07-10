package kz.kbtu.wsp.util

import android.content.Intent
import android.provider.Settings
import kz.kbtu.wsp.MainApplication

actual fun openNotificationSettings() {
    val context = MainApplication.instance
    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
        putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}
