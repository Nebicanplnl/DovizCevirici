package com.example.dovizcevirici.domain.repository

import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.model.Gold
import retrofit2.Response

interface GoldRepository {
    suspend fun getGold(): GoldDto
    suspend fun getCurrency(): GoldDto
}
