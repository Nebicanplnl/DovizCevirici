package com.example.dovizcevirici.data.api

import com.example.dovizcevirici.common.Constants.API_KEY
import com.example.dovizcevirici.common.Constants.CONTENT_TYPE
import com.example.dovizcevirici.data.model.ConverterDto
import com.example.dovizcevirici.data.model.GoldDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GoldAPI {
    @GET("economy/goldPrice")
    suspend fun getGold(
        @Header("content-type") type: String = CONTENT_TYPE,
        @Header("authorization") apiKey: String = API_KEY
    ): GoldDto

    @GET("economy/allCurrency")
    suspend fun getAllCurrency(
        @Header("content-type") type: String = CONTENT_TYPE,
        @Header("authorization") apiKey: String = API_KEY
    ): GoldDto

    @GET("economy/exchange")// ?int=10&to=EUR&base=USD
    suspend fun getAllConverter(
        @Header("content-type") type: String = CONTENT_TYPE,
        @Header("authorization") apiKey: String = API_KEY,
        @Query("int") int: Int,
        @Query("to") to: String,
        @Query("base") base: String,
    ): ConverterDto
}
