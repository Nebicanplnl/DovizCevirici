package com.example.dovizcevirici.presentation.ui.gold

import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.model.Gold

data class GoldState(val isLoading: Boolean = false, val gold: GoldDto = GoldDto(), val error: String = "")
