package com.example.userapp.domain.useCases

import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.CartDataModels
import com.example.userapp.domain.models.ProductDataModels

import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavUseCase@Inject constructor(private val repo: Repo)  {
    fun getAllFavUseCase() : Flow<ResultState<List<ProductDataModels>>> {

        return repo.getAllFav()
    }


}