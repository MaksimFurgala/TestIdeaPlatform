package com.example.testideaplatform.commons

import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DataConverter {

    private const val MILLISECONDS_PER_SECOND = 1000

    fun timestampToStringDate(timestamp: Int): String {
        val date = Date(timestamp.toLong() * MILLISECONDS_PER_SECOND)
        return SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(date)
    }

    fun getItemTagsFromString(tags: String): List<String> {
        return runCatching {
            Gson().fromJson(tags, Array<String>::class.java).asList()
        }.getOrDefault(emptyList<String>())
    }
}