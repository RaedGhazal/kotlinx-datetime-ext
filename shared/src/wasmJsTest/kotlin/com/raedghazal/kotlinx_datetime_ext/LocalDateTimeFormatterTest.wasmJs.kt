package com.raedghazal.kotlinx_datetime_ext

@JsModule("date-fns/locale/pl")
external object DateFnsLocalePl : JsAny

actual fun initPlatformLocales() {
    Locale.initPlatformLocales(DateFnsLocalePl)
}
