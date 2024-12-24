package com.example.dovizcevirici.data.repository

import com.example.dovizcevirici.data.api.GoldAPI
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.repository.GoldRepository
import javax.inject.Inject

class CurrenyRepositoryImpl @Inject constructor(private val currencyAPI : CurrencyAPI): CurrencyRepository {

}