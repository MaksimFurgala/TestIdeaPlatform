package com.example.testideaplatform.commons

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

/**
 * Конвертер для данных из БД.
 *
 * @constructor Create empty Data converter
 */
object DataConverter {

    /**
     * Конвертер из timestamp в строковый формат.
     *
     * @param timestamp - время в формате timestamp
     * @return - отформатированное время
     */
    fun timestampToStringDate(timestamp: Long): String {
        val date = Date(Timestamp(timestamp).time)
        return SimpleDateFormat("dd.MM.yy", Locale.getDefault()).format(date)
    }

    /**
     * Конвертер для получения списка тегов товара из строкового представления (jsonArray).
     *
     * @param tags - теги
     * @return - список тегов
     */
    fun getItemTagsFromString(tags: String): List<String> {
        return runCatching {
            Gson().fromJson(tags, Array<String>::class.java).asList()
        }.getOrDefault(emptyList<String>())
    }
}