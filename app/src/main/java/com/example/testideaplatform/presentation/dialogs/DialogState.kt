package com.example.testideaplatform.presentation.dialogs

import com.example.testideaplatform.domain.entity.Item

/**
 * State для диалогового окна.
 *
 * @constructor Create empty Dialog state
 */
sealed class DialogState {

    object Initial : DialogState()

    /**
     * Диалог редактирования.
     *
     * @property currentItem - тек. элемент
     * @property buttonLabels - надписи для кнопок
     * @constructor Create empty Edit
     */
    data class Edit(
        val currentItem: Item
    ) : DialogState()

    data class Delete(
        val currentItem: Item
    ) : DialogState()
}
