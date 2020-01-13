package com.bookyourworkerapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao


@Dao
interface Dao{

    @Insert
    fun insert(form: FormEntity)

    @Query("DELETE FROM form_table")
    fun deleteAll()

    @Query("SELECT * FROM form_table ")
    fun getAll(): LiveData<List<FormEntity>>

    @Delete
    fun delete(todo: FormEntity)

    @Update
    fun updateTodo(vararg todos: FormEntity)
}