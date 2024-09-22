package com.example.testideaplatform.data.repository

import com.example.testideaplatform.data.db.AppDao
import com.example.testideaplatform.data.mapper.DatabaseMapper
import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Репозиторий приложения.
 *
 * @property appDao - dao
 * @property dbMapper - маппер
 * @constructor Create empty App repository impl
 */
class AppRepositoryImpl @Inject constructor(
    val appDao: AppDao,
    val dbMapper: DatabaseMapper
) : AppRepository {

    private val _itemsFlow = flow {
        val items = appDao.getItems()
        items.collect {
            emit(dbMapper.itemsDataModelToItems(it))
        }
    }.flowOn(Dispatchers.IO)

    /**
     * Получение списка товаров в виде flow
     *
     * @return
     */
    override fun getItems(): Flow<List<Item>> = _itemsFlow

    /**
     * Обновление товара.
     *
     * @param item - товар
     */
    override suspend fun updateItem(item: Item) {
        appDao.updateItem(dbMapper.itemToItemDto(item))
    }

    /**
     * Удаление товара.
     *
     * @param item - товар
     */
    override suspend fun deleteItem(item: Item) {
        appDao.deleteItem(dbMapper.itemToItemDto(item))
    }

}