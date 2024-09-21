package com.example.testideaplatform.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.testideaplatform.data.model.ItemDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM item")
    fun getItems(): Flow<List<ItemDataModel>>

    @Update
    suspend fun updateItem(itemDataModel: ItemDataModel)

    @Delete
    suspend fun deleteItem(itemDataModel: ItemDataModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(itemDataModel: ItemDataModel)
}