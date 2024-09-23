package com.example.testideaplatform.presentation.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.usecases.DeleteItemUseCase
import com.example.testideaplatform.domain.usecases.GetItemsUseCase
import com.example.testideaplatform.domain.usecases.UpdateItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
) : ViewModel() {

    private val itemsFlow = getItemsUseCase()

    val screenState = itemsFlow
        .map { MainScreenState.Items(items = it) as MainScreenState }
        .onStart { emit(MainScreenState.Loading) }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            updateItemUseCase(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            deleteItemUseCase(item)
        }
    }
}