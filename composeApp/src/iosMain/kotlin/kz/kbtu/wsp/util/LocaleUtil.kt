package kz.kbtu.wsp.util

actual fun setLocale(languageCode: String) {
    // iOS does not allow programmatic locale change.
    // The user must change the language in iOS Settings → your app → Language.
}