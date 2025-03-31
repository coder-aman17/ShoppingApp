package com.example.userapp.presentation.navigation

import kotlinx.serialization.Serializable


sealed class SubNavigation{
    @Serializable
    object LoginSignUpScreen : SubNavigation()

    @Serializable
    object MainHomeScreen : SubNavigation()


}

sealed class Routes{
    @Serializable
    object LoginScreen

    @Serializable
    object SignUpScreen

    @Serializable
    object HomeScreen

    @Serializable
    object ProfileScreen

    @Serializable
    object WishList

    @Serializable
    object CartScreen

    @Serializable
    data class CheckOutScreen(val productId: String)

    @Serializable
    object PayScreen

    @Serializable
    object SeeAllProductScreen

    @Serializable
    data class EachProductDetailsScreen(val productId: String)

    @Serializable
    object AllCategoriesScreen

    @Serializable
    data class EachCategoryItemScreens(val categoryName: String)


}