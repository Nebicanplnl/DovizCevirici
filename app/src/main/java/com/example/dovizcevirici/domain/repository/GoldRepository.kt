package com.example.dovizcevirici.domain.repository

import com.example.dovizcevirici.data.model.ConverterDto
import com.example.dovizcevirici.data.model.GoldDto


interface GoldRepository {
    suspend fun getGold(): GoldDto
    suspend fun getCurrency(): GoldDto
    suspend fun getAllConverter(base: String, to: String, int: Int): ConverterDto
}
