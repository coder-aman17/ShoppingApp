package com.example.userapp.domain.repo

import android.net.Uri
import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.BannerDataModels
import com.example.userapp.domain.models.CartDataModels
import com.example.userapp.domain.models.CategoryDataModels
import com.example.userapp.domain.models.ProductDataModels
import com.example.userapp.domain.models.UserData
import com.example.userapp.domain.models.UserDataParents
import kotlinx.coroutines.flow.Flow

interface Repo {
    fun registerUserWithEmailAndPassword(UserData: UserData): Flow<ResultState<String>>
    fun loginUserWithEmailAndPassword(UserData: UserData): Flow<ResultState<String>>
    fun getUserById(uid: String): Flow<ResultState<UserDataParents>>
    fun updateUserData(userDataParents: UserDataParents): Flow<ResultState<String>>
    fun userProfileImage(uid: Uri): Flow<ResultState<String>>
    fun getCategoriesInLimited(): Flow<ResultState<List<CategoryDataModels>>>
    fun getProductsInLimited(): Flow<ResultState<List<ProductDataModels>>>
    fun getAllProducts(): Flow<ResultState<List<ProductDataModels>>>
    fun getProductsById(productId: String): Flow<ResultState<ProductDataModels>>
    fun addToCart(cartDataModels: CartDataModels): Flow<ResultState<String>>
    fun addToFav(productDataModels: ProductDataModels): Flow<ResultState<String>>
    fun getAllFav() : Flow<ResultState<List<ProductDataModels>>>
    fun getCart() : Flow<ResultState<List<CartDataModels>>>
    fun getAllCategories() : Flow<ResultState<List<CategoryDataModels>>>
    fun getCheckOut(productId: String): Flow<ResultState<List<ProductDataModels>>>
    fun getBanner() : Flow<ResultState<List<BannerDataModels>>>
    fun getSpecificCategoryItems(category: String) : Flow<ResultState<List<ProductDataModels>>>
    fun getAllSuggestedProducts() : Flow<ResultState<List<ProductDataModels>>>




}