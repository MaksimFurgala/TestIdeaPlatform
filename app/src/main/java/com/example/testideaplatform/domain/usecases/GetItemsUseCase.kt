package com.example.testideaplatform.domain.usecases

import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * UseCase для получения товара.
 *
 * @property repository - репозиторий
 * @constructor Create empty Get items use case
 */
class GetItemsUseCase @Inject constructor(val repository: AppRepository) {

    operator fun invoke(): Flow<List<Item>> {
        return repository.getItems()
    }
}