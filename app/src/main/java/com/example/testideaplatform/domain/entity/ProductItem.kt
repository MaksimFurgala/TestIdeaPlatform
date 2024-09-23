package com.example.testideaplatform.domain.entity

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
data class ProductItem(
    val id: Int,
    val name: String,
    val time: Int,
    val tags: String,
    val amount: Int,
)
