package com.example.userapp.domain.useCases

import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.CartDataModels
import com.example.userapp.domain.models.CategoryDataModels

import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoryLimitUseCase@Inject constructor(private val repo: Repo)  {
    fun getCategoryLimitUseCase() : Flow<ResultState<List<CategoryDataModels>>> {

        return repo.getCategoriesInLimited()
    }


}