package com.example.testideaplatform.data.mapper

import com.example.testideaplatform.data.model.ItemDataModel
import com.example.testideaplatform.domain.entity.Item
import org.mapstruct.IterableMapping
import org.mapstruct.Mapper

@Mapper
interface DatabaseMapper {
    fun itemToItemDto(item: Item): ItemDataModel
    fun itemDtoToItem(itemDataModel: ItemDataModel): Item

    @IterableMapping(elementTargetType = ItemDataModel::class)
    fun itemsToItemsDataModel(items: List<Item>): List<ItemDataModel>

    @IterableMapping(elementTargetType = Item::class)
    fun itemsDataModelToItems(itemsDataModel: List<ItemDataModel>): List<Item>
}