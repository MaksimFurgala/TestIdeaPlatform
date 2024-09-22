package com.example.testideaplatform.commons

import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Конвертер для данных из БД.
 *
 * @constructor Create empty Data converter
 */
object DataConverter {

    private const val MILLISECONDS_PER_SECOND = 1000

    /**
     * Конвертер из timestamp в строковый формат.
     *
     * @param timestamp - время в формате timestamp
     * @return - отформатированное время
     */
    fun timestampToStringDate(timestamp: Int): String {
        val date = Date(timestamp.toLong() * MILLISECONDS_PER_SECOND)
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