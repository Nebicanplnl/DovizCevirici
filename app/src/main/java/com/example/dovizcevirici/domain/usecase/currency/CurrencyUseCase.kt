package com.example.dovizcevirici.domain.usecase.currency

import com.example.dovizcevirici.data.model.CurrencyDto
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.repository.CurrencyRepository
import com.example.dovizcevirici.domain.repository.GoldRepository
import com.example.dovizcevirici.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyUseCase@Inject constructor(private val repository: CurrencyRepository) {

        fun getCurrency(): Flow<Resources<CurrencyDto>> = flow {
            try {
                emit(Resources.Loading())
                val currency = repository.getCurrency()
                if (currency.success){
                    emit(Resources.Success(currency))
                }else{
                    emit(Resources.Error(message = "Gold Error"))
                }
            }catch (e: HttpException) {
                emit(Resources.Error(message = e.localizedMessage ?: "Gold Error"))
            }catch (e: IOException) {
                emit(Resources.Error(message = e.localizedMessage ?: "Internet Error"))
            }
        }
    }