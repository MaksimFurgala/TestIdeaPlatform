package com.example.testideaplatform.domain.usecases

import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.repository.AppRepository
import javax.inject.Inject

/**
 * UseCase для обновления товара.
 *
 * @property repository - репозиторий.
 * @constructor Create empty Update item use case
 */
class UpdateItemUseCase @Inject constructor(val repository: AppRepository) {

    suspend operator fun invoke(item: Item) {
        repository.updateItem(item)
    }
}