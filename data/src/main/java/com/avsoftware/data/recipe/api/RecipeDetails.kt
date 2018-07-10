package com.avsoftware.data.recipe.api

import com.google.gson.annotations.SerializedName

data class RecipeDetails(
        @SerializedName("f2f_url")
        val recipeUrl: String,

        @SerializedName("publisher_url")
        val publisherUrl: String?,

        val ingredients: List<String>,

        @SerializedName("source_url")
        val sourceUrl: String,

        @SerializedName("social_rank")
        val socialRank: Double,

        @SerializedName("recipe_id")
        val recipeId: String,

        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("title")
        val title: String
)

// Response class
data class RecipeDetailsResponse( val recipe: RecipeDetails?)