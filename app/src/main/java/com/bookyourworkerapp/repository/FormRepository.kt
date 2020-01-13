package com.bookyourworkerapp.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.bookyourworkerapp.database.Dao
import com.bookyourworkerapp.database.FormEntity

class FormRepository ( private var formDao : Dao){

    private var allForm: LiveData<List<FormEntity>> = formDao.getAll()

    fun insert(form: FormEntity) {
        InsertFormAsyncTask(
            formDao
        ).execute(form)
    }

    fun deleteAllForms() {
        DeleteAllFormsAsyncTask(
            formDao
        ).execute()
    }

    fun getAllForms(): LiveData<List<FormEntity>> {
        return allForm
    }

    private class InsertFormAsyncTask(val formDao: Dao) : AsyncTask<FormEntity, Unit, Unit>() {

        override fun doInBackground(vararg form: FormEntity?) {
            formDao.insert(form[0]!!)
        }
    }


    private class DeleteAllFormsAsyncTask(val formDao: Dao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            formDao.deleteAll()
        }
    }



}