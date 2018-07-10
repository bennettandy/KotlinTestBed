package com.avsoftware.data.recipe.api

import com.google.gson.annotations.SerializedName

data class RecipeSearchRequest(
        @SerializedName("q") val query: String = "",
        @SerializedName("sort") val sort: String = "r",
        @SerializedName("page") val page: Int = 1
)

// Response Class
data class RecipeResponse( val count: Int, val recipes: List<Recipe>)