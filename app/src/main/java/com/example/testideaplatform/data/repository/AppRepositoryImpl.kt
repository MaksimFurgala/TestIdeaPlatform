package com.example.testideaplatform.data.repository

import com.example.testideaplatform.data.db.AppDao
import com.example.testideaplatform.data.mapper.DatabaseMapper
import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    val appDao: AppDao,
    val dbMapper: DatabaseMapper
) : AppRepository {
    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val _itemsFlow = flow {
        val items = appDao.getItems()
        items.collect {
            emit(dbMapper.itemsDataModelToItems(it))
        }
    }.flowOn(Dispatchers.IO)

    override fun getItems(): Flow<List<Item>> = _itemsFlow

    override suspend fun updateItem(item: Item) {
        appDao.updateItem(dbMapper.itemToItemDto(item))
    }

    override suspend fun deleteItem(item: Item) {
        appDao.deleteItem(dbMapper.itemToItemDto(item))
    }

}