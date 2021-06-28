package kr.co.greentech.measure.util

import java.io.File
import java.util.*

object FileUtil {

    fun deleteFiles(date: Date, path: String) {
        val deleteDate = date.prevWeek.startOfWeek

        for (i in -14 until 0) {
            val nextDate = deleteDate.getNextDay(i)
            val file = File(path + "/" + date.toString_yyyyMMdd())
            removeDir(file)
        }
    }

    fun getHourString(): String {
        return Date().toString_HH_mm()
    }

    private fun removeDir(file: File) {
        val childFileList = file.listFiles()

        if (childFileList != null) {
            for (childFile in childFileList) {
                if (childFile.isDirectory) {
                    removeDir(childFile)
                } else {
                    childFile.delete()
                }
            }
        }

        file.delete()
    }

    fun getTimeString(): String {
        return "/" + Date().toString_yyyyMMdd()
    }

    fun getTimeString(date: Date): String {
        return "/" + date.toString_yyyyMMdd()
    }

    fun startOfDay(date: Date): Date {
        return date.startOfDay
    }

    fun endOfDay(date: Date): Date {
        return date.endOfDay
    }
}