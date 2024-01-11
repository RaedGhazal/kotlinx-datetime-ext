package com.raedghazal.kotlinx_datetime_ext

import kotlinx.browser.window

actual class Locale private constructor(val dateFnsLocale: DateFnsLocale) {
    actual companion object {

        private val platformLocales = mutableMapOf<String, DateFnsLocale>()

        actual fun default(): Locale {
            val language = window.navigator.language
            return forLanguageTag(language)
        }

        actual fun en(): Locale {
            return Locale(DateFnsLocaleEnUS.asDynamic()["enUS"].unsafeCast<DateFnsLocale>())
        }

        actual fun forLanguageTag(languageTag: String): Locale {
            val dateFnsLocale = platformLocales[languageTag.replace("-", "").replace("_", "")]
                ?: platformLocales[languageTag.split("-")[0].split("_")[0]]
            return dateFnsLocale?.let { Locale(it) } ?: en()
        }

        actual fun initPlatformLocales(vararg platformLocale: Any) {
            for (locale in platformLocale) {
                keys(locale).forEach { key ->
                    platformLocales[key] = locale.asDynamic()[key].unsafeCast<DateFnsLocale>()
                }
            }
        }
    }
}
