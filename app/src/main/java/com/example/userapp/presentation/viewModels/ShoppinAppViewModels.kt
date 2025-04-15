package com.example.userapp.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.common.HomeScreenState
import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.CategoryDataModels
import com.example.userapp.domain.models.ProductDataModels
import com.example.userapp.domain.models.UserDataParents
import com.example.userapp.domain.useCases.AddToCartUseCase
import com.example.userapp.domain.useCases.AddToFavUseCase
import com.example.userapp.domain.useCases.CreateUserUseCase
import com.example.userapp.domain.useCases.GetAllProductUseCase
import com.example.userapp.domain.useCases.GetBannerUseCase
import com.example.userapp.domain.useCases.GetCategoryLimitUseCase
import com.example.userapp.domain.useCases.GetSpecificCategoryItems
import com.example.userapp.domain.useCases.GetUserUseCase
import com.example.userapp.domain.useCases.LoginUserUseCase
import com.example.userapp.domain.useCases.UpdateUserDataUseCase
import com.example.userapp.domain.useCases.UserProfileImageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserAppViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val addToFavUseCase: AddToFavUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAllCategoriesState: GetAllCategoriesState,
    private val getAllFavState: GetAllFavState,
    private val getAllProductState: GetAllProductState,
    private val getAllSuggestedProductsState: GetAllSuggestedProductsState,
    private val getBannerUseCase: GetBannerUseCase,
    private val getCartState: GetCartState,
    private val getCategoriesInLimitedUseCase: GetCategoryLimitUseCase,
    private val getCheckOutState: GetCheckOutState,
    private val getSpecificCategoryItemsState: GetSpecificCategoryItems,
    private val getUserUseCase: GetUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val userProfileImageUseCase: UserProfileImageUseCase

):ViewModel(){

    private val _signUpScreenState = MutableStateFlow(SignUpScreenState())
    val signUpScreenState = _signUpScreenState.asStateFlow()

    private val _loginScreenState = MutableStateFlow(LoginScreenState())
    val loginScreenState = _loginScreenState.asStateFlow()

    private val _profileScreenState = MutableStateFlow(ProfileScreenState())
    val profileScreenState = _profileScreenState.asStateFlow()

    private val _updateScreenState = MutableStateFlow(UpdateScreenState())
    val updateScreenState = _updateScreenState.asStateFlow()

    private val _userProfileImageState = MutableStateFlow(UploadUserProfileImageState())
    val userProfileImageState = _userProfileImageState.asStateFlow()

    private val _addToCartScreenState = MutableStateFlow(AddToCartScreenState())
    val addToCartScreenState = _addToCartScreenState.asStateFlow()

    private val _getProductByIdState = MutableStateFlow(GetProductByIdState())
    val getProductByIdState = _getProductByIdState.asStateFlow()

    private val _addToFavScreenState = MutableStateFlow(AddToFavScreenState())
    val addToFavScreenState = _addToFavScreenState.asStateFlow()

    private val _getAllFavScreenState = MutableStateFlow(GetAllFavState())
    val getAllFavScreenState = _getAllFavScreenState.asStateFlow()

    private val _getAllProductScreenState = MutableStateFlow(GetAllProductState())
    val getAllProductScreenState = _getAllProductScreenState.asStateFlow()

    private val _getCartScreenState = MutableStateFlow(GetCartState())
    val getCartScreenState = _getCartScreenState.asStateFlow()

    private val _getAllCategoriesScreenState = MutableStateFlow(GetAllCategoriesState())
    val getAllCategoriesScreenState = _getAllCategoriesScreenState.asStateFlow()

    private val _checkOutScreenState = MutableStateFlow(GetCheckOutState())
    val checkOutScreenState = _checkOutScreenState.asStateFlow()

    private val _getSpecificCategoryItemsScreenState = MutableStateFlow(GetSpecificCategoryItemsState())
    val getSpecificCategoryItemsScreenState = _getSpecificCategoryItemsScreenState.asStateFlow()

    private val _getAllSuggestedProductsScreenState = MutableStateFlow(GetAllSuggestedProductsState())
    val getAllSuggestedProductsScreenState = _getAllSuggestedProductsScreenState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun getSpecificCategoryItems(categoryName : String){
        viewModelScope.launch {
            getSpecificCategoryItemsState.getSpecificCategoryItems(categoryName).collect{
                when(it){
                    is ResultState.Error->{
                        _getSpecificCategoryItemsScreenState.value = _getSpecificCategoryItemsScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }
                    is ResultState.Loading->{
                        _getSpecificCategoryItemsScreenState.value = _getSpecificCategoryItemsScreenState.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultState.Success->{
                        _getSpecificCategoryItemsScreenState.value = _getSpecificCategoryItemsScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                }
            }
        }

    }





















}

















































data class ProfileScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : UserDataParents?=null

)

data class SignUpScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class LoginScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class UpdateScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class UploadUserProfileImageState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class AddToCartScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class GetProductByIdState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : ProductDataModels?=null

)
data class AddToFavScreenState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : String?=null

)

data class GetAllFavState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<ProductDataModels?> = emptyList()

)

data class GetAllProductState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<ProductDataModels?> =emptyList()

)

data class GetCartState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<ProductDataModels?> =emptyList()

)

data class GetAllCategoriesState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<ProductDataModels?> =emptyList()

)

data class GetCheckOutState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : ProductDataModels?=null

)

data class GetSpecificCategoryItemsState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<CategoryDataModels?> =emptyList()

)

data class GetAllSuggestedProductsState(
    val isLoading : Boolean = false,
    val errorMessage : String?=null,
    val userData : List<ProductDataModels?> =emptyList()

)



