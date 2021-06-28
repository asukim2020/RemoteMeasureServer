package kr.co.greentech.measure.service

enum class FilePath {
    SLOPE {
        override fun toString(): String {
            return "/slope"
        }
    },
    ACCEL {
        override fun toString(): String {
            return "/accel"
        }
    },
    TRIGGER {
        override fun toString(): String {
            return "/trigger"
        }
    },
    REQUEST {
        override fun toString(): String {
            return "/request"
        }
    }
}