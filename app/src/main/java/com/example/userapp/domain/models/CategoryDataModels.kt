package com.example.userapp.domain.models

data class CategoryDataModels(
    var name : String = "",
    var date : Long = System.currentTimeMillis(),
    var createdBy :String,
    var categoryImage : String

)