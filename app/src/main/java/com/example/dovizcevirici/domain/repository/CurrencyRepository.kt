package com.example.dovizcevirici.domain.repository

import com.example.dovizcevirici.data.model.CurrencyDto

interface CurrencyRepository {
    suspend fun getCurrency(): CurrencyDto
}