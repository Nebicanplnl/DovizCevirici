package com.example.dovizcevirici.data.repository

import com.example.dovizcevirici.data.api.GoldAPI
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.model.Gold
import com.example.dovizcevirici.domain.repository.GoldRepository
import com.example.dovizcevirici.utils.Resources
import retrofit2.Response
import javax.inject.Inject

class GoldRepositoryImpl @Inject constructor(private val goldAPI : GoldAPI): GoldRepository {
    override suspend fun getGold(): GoldDto {
    return goldAPI.getGold()
    }

    override suspend fun getCurrency(): GoldDto {
       return goldAPI.getAllCurrency()
    }

    override suspend fun getAllCurrency(): GoldDto {
        return goldAPI.getAllConverter()
    }
}

