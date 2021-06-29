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
    SLOPE_REQUEST {
        override fun toString(): String {
            return "/slope/request"
        }
    },
    ACCEL_REQUEST {
        override fun toString(): String {
            return "/accel/request"
        }
    }
}