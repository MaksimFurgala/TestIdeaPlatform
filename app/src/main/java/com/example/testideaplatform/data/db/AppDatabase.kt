package com.example.testideaplatform.data.db

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testideaplatform.data.model.ProductItemDataModel

@Database(
    entities = [ProductItemDataModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        private var INSTANSE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "data.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANSE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANSE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).createFromAsset("data.db")
                    .build()
                INSTANSE = db
                return db
            }
        }
    }


}