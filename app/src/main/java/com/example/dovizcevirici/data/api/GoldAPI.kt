package com.example.dovizcevirici.data.api

import com.example.dovizcevirici.common.Constants.API_KEY
import com.example.dovizcevirici.common.Constants.CONTENT_TYPE
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.model.Gold
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface GoldAPI {
    @GET("economy/goldPrice")
    suspend fun getGold(
        @Header("content-type") type: String = CONTENT_TYPE,
        @Header("authorization") apiKey: String = API_KEY
    ): GoldDto
}