package com.raedghazal.kotlinx_datetime_ext

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual class Locale private constructor(val nsLocale: NSLocale) {
    actual companion object {
        actual fun default(): Locale {
            return Locale(NSLocale.currentLocale)
        }

        actual fun en(): Locale {
            return forLanguageTag("en")
        }

        actual fun forLanguageTag(languageTag: String): Locale {
            return Locale(NSLocale(languageTag))
        }
    }
}