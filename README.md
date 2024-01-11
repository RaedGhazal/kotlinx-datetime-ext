[![Download](https://img.shields.io/maven-central/v/com.raedghazal/kotlinx_datetime_ext)](https://repo1.maven.org/maven2/com/raedghazal/kotlinx_datetime_ext)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.21-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlinx-DateTime](https://img.shields.io/badge/kotlinx--datetime-0.5.0-blue)](https://github.com/Kotlin/kotlinx-datetime)
# kotlinx-datetime-ext
A Kotling Multiplatform library that provides for kotlinx-datetime extensions and helper functions

If you recently switched from **java.datetime** to **kotlinx.datetime** you probably noticed that its missing a lot of functionality that you will have to do manually, this library comes to close the gap by providing most of the **java.datetime** functionality but in Kotlin!


### Supported platforms
- Android
- iOS
- JVM (Desktop)
- Js & WasmJs


### Implementation
```kt
implementation("com.raedghazal:kotlinx_datetime_ext:1.1.0")
```
if you're using it in commonMain and would like to access it from androidApp then use `api(...)` instead to expose it [more about the difference between implementation and api](https://stackoverflow.com/a/44419574/10834775)


### Initialization (JS only)
No Initialization is needed, except if you're targeting JS or WasmJS, call `Locale.initPlatformLocales(...)` in your JS module to setup all the locales you're supporting, default is English Locale only

#### examples:
1. to support specific Locales
```kt
@JsModule("date-fns/locale/pl")
external object DateFnsLocalePl

@JsModule("date-fns/locale/it")
external object DateFnsLocaleIt

Locale.initPlatformLocales(DateFnsLocalePl, DateFnsLocaleIt) // support "pl" and "it" locales
```
2. to support all Locales
```kt
@JsModule("date-fns/locale")
external object DateFnsLocales

Locale.initPlatformLocales(DateFnsLocales) // support all locales 
```

**This function is only available in the JS module, won't appear in `commonMain`*


## Usage

### 1. Math
To add or subtract date or time to `LocalDateTime` object
you can use operator functions `+` and `-` with duration values
```kt
val localDateTime = LocalDateTime(2023, 1, 7, 21, 0)

val afterFiveDays = localDateTime + 5.days
val beforeThreeHours = localDateTime - 3.hours
```

or using the overload function with DateTimeUnit
```kt
val localDateTime = LocalDateTime(2023, 1, 7, 21, 0)

val afterFiveDays = localDateTime.plus(5, DateTimeUnit.DAY)
val beforeThreeHours = localDateTime.minus(3, DateTimeUnit.HOUR)
```

### 2. Parsing & Formatting
#### Formatting to String

```kt
val localDateTime = LocalDateTime(2023, 1, 1, 1, 1)
val formatter = LocalDateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm", Locale.en())

print(formatter.format(localDateTime)) //"01/01/2023 - 01:01"
```
the overloads support all datetime objects
```kt
fun format(localDateTime: LocalDateTime): String
fun format(localTime: LocalTime): String
fun format(localDate: LocalDate): String
```

#### Parsing from String
```
val formatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.en())
val localDateTime = formatter.parseToLocalDateTime("2023-01-01 01:01:00") // LocalDateTime(2023, 1, 1, 1, 1)
```
also you can parse to any datetime object
```kt
    fun parseToLocalDateTime(str: String): LocalDateTime
fun parseToLocalDate(str: String): LocalDate
fun parseToLocalTime(str: String): LocalTime
```

### Helper extension functions

To transform a LocalDate object to LocalDateTime you can use `atStartOfDay()` or `atEndOfDay()` similar to **java.datetime**

```kt
val localDate = LocalDate(2023, 12, 17).atStartOfDay() //LocalDateTime(2023, 12, 17, 0, 0)
val localDate = LocalDate(2023, 12, 17).atEndOfDay() // LocalDateTime(2023, 12, 17, 23, 59, 59, 999999999)
```

To get the **min time**, **max time**, and **now**:
```kt
LocalTime.MIN
LocalTime.MAX

LocalDateTime.now()
LocalDate.now()
LocalTime.now()
```

** Note: `MAX` and `atEndOfDay()` value is inspired by what [java.datetime used to use](https://cs.android.com/android/platform/superproject/+/master:libcore/ojluni/src/main/java/java/time/LocalTime.java;drc=63ed7b354cbcc49d2f05037026921b59be49d342;l=128)

You can also get `Duration` between 2 `LocalDateTime` objects by using the infix function `durationUntil`
```kt
firstLocalDateTime durationUntil secondLocalDateTime
```

More examples can be found in the [UnitTests directory](https://github.com/RaedGhazal/kotlinx-datetime-ext/tree/main/shared/src/commonTest/kotlin/com/raedghazal/kotlinx_datetime_ext)

---
Thank you for using the library, contributions are welcomed!  
support me by a star-ing the repo and follow me on social media ❤️

[LinkedIn](https://www.linkedin.com/in/raed-o-ghazal/) • [Twitter](https://twitter.com/RaedOGhazal) • [Github](https://github.com/RaedGhazal)
