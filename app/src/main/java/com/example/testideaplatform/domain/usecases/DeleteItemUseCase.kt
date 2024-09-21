package com.example.testideaplatform.domain.usecases

import com.example.testideaplatform.domain.entity.Item
import com.example.testideaplatform.domain.repository.AppRepository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(val repository: AppRepository) {

    suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}