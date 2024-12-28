package com.example.dovizcevirici.data.mapper

import com.example.dovizcevirici.data.model.GoldDto
import com.example.dovizcevirici.domain.model.Gold

class GoldMapper {

    fun GoldDto.toGold(): List<Gold> {
        return result.map {
            Gold(
                buy = it.buy,
                name = it.name,
                sell = it.sell,
                code = it.code
            )
        }
    }
}