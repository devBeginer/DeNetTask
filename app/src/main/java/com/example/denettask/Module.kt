package com.example.denettask

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {}

val viewModelModule = module {

    single {
        TreeViewModel(get())
    }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), AppDB::class.java, "tree")
            .build()
    }
    single {
        get<AppDB>().dao()
    }
}

val repositoryModule = module {
    single { Repository(get()) }
}