package com.example.userapp.domain.useCases

import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.ProductDataModels

import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCheckOutUseCase@Inject constructor(private val repo: Repo)  {
    fun getCheckOutUseCase(productId : String) : Flow<ResultState<ProductDataModels>> {

        return repo.getCheckOut(productId)
    }


}