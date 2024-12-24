package com.example.dovizcevirici.common

fun String.formatAsTwoDecimal(): String {
    return try {
        String.format("%.3f", this.toDouble()) // String -> Double -> 2 Ondalık Basamak
    } catch (e: Exception) {
        "N/A" // Eğer çevrilemezse varsayılan değer
    }
}