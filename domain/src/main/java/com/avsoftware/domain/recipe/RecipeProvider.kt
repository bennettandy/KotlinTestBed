package com.avsoftware.domain.recipe

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single

interface RecipeProvider {

    // TODO: Not happy with this, but allows us to show loading progress
    val currentProgress: MutableLiveData<Int>
    val progressTarget: MutableLiveData<Int>

    fun searchRecipes(searchString: String?): Single<List<RecipeInfo>>
}