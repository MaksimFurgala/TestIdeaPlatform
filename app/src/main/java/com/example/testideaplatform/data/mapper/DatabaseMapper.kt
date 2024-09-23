package com.example.testideaplatform.data.mapper

import com.example.testideaplatform.data.model.ProductItemDataModel
import com.example.testideaplatform.domain.entity.ProductItem
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface DatabaseMapper {

    fun productItemToProductItemDataModel(productItem: ProductItem): ProductItemDataModel

    fun productItemDataModelToProductItem(productItemDataModel: ProductItemDataModel): ProductItem

    @IterableMapping(elementTargetType = ProductItem::class)
    fun itemsDataModelToItems(itemsDataModel: List<ProductItemDataModel>): List<ProductItem>
}