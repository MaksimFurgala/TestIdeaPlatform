package com.example.testideaplatform.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.testideaplatform.data.model.ProductItemDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<ProductItemDataModel>>

    @Update
    suspend fun updateItem(productItemDataModel: ProductItemDataModel)

    @Delete
    suspend fun deleteItem(productItemDataModel: ProductItemDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(productItemDataModel: ProductItemDataModel)
}