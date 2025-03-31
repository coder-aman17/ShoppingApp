package com.example.userapp.domain.models

data class BannerDataModels(
    val name : String = "",
    val image : String = "",
    val time : Long = System.currentTimeMillis(),
)