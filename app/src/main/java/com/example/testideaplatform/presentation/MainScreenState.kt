package com.example.testideaplatform.presentation

import com.example.testideaplatform.domain.entity.Item

sealed class MainScreenState {
    object Initial : MainScreenState()

    object Loading : MainScreenState()

    data class Items(
        val items: List<Item>,
    ) :
        MainScreenState()
}