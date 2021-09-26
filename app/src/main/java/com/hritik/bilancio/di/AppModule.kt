package com.hritik.bilancio.di

import android.app.Application
import android.content.Context
import com.hritik.bilancio.BaseApplication
import com.hritik.bilancio.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context) : BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideTransactionDatabase(application: Application) :AppDatabase{
        return  AppDatabase.invoke(application.applicationContext)
    }
}