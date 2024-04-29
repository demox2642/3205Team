package com.example.a3205team.di

import android.content.Context
import com.example.database.db.Database
import com.example.history.repository.HistoryRepository
import com.example.history.repository.HistoryRepositoryImpl
import com.example.search.api.SearchApi
import com.example.search.repository.SearchRepository
import com.example.search.repository.SearchRepositoryImpl
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30L, TimeUnit.SECONDS)
            .addNetworkInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY),
            )
            .followRedirects(true)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit.Builder {
        val gson =
            GsonBuilder()
                .setLenient()
                .create()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.urlBase)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideSearchApi(retrofit: Retrofit.Builder): SearchApi {
        return retrofit.build()
            .create(SearchApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchRepository(
        database: Database,
        @ApplicationContext context: Context,
        searchApi: SearchApi,
    ): SearchRepository {
        return SearchRepositoryImpl(database, context, searchApi)
    }

    @Singleton
    @Provides
    fun provideHistoryRepository(database: Database): HistoryRepository {
        return HistoryRepositoryImpl(database)
    }
}
