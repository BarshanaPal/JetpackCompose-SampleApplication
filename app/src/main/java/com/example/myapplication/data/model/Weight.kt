package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Weight(
    val imperial: String?=null,
    val metric: String?=null
)