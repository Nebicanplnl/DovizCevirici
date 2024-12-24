package com.example.dovizcevirici.presentation.ui.currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.usecase.currency.CurrencyUseCase
import com.example.dovizcevirici.presentation.ui.gold.GoldState
import com.example.dovizcevirici.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(private val currencyUseCase: CurrencyUseCase): ViewModel() {

    private var _uiState = MutableStateFlow(CurrencyState())
    val uiState: StateFlow<CurrencyState> = _uiState.asStateFlow()

    init {
        getCurrency()
    }

    private var job: Job? = null

    private fun getCurrency() {
        job?.cancel()
        job = currencyUseCase.getGold().onEach {
            when(it) {
                is Resources.Success -> {
                    _uiState.value = CurrencyState(gold = it.data ?: GoldDto())
                }
                is Resources.Error -> {
                    _uiState.value = CurrencyState(error = it.message ?: "Error!")
                }
                is Resources.Loading -> {
                    _uiState.value = CurrencyState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}