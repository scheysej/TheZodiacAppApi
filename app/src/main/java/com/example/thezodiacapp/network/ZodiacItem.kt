package com.example.thezodiacapp.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ZodiacItem(
    val sign: String,
    val title: String
)
