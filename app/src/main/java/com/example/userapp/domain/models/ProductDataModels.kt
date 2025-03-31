package com.example.userapp.domain.models

import kotlinx.serialization.Serializable


@Serializable
data class ProductDataModels(
    val name : String = "",
    val description : String = "",
    val price : String = "",
    val finalPrice : String = "",
    val category : String = "",
    val image : String = "",
    val date : Long = System.currentTimeMillis(),
    val createdBy : String = "",
    val availableUnites : Int = 0,
    val productId : String = "",


    )