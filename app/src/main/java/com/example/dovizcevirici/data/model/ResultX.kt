package com.example.dovizcevirici.data.model

import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("buying") val buying: String,
    @SerializedName("name") val name: String,
    @SerializedName("selling") val selling: String
)