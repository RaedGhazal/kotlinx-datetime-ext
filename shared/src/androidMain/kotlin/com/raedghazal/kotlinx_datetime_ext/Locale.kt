package com.raedghazal.kotlinx_datetime_ext

actual class Locale private constructor(val javaLocale: java.util.Locale) {

    actual companion object {
        actual fun default(): Locale {
            return Locale(java.util.Locale.getDefault())
        }

        actual fun en(): Locale {
            return Locale(java.util.Locale.ENGLISH)
        }

        actual fun forLanguageTag(languageTag: String): Locale {
            return Locale(java.util.Locale.forLanguageTag(languageTag))
        }

        actual fun initPlatformLocales(vararg platformLocale: Any) {
            // not used on this platform
        }
    }
}