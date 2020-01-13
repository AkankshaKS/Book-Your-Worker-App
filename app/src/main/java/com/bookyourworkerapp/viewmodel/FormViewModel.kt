package com.bookyourworkerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bookyourworkerapp.database.FormEntity
import com.bookyourworkerapp.repository.FormRepository

class FormViewModel(private var repository: FormRepository) : ViewModel() {

    private var allForms: LiveData<List<FormEntity>> = repository.getAllForms()

    fun insert(formEntity: FormEntity) {
        repository.insert(formEntity)
    }

    fun deleteAll() {
        repository.deleteAllForms()
    }

    fun getAllForms(): LiveData<List<FormEntity>> {
        return allForms
    }
}