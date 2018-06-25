package com.avsoftware.domain.recipe

import io.reactivex.Single

interface RecipeProvider {
    fun searchRecipes(searchString: String?): Single<List<RecipeInfo>>
}