package com.example.exxceliqsolutions.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.exxceliqsolutions.data.local.UserDatabase
import com.example.exxceliqsolutions.data.network.ApiService
import com.example.exxceliqsolutions.data.repository.RemoteDataSourceImpl
import com.example.exxceliqsolutions.data.repository.Repository
import com.example.exxceliqsolutions.domain.repository.RemoteDataSource
import com.example.exxceliqsolutions.domain.use_cases.GetAllUsersUseCase
import com.example.exxceliqsolutions.domain.use_cases.UseCase
import com.example.exxceliqsolutions.presentation.util.Constants.USER_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomData(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, UserDatabase::class.java, USER_DATABASE).build()


    @Singleton
    @Provides
    fun provideBorutoApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        borutoApi: ApiService, borutoDatabase: UserDatabase, @ApplicationContext context: Context
    ): RemoteDataSource {
        return RemoteDataSourceImpl(borutoApi, borutoDatabase, context)
    }

    @Provides
    @Singleton
    fun provideRepository(repository: Repository): UseCase {
        return UseCase(
            getAllUsersUseCase = GetAllUsersUseCase(repository)
        )
    }
}