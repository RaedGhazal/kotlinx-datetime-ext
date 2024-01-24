[![Download](https://img.shields.io/maven-central/v/com.raedghazal/kotlinx_datetime_ext)](https://repo1.maven.org/maven2/com/raedghazal/kotlinx_datetime_ext)
[![Kotlin](https://img.shields.io/badge/kotlin-1.9.21-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Kotlinx-DateTime](https://img.shields.io/badge/kotlinx--datetime-0.5.0-blue)](https://github.com/Kotlin/kotlinx-datetime)
# kotlinx-datetime-ext
**A Kotlin Multiplatform library that provides `kotlinx-datetime` extensions and helper functions.**

If you've recently switched from **`java.time`** APIs to **`kotlinx-datetime`**, you've probably noticed that it's missing a lot of functionality that has to be implemented manually. This library comes to close the gap by providing most of `java.time`'s functionalities but in Kotlin!


### Supported platforms
- Android
- iOS
- JVM (Desktop)
- Js & WasmJs


### Implementation
```kt
implementation("com.raedghazal:kotlinx_datetime_ext:1.2.0")
```
If you're using it in `commonMain` and want to access it from `androidApp`, then use `api(...)` instead to expose it. [More about the difference between `implementation` and `api`](https://stackoverflow.com/a/44419574/10834775).


### Initialization (JS only)
No Initialization is needed, except if you're targeting JS or WasmJS, call `Locale.initPlatformLocales(...)` in your JS module to setup all the locales you're supporting, default is English Locale only

#### examples:
1. To support specific Locales
```kt
@JsModule("date-fns/locale/pl")
external object DateFnsLocalePl

@JsModule("date-fns/locale/it")
external object DateFnsLocaleIt

Locale.initPlatformLocales(DateFnsLocalePl, DateFnsLocaleIt) // support "pl" and "it" locales
```
2. To support all Locales
```kt
@JsModule("date-fns/locale")
external object DateFnsLocales

Locale.initPlatformLocales(DateFnsLocales) // support all locales 
```

**This function is only available in the JS module, and it will not be accessible in `commonMain`* .


## Usage

### 1. Math
Add or subtract date or time to a `LocalDateTime`
using operator functions `+` and `-` with duration values:
```kt
val localDateTime = LocalDateTime(2023, 1, 7, 21, 0)

val afterFiveDays = localDateTime + 5.days
val beforeThreeHours = localDateTime - 3.hours
```

Or using the `DateTimeUnit` overload:
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
There are overloads for all date/time objects:
```kt
fun format(localDateTime: LocalDateTime): String
fun format(localTime: LocalTime): String
fun format(localDate: LocalDate): String
```

#### Parsing from String
```kt
val formatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.en())
val localDateTime = formatter.parseToLocalDateTime("2023-01-01 01:01:00") // LocalDateTime(2023, 1, 1, 1, 1)
```
You can also parse to any date/time object
```kt
fun parseToLocalDateTime(str: String): LocalDateTime
fun parseToLocalDate(str: String): LocalDate
fun parseToLocalTime(str: String): LocalTime
```

### Helper extension functions

Transform a `LocalDate` to a `LocalDateTime`, you can use `atStartOfDay()` or `atEndOfDay()`, similar to **java.time**:
```kt
val localDate = LocalDate(2023, 12, 17).atStartOfDay() //LocalDateTime(2023, 12, 17, 0, 0)
val localDate = LocalDate(2023, 12, 17).atEndOfDay() // LocalDateTime(2023, 12, 17, 23, 59, 59, 999999999)
```

Get the current date/time:
```kt
LocalDateTime.now()
LocalDate.now()
LocalTime.now()
```

Get minimum/maximum time:
```kt
LocalTime.MIN
LocalTime.MAX
```

Get the `Duration` between two `LocalDateTime` objects using the `durationUntil` infix function:
```kt
firstLocalDateTime durationUntil secondLocalDateTime
```

More examples can be found in the [unit tests directory](https://github.com/RaedGhazal/kotlinx-datetime-ext/tree/main/shared/src/commonTest/kotlin/com/raedghazal/kotlinx_datetime_ext).

---
Thank you for using the library, contributions are welcomed!  
Support me by a starring the repo and follow me on social media ❤️

[LinkedIn](https://www.linkedin.com/in/raed-o-ghazal/) • [Twitter](https://twitter.com/RaedOGhazal) • [Github](https://github.com/RaedGhazal)
