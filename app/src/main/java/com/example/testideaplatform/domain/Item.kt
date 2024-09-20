package com.example.testideaplatform.domain

/**
 * Модель для представления элемента в списке товаров.
 *
 * @property id - id
 * @property name - наименование
 * @property time - время
 * @property tags - список тегов
 * @property amount - количество
 * @constructor Create empty Item
 */
data class Item(
    val id: Long,
    val name: String,
    val time: Long,
    val tags: String,
    val amount: Long,
)
