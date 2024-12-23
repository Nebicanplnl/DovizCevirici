package com.example.dovizcevirici.di

import com.example.dovizcevirici.common.Constants.BASE_URL
import com.example.dovizcevirici.data.api.GoldAPI
import com.example.dovizcevirici.data.repository.GoldRepositoryImpl
import com.example.dovizcevirici.domain.repository.GoldRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GoldModule {

    @Singleton
    @Provides
    fun provideRepository(goldAPI: GoldAPI): GoldRepository {
        return GoldRepositoryImpl(goldAPI)
    }

    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        interceptor: Interceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(
        okHttpClient: OkHttpClient
    ): GoldAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoldAPI::class.java)
    }
}