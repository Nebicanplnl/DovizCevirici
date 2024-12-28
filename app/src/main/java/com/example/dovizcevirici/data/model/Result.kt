package com.example.dovizcevirici.data.model

import com.google.gson.annotations.SerializedName


data class Result(
   @SerializedName("buying") val buy: String,
   @SerializedName("name")val name: String,
   @SerializedName("selling") val sell: String,
   @SerializedName("code") val code: String? = null
)

