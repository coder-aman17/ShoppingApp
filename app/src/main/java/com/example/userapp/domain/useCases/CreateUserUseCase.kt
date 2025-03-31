package com.example.userapp.domain.useCases

import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.UserData

import com.example.userapp.domain.repo.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateUserUseCase@Inject constructor(private val repo: Repo)  {
    fun createUser(userData: UserData) : Flow<ResultState<String>> {

        return repo.registerUserWithEmailAndPassword(userData)
    }


}