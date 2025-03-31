package com.example.userapp.common

import com.example.userapp.domain.models.BannerDataModels
import com.example.userapp.domain.models.CategoryDataModels
import com.example.userapp.domain.models.ProductDataModels

data class HomeScreenState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val categories: List<CategoryDataModels>? = null,
    val products: List<ProductDataModels>? = null,
    val banners : List<BannerDataModels>? = null

    )