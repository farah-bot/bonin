package com.bonin.core.network

import com.bonin.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideJson(): Json {

        return Json {
            ignoreUnknownKeys = true
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor():
        HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level = if (BuildConfig.DEBUG) {

                HttpLoggingInterceptor.Level.BASIC

            } else {

                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor:
        HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient
            .Builder()
            .addInterceptor(
                loggingInterceptor
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit {

        val contentType =
            "application/json".toMediaType()

        return Retrofit
            .Builder()
            .baseUrl(
                BuildConfig.API_BASE_URL
            )
            .client(
                okHttpClient
            )
            .addConverterFactory(
                json.asConverterFactory(
                    contentType
                )
            )
            .build()
    }
}