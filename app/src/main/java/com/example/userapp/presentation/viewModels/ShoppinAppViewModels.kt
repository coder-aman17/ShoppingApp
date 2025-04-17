package com.example.userapp.presentation.viewModels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userapp.common.HomeScreenState
import com.example.userapp.common.ResultState
import com.example.userapp.domain.models.CartDataModels
import com.example.userapp.domain.models.CategoryDataModels
import com.example.userapp.domain.models.ProductDataModels
import com.example.userapp.domain.models.UserData
import com.example.userapp.domain.models.UserDataParents
import com.example.userapp.domain.useCases.AddToCartUseCase
import com.example.userapp.domain.useCases.AddToFavUseCase
import com.example.userapp.domain.useCases.CreateUserUseCase
import com.example.userapp.domain.useCases.GetAllCategoryUseCase
import com.example.userapp.domain.useCases.GetAllFavUseCase
import com.example.userapp.domain.useCases.GetAllProductUseCase
import com.example.userapp.domain.useCases.GetAllSuggestedUseCase
import com.example.userapp.domain.useCases.GetBannerUseCase
import com.example.userapp.domain.useCases.GetCartUseCase
import com.example.userapp.domain.useCases.GetCategoryLimitUseCase
import com.example.userapp.domain.useCases.GetCheckOutUseCase
import com.example.userapp.domain.useCases.GetProductByIdUseCase
import com.example.userapp.domain.useCases.GetProductInLimitUseCase
import com.example.userapp.domain.useCases.GetSpecificCategoryItemsUseCase
import com.example.userapp.domain.useCases.GetUserUseCase
import com.example.userapp.domain.useCases.LoginUserUseCase
import com.example.userapp.domain.useCases.UpdateUserDataUseCase
import com.example.userapp.domain.useCases.UserProfileImageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

import kotlinx.coroutines.launch
import java.net.URI
import javax.inject.Inject


class UserAppViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val addToFavUseCase: AddToFavUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAllCategoriesState: GetAllCategoryUseCase,
    private val getAllFavState: GetAllFavUseCase,
    private val getAllProductState: GetAllProductUseCase,
    private val getAllSuggestedProductsState: GetAllSuggestedUseCase,
    private val getBannerUseCase: GetBannerUseCase,
    private val getCartState: GetCartUseCase,
    private val getCategoriesInLimitedUseCase: GetCategoryLimitUseCase,
    private val getCheckOutState: GetCheckOutUseCase,
    private val getSpecificCategoryItemsState: GetSpecificCategoryItemsUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val userProfileImageUseCase: UserProfileImageUseCase,
    private val getProductByIdStateUseCase: GetProductByIdUseCase,
    private val getProductInLimitUseCase: GetProductInLimitUseCase


) : ViewModel() {

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

    private val _getSpecificCategoryItemsScreenState =
        MutableStateFlow(GetSpecificCategoryItemsState())
    val getSpecificCategoryItemsScreenState = _getSpecificCategoryItemsScreenState.asStateFlow()

    private val _getAllSuggestedProductsScreenState =
        MutableStateFlow(GetAllSuggestedProductsState())
    val getAllSuggestedProductsScreenState = _getAllSuggestedProductsScreenState.asStateFlow()

    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()


    fun getSpecificCategoryItems(categoryName: String) {
        viewModelScope.launch {
            getSpecificCategoryItemsState.getSpecificCategoryItems(categoryName).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getSpecificCategoryItemsScreenState.value =
                            _getSpecificCategoryItemsScreenState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                    }

                    is ResultState.Loading -> {
                        _getSpecificCategoryItemsScreenState.value =
                            _getSpecificCategoryItemsScreenState.value.copy(
                                isLoading = true
                            )
                    }

                    is ResultState.Success -> {
                        _getSpecificCategoryItemsScreenState.value =
                            _getSpecificCategoryItemsScreenState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }
                }
            }

        }
    }

    fun getCheckOut(productId: String) {
        viewModelScope.launch {
            getCheckOutState.getCheckOutUseCase(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _checkOutScreenState.value = _checkOutScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _checkOutScreenState.value = _checkOutScreenState.value.copy(
                            isLoading = true

                        )
                    }

                    is ResultState.Success -> {
                        _checkOutScreenState.value = _checkOutScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }
        }
    }

    fun getAllCategories() {
        viewModelScope.launch {
            getAllCategoriesState.getAllCategoryUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllCategoriesScreenState.value =
                            _getAllCategoriesScreenState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )
                    }

                    is ResultState.Loading -> {
                        _getAllCategoriesScreenState.value =
                            _getAllCategoriesScreenState.value.copy(
                                isLoading = true

                            )
                    }

                    is ResultState.Success -> {
                        _getAllCategoriesScreenState.value =
                            _getAllCategoriesScreenState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )
                    }
                }
            }
        }
    }


    fun getCart() {
        viewModelScope.launch {
            getCartState.getCartUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getCartScreenState.value = _getCartScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )

                    }

                    is ResultState.Loading -> {
                        _getCartScreenState.value = _getCartScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getCartScreenState.value = _getCartScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }


                }
            }

        }

    }

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductState.getAllProducts().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllProductScreenState.value = _getAllProductScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message

                        )
                    }

                    is ResultState.Loading -> {
                        _getAllProductScreenState.value = _getAllProductScreenState.value.copy(
                            isLoading = true
                        )

                    }

                    is ResultState.Success -> {
                        _getAllProductScreenState.value = _getAllProductScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }
                }
            }
        }

    }

    fun getAllFav() {
        viewModelScope.launch {
            getAllFavState.getAllFavUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllFavScreenState.value = _getAllFavScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )

                    }

                    is ResultState.Loading -> {
                        _getAllFavScreenState.value = _getAllFavScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getAllFavScreenState.value = _getAllFavScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }
                }

            }

        }
    }

    fun addToFav(productDataModels: ProductDataModels) {
        viewModelScope.launch {
            addToFavUseCase.addToFav(productDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addToFavScreenState.value = _addToFavScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _addToFavScreenState.value = _addToFavScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _addToFavScreenState.value = _addToFavScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }

            }

        }
    }

    fun getProductById(productId: String) {
        viewModelScope.launch {
            getProductByIdStateUseCase.getProductByIdUseCase(productId).collect {
                when (it) {
                    is ResultState.Error -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = false,
                            errorMessage = it.message

                        )
                    }

                    is ResultState.Loading -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _getProductByIdState.value = _getProductByIdState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }

                }
            }
        }
    }

    fun addToCart(cartDataModels: CartDataModels) {
        viewModelScope.launch {
            addToCartUseCase.addToCart(cartDataModels).collect {
                when (it) {
                    is ResultState.Error -> {
                        _addToCartScreenState.value = _addToCartScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _addToCartScreenState.value = _addToCartScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _addToCartScreenState.value = _addToCartScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }
            }

        }
    }

    init {
        loadHomeScreenData()
    }

    fun loadHomeScreenData() {
        viewModelScope.launch {
            combine(
                getCategoriesInLimitedUseCase.getCategoryLimitUseCase(),
                getProductInLimitUseCase.getProductInLimitUseCase(),
                getBannerUseCase.getBannerUseCase()
            ) { categoriesResult, productsResult, bannersResult ->
                when {
                    categoriesResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = categoriesResult.message)

                    }

                    productsResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = productsResult.message)
                    }

                    bannersResult is ResultState.Error -> {
                        HomeScreenState(isLoading = false, errorMessage = bannersResult.message)

                    }

                    categoriesResult is ResultState.Success && productsResult is ResultState.Success && bannersResult is ResultState.Success -> {
                        HomeScreenState(
                            isLoading = false,
                            categories = categoriesResult.data,
                            products = productsResult.data,
                            banners = bannersResult.data)
                    }

                    else -> {
                        HomeScreenState(isLoading = true)

                    }
                }


            }.collect {
                _homeScreenState.value = it

            }
        }
    }

    fun upLoadUserProfileImage(uri: Uri) {
        viewModelScope.launch {
            userProfileImageUseCase.userProfileImage(uri).collect {
                when (it) {
                    is ResultState.Error -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = true
                        )

                    }

                    is ResultState.Success -> {
                        _userProfileImageState.value = _userProfileImageState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )

                    }

                }
            }
        }

    }

    fun upDateUserData(userDataParents: UserDataParents) {
        viewModelScope.launch {
            updateUserDataUseCase.updateUserUse(userDataParents = userDataParents).collect {
                when (it) {
                    is ResultState.Error -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = true

                        )
                    }

                    is ResultState.Success -> {
                        _updateScreenState.value = _updateScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }


                }
            }
        }
    }

    fun createUser(userData: UserData) {
        viewModelScope.launch {
            createUserUseCase.createUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )

                    }

                    is ResultState.Loading -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _signUpScreenState.value = _signUpScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }

        }
    }

    fun loginUser(userData: UserData) {
        viewModelScope.launch {
            loginUserUseCase.loginUser(userData).collect {
                when (it) {
                    is ResultState.Error -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _loginScreenState.value = _loginScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )


                    }
                }

            }
        }
    }

    fun getUserById(uid: String) {
        viewModelScope.launch {
            getUserUseCase.getUserById(uid).collect {
                when (it) {
                    is ResultState.Error -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            errorMessage = it.message
                        )
                    }

                    is ResultState.Loading -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = true
                        )
                    }

                    is ResultState.Success -> {
                        _profileScreenState.value = _profileScreenState.value.copy(
                            isLoading = false,
                            userData = it.data
                        )
                    }
                }

            }
        }
    }

    fun getAllSuggestedProducts() {
        viewModelScope.launch {

            getAllSuggestedProductsState.getAllSuggestedUseCase().collect {
                when (it) {
                    is ResultState.Error -> {
                        _getAllSuggestedProductsScreenState.value =
                            _getAllSuggestedProductsScreenState.value.copy(
                                isLoading = false,
                                errorMessage = it.message
                            )

                    }

                    is ResultState.Loading -> {
                        _getAllSuggestedProductsScreenState.value =
                            _getAllSuggestedProductsScreenState.value.copy(
                                isLoading = true
                            )


                    }

                    is ResultState.Success -> {
                        _getAllSuggestedProductsScreenState.value =
                            _getAllSuggestedProductsScreenState.value.copy(
                                isLoading = false,
                                userData = it.data
                            )

                    }
                }
            }
        }
    }
}

data class ProfileScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: UserDataParents? = null

)

data class SignUpScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class LoginScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class UpdateScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class UploadUserProfileImageState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class AddToCartScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class GetProductByIdState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null

)

data class AddToFavScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: String? = null

)

data class GetAllFavState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()

)

data class GetAllProductState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()

)

data class GetCartState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CartDataModels>? = emptyList()

)

data class GetAllCategoriesState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<CategoryDataModels>? = emptyList()

)

data class GetCheckOutState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: ProductDataModels? = null

)

data class GetSpecificCategoryItemsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()

)

data class GetAllSuggestedProductsState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val userData: List<ProductDataModels>? = emptyList()

)





