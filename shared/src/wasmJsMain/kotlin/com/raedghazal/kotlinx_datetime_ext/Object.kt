package com.raedghazal.kotlinx_datetime_ext

/**
 * A helper function to create an empty JS object.
 */
fun obj(): JsAny = js("({})")

/**
 * A helper function to create a JS object of given external type.
 */
inline fun <T : JsAny> obj(init: T.() -> Unit): T {
    return obj().unsafeCast<T>().apply(init)
}

private fun jsKeys(obj: JsAny): JsArray<JsString> = js("Object.keys(obj)")

/**
 * Convert JsArray to Kotlin Array.
 */
private inline fun <reified T : JsAny> JsArray<T>.toArray(): Array<T> {
    return Array(length) { get(it)!! }
}

/**
 * Get the list of keys from JS Object
 */
fun keys(o: JsAny): List<String> {
    return jsKeys(o).toArray().asList().map { it.toString() }
}

@Suppress("RedundantNullableReturnType")
private fun objGet(obj: JsAny, key: String): JsAny? = js("obj[key]")

/**
 * Operator to get property from JS Object
 */
operator fun JsAny.get(key: String): JsAny? {
    return objGet(this, key)
}
