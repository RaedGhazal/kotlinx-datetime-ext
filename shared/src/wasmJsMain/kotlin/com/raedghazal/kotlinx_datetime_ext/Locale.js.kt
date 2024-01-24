package com.raedghazal.kotlinx_datetime_ext

import kotlinx.browser.window

actual class Locale private constructor(val dateFnsLocale: DateFnsLocale) {
    actual companion object {

        internal var platformLocales: Map<String, DateFnsLocale> = emptyMap()

        actual fun default(): Locale {
            val language = window.navigator.language
            return forLanguageTag(language)
        }

        actual fun en(): Locale {
            return Locale(DateFnsLocaleEnUS["enUS"]!!.unsafeCast())
        }

        actual fun forLanguageTag(languageTag: String): Locale {
            val dateFnsLocale = platformLocales[languageTag.replace("-", "").replace("_", "")]
                ?: platformLocales[languageTag.split("-")[0].split("_")[0]]
            return dateFnsLocale?.let { Locale(it) } ?: en()
        }
    }
}

/**
 * Initialize the library with external platform locale data.
 * Used to allow choosing the locales added to the final application bundle.
 */
fun Locale.Companion.initPlatformLocales(vararg platformLocale: JsAny) {
    platformLocales = platformLocale.flatMap {
        keys(it).map { key ->
            key to it[key]!!.unsafeCast<DateFnsLocale>()
        }
    }.toMap()
}
