package com.example.exxceliqsolutions.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@DisableInstallInCheck
object LoggingInterceptorModule {
    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}