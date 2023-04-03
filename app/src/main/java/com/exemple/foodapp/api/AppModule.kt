package com.exemple.foodapp.api

import android.content.Context
import com.exemple.foodapp.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun applicationProvider(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

}