package com.example.dovizcevirici.presentation.ui.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dovizcevirici.domain.usecase.currency.CurrencyUseCase
import com.example.dovizcevirici.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(private val currencyUseCase: CurrencyUseCase): ViewModel() {

    private var _uiState = MutableStateFlow(ConverterState())
    val uiState: StateFlow<ConverterState> = _uiState.asStateFlow()

    fun getCurrency() {
        viewModelScope.launch {
            currencyUseCase.getGold().onEach {
                when (it) {
                    is Resources.Success -> {
                        val x = it.data?.result?.map { item -> item.name }.orEmpty()

                        _uiState.value = ConverterState(spinnerList = x)
                    }
                    is Resources.Error -> {
                        _uiState.value = ConverterState(error = it.message ?: "Error!")
                    }
                    is Resources.Loading -> {
                        _uiState.value = ConverterState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)

        }

        fun getSpinnerData() {
            viewModelScope.launch {

            }
        }
    }
}