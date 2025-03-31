package com.example.userapp.domain.useCases

import android.net.Uri
import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.CartDataModels

import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserProfileImageUseCase@Inject constructor(private val repo: Repo)  {
    fun userProfileImage(uri : Uri) : Flow<ResultState<String>> {

        return repo.userProfileImage(uri)
    }


}