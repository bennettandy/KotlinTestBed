package com.avsoftware.data.recipe

import com.google.gson.annotations.SerializedName

data class Recipe(
        val publisher: String,
        @SerializedName("social_rank")
        val socialRank: Double?,
        @SerializedName("f2f_url")
        val recipeUrl: String,
        @SerializedName("publisher_url")
        val publisherUrl: String,
        val title: String,
        @SerializedName("source_url")
        val sourceUrl: String,
        val page: Int
)