package com.bookyourworkerapp.di

import com.bookyourworkerapp.database.AppDatabase
import com.bookyourworkerapp.repository.FormRepository
import com.bookyourworkerapp.view.adapter.FormAdapter
import com.bookyourworkerapp.viewmodel.FormViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module { single{
                AppDatabase.getDatabase(
                    context = get()
                ) }
        factory{
            get<AppDatabase>().doDao() }
}

val repositoryModule = module { single{
                FormRepository(get()) }
}

val uiModule = module {
    factory { FormAdapter() }
    viewModel { FormViewModel(get()) }
}