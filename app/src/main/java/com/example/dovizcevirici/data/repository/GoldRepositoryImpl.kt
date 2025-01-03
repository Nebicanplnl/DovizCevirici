package com.example.dovizcevirici.data.repository

import com.example.dovizcevirici.data.api.GoldAPI
import com.example.dovizcevirici.data.model.ConverterDto
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.repository.GoldRepository
import javax.inject.Inject

class GoldRepositoryImpl @Inject constructor(private val goldAPI : GoldAPI ): GoldRepository {
    override suspend fun getGold(): GoldDto {
    return goldAPI.getGold()
    }

    override suspend fun getCurrency(): GoldDto {
       return goldAPI.getAllCurrency()
    }

    override suspend fun getAllConverter(base: String, to: String, int: Int): ConverterDto {
        return goldAPI.getAllConverter(base = base, to = to, int = int)
    }
}

