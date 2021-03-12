package com.example.myapplication.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DogBreedItem(
    val bred_for: String?=null,
    val breed_group: String?=null,
    val country_code: String?=null,
    val description: String?=null,
    val height: Height?=null,
    val history: String?=null,
    val id: Int?=null,
    val life_span: String?=null,
    val name: String?=null,
    val origin: String?=null,
    val temperament: String?=null,
    val url: String?=null,
    val weight: Weight?=null
)