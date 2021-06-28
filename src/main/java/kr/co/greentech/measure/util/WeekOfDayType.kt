package kr.co.greentech.measure.util

enum class WeekOfDayType(val value: Int) {
    SUNDAY(0),
    MONDAY(1),
    TUESDAY(2),
    WEDNESDAY(3),
    THURSDAY(4),
    FRIDAY(5),
    SATURDAY(6);

    companion object {
        fun fromInt(value: Int) = values().first { it.value == value }
    }

    fun getShortTitle(): String {
        return when(this) {
            SUNDAY -> "일"
            MONDAY -> "월"
            TUESDAY -> "화"
            WEDNESDAY -> "수"
            THURSDAY -> "목"
            FRIDAY -> "금"
            SATURDAY -> "토"
        }
    }

    fun getTitle(): String {
        return getShortTitle() + "요일"
    }

}