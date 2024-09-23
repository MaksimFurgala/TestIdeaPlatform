package com.example.testideaplatform.domain.repository

import com.example.testideaplatform.domain.entity.ProductItem
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getItems(): Flow<List<ProductItem>>

    suspend fun updateItem(productItem: ProductItem)

    suspend fun deleteItem(productItem: ProductItem)
}