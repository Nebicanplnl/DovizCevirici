package com.example.dovizcevirici.domain.usecase.converter

import com.example.dovizcevirici.data.model.ConverterDto
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.repository.GoldRepository
import com.example.dovizcevirici.utils.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ConverterUseCase@Inject constructor(private val repository: GoldRepository)  {
    fun getGold():Flow<Resources<ConverterDto>> = flow {
        try {
            emit(Resources.Loading())
            val gold = repository.getAllConverter()
            if (gold.success){
                emit(Resources.Success(gold))
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