package com.example.dovizcevirici.presentation.ui.currency

import com.example.dovizcevirici.data.model.GoldDto

data class CurrencyState(val isLoading: Boolean = false, val gold: GoldDto = GoldDto(), val error: String = "")
