package com.example.dovizcevirici.domain.model

import com.google.gson.annotations.SerializedName

data class Gold(
    @SerializedName("buy") val buy: String,
    @SerializedName("name") val name: String,
    @SerializedName("sell") val sell: String,
    @SerializedName("code") val code: String? = null

)
