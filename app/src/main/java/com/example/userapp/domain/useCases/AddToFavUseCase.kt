package com.example.userapp.domain.useCases

import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.ProductDataModels
import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddToFavUseCase@Inject constructor(private val repo: Repo)   {
    fun addToFav(productDataModels: ProductDataModels) : Flow<ResultState<String>> {
        return repo.addToFav(productDataModels)
    }

    }
