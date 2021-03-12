package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Height(
    val imperial: String?=null,
    val metric: String?=null
)