package com.avsoftware.data.recipe

import com.google.gson.annotations.SerializedName

data class RecipeSearchRequest(
        @SerializedName("q") val query: String?,
        @SerializedName("sort") val sort: String?,
        @SerializedName("page") val page: Int?
) {
    companion object {
        fun newInstance(query: String): RecipeSearchRequest {
            return RecipeSearchRequest(query, "r", 1)
        }
        fun newInstance(query: String, page: Int?): RecipeSearchRequest {
            return RecipeSearchRequest(query, "r", page)
        }
    }
}

// Response Class
data class RecipeResponse( val count: Int, val recipes: List<Recipe>)