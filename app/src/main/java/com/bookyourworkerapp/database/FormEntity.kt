package com.bookyourworkerapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "form_table")
data class FormEntity(val title: String, val description: String, val budget : Int,
                      val rate: String, val paymentmethod: String, val date: Int, val jobTerm: String,
                      val attachments: Int)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}