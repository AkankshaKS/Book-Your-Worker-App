package com.bookyourworkerapp.database

import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao{

    @Query("SELECT * FROM Entity")
    fun getAll(): List<Entity>

    @Insert
    fun insertAll(vararg toDo: Entity)

    @Delete
    fun delete(todo: Entity)

    @Update
    fun updateTodo(vararg todos: Entity)
}