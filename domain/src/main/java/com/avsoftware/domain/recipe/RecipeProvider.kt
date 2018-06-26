package com.avsoftware.domain.recipe

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Single

abstract class RecipeProvider {

    val currentProgress: MutableLiveData<Int> = MutableLiveData()
    val progressTarget: MutableLiveData<Int> = MutableLiveData()

    abstract fun searchRecipes(searchString: String?): Single<List<RecipeInfo>>
}