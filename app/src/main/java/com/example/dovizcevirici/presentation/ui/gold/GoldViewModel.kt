package com.example.dovizcevirici.presentation.ui.gold

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.usecase.gold.GoldUseCase
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
class GoldViewModel @Inject constructor(private val goldUseCase: GoldUseCase): ViewModel() {

    private var _uiState = MutableStateFlow(GoldState())
    val uiState: StateFlow<GoldState> = _uiState.asStateFlow()

    init {
        getGolds()
    }

    private var job: Job? = null

    private fun getGolds() {
        job?.cancel()
        job = goldUseCase.getGold().onEach {
            when(it) {
                is Resources.Success -> {
                    _uiState.value = GoldState(gold = it.data ?: GoldDto())
                }
                is Resources.Error -> {
                    _uiState.value = GoldState(error = it.message ?: "Error!")
                }
                is Resources.Loading -> {
                    _uiState.value = GoldState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}