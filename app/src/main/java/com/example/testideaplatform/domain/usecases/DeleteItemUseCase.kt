package com.example.testideaplatform.domain.usecases

import com.example.testideaplatform.domain.entity.ProductItem
import com.example.testideaplatform.domain.repository.AppRepository
import javax.inject.Inject

/**
 * UseCase для удаления товара.
 *
 * @property repository - репозиторий
 * @constructor Create empty Delete item use case
 */
class DeleteItemUseCase @Inject constructor(val repository: AppRepository) {

    suspend operator fun invoke(productItem: ProductItem) {
        repository.deleteItem(productItem)
    }
}