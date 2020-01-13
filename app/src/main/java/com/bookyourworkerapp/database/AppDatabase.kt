package com.bookyourworkerapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(com.bookyourworkerapp.database.FormEntity::class), version = 1
, exportSchema = false)
abstract class AppDatabase: RoomDatabase(){

    abstract fun doDao() : Dao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bookworker_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}

