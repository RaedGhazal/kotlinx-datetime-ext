package com.raedghazal.kotlinx_datetime_ext

/**
 * A helper function to create a JS object of given external type.
 */
inline fun <T> obj(init: T.() -> Unit): T {
    return (js("{}").unsafeCast<T>()).apply(init)
}

/**
 * Get the list of keys from JS Object
 */
fun keys(o: dynamic): List<String> {
    return js("Object").keys(o).unsafeCast<Array<String>>().toList()
}
