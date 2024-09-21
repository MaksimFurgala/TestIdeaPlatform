package com.example.testideaplatform.domain.repository

import com.example.testideaplatform.domain.entity.Item
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getItems(): Flow<List<Item>>

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)
}