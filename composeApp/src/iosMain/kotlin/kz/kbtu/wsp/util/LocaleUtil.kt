package kz.kbtu.wsp.util

import platform.Foundation.NSUserDefaults

actual fun setLocale(languageCode: String) {
    // iOS resolves the app's locale from AppleLanguages at process launch,
    // so this takes effect the next time the app starts, not immediately.
    NSUserDefaults.standardUserDefaults.setObject(
        listOf(languageCode),
        forKey = "AppleLanguages"
    )
}