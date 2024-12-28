package com.example.dovizcevirici.presentation.ui.converter

import com.example.dovizcevirici.data.model.GoldDto

data class ConverterState(
    val isLoading: Boolean = false,
    val gold: GoldDto = GoldDto(),
    val error: String = "",
    val spinnerList: List<String> = emptyList()
)

