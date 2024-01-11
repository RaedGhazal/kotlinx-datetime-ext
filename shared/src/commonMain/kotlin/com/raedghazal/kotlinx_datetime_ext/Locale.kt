package com.raedghazal.kotlinx_datetime_ext

expect class Locale {
    companion object {
        fun default(): Locale
        fun en(): Locale
        fun forLanguageTag(languageTag: String): Locale

        /**
         * Initialize the library with external platform locale data.
         * Used on JS/WasmJS targets to allow choosing the locales added to the final application bundle.
         */
        fun initPlatformLocales(vararg platformLocale: Any)
    }
}
