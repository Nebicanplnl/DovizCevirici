package com.example.dovizcevirici.data.model

import com.example.dovizcevirici.domain.model.Gold

data class GoldDto(
    val result: List<Result> = emptyList(),
    val success: Boolean = false
)

fun GoldDto.toGold(): List<Gold> {
    return result.map {
        Gold(
            buy = it.buy.toString(),
            name = it.name,
            sell = it.sell.toString()
        )
    }
}

