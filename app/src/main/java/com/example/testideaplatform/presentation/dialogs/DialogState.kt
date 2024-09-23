package com.example.testideaplatform.presentation.dialogs

import com.example.testideaplatform.domain.entity.ProductItem

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
     * @property currentProductItem - тек. элемент
     * @property buttonLabels - надписи для кнопок
     * @constructor Create empty Edit
     */
    data class Edit(
        val currentProductItem: ProductItem
    ) : DialogState()

    data class Delete(
        val currentProductItem: ProductItem
    ) : DialogState()
}
