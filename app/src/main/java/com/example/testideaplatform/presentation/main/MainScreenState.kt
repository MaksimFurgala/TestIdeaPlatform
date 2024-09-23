package com.example.testideaplatform.presentation.main

import com.example.testideaplatform.domain.entity.ProductItem

/**
 * State для главного экрана приложения.
 *
 * @constructor Create empty Main screen state
 */
sealed class MainScreenState {

    object Initial : MainScreenState()

    // Данные в состоянии загрузки.
    object Loading : MainScreenState()

    // Данные загружены.
    data class Items(
        val productItems: List<ProductItem>,
    ) :
        MainScreenState()
}