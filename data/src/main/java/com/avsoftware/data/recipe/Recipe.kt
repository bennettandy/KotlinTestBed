package com.avsoftware.data.recipe

import com.google.gson.annotations.SerializedName
import java.util.regex.Pattern

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
) {
    private val RECIPE_ID_PATTERN = "^.*?/view/(.+)$"

    fun recipeId(): String {
        val pattern = Pattern.compile(RECIPE_ID_PATTERN) // extract recipe id from url
        val matcher = pattern.matcher(recipeUrl)
        if (matcher.find()) {
            return matcher.group(1)
        }
        return ""
    }
}