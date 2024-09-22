package com.example.testideaplatform.presentation

import com.example.testideaplatform.domain.entity.Item

/**
 * State для диалогового окна.
 *
 * @constructor Create empty Dialog state
 */
sealed class DialogState {
    open val buttonLabels: Array<String> = emptyArray()

    object Initial : DialogState()

    /**
     * Диалог редактирования.
     *
     * @property currentItem - тек. элемент
     * @property buttonLabels - надписи для кнопок
     * @constructor Create empty Edit
     */
    data class Edit(
        val currentItem: Item,
        override val buttonLabels: Array<String> = arrayOf("Принять", "Отмена")
    ) : DialogState()

    data class Delete(
        val currentItem: Item,
        override val buttonLabels: Array<String> = arrayOf("Да", "Нет")
    ) : DialogState()
}
