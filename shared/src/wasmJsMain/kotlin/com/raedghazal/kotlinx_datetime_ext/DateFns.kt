package com.raedghazal.kotlinx_datetime_ext

external class DateFnsLocale : JsAny

external interface FormatOptions : JsAny {
    var locale: DateFnsLocale
}

external interface ParseOptions : JsAny {
    var locale: DateFnsLocale
}

@JsModule("date-fns")
external object DateFns : JsAny {
    fun format(date: Date, pattern: String, options: FormatOptions = definedExternally): String
    fun parse(dateStr: String, pattern: String, baseDate: Date, options: ParseOptions = definedExternally): Date
}

@JsModule("date-fns/locale/en-US")
external object DateFnsLocaleEnUS : JsAny
