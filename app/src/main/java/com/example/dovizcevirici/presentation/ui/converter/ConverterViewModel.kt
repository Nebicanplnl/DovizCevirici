package com.example.dovizcevirici.presentation.ui.converter

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dovizcevirici.domain.usecase.converter.ConverterUseCase
import com.example.dovizcevirici.domain.usecase.currency.CurrencyUseCase
import com.example.dovizcevirici.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
    val converterUseCase: ConverterUseCase): ViewModel() {


    private var _uiState = MutableStateFlow(ConverterState())
    val uiState: StateFlow<ConverterState> = _uiState.asStateFlow()
    var int = 0
    var base = "USD"
    var to = "EUR"

    fun getConverter() {
        viewModelScope.launch {
            currencyUseCase.getGold().onEach { requestState ->
                when (requestState) {
                    is Resources.Success -> {
                        val mappedList = requestState.data?.result?.map { item -> item.name }.orEmpty()

                        _uiState.value = ConverterState(spinnerList = mappedList)
                    }

                    is Resources.Error -> {
                        _uiState.value = ConverterState(error = requestState.message ?: "Error!")
                    }

                    is Resources.Loading -> {
                        _uiState.value = ConverterState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)

        }

    }
        fun getExchange() {
            viewModelScope.launch {
                converterUseCase.getConverter(base = base, to = to, int = int).onEach {
                    Log.e("ConverterViewModel", "getExchange: $it")
                }.launchIn(viewModelScope)
            }
        }

        fun getSpinnerData() {
            viewModelScope.launch {

            }
        }
    }
