package com.example.dovizcevirici.presentation.ui.gold

import com.example.dovizcevirici.data.model.GoldDto

data class GoldState(val isLoading: Boolean = false, val gold: GoldDto = GoldDto(), val error: String = "")
