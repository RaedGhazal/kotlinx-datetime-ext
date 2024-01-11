package com.raedghazal.kotlinx_datetime_ext

expect class Locale {
    companion object {
        fun default(): Locale
        fun en(): Locale
        fun forLanguageTag(languageTag: String): Locale
    }
}
