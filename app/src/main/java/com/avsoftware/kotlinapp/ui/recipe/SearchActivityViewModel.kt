package com.avsoftware.kotlinapp.ui.recipe

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.domain.recipe.RecipeRepository
import com.avsoftware.kotlinapp.R

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import com.nextfaze.poweradapters.PowerAdapter
import com.nextfaze.poweradapters.binding.Binder
import com.nextfaze.poweradapters.binding.ViewHolderBinder
import com.nextfaze.poweradapters.rxjava2.ObservableAdapterBuilder

/**
 * Created by abennett on 24/03/2018.
 */


class SearchActivityViewModel(private val mRecipeRepository: RecipeRepository): ViewModel() {

    val isRefreshing = mRecipeRepository.isRefreshing

    val didError: LiveData<Boolean> = mRecipeRepository.didError
    //val recipeClicked: PublishRelay<RecipeInfo> = PublishRelay.create()
    val currentProgress: LiveData<Int> = mRecipeRepository.currentProgress
    val progressTarget: LiveData<Int> =  mRecipeRepository.progressTarget
    //val recipeClickedLiveData: LiveData<RecipeInfo> = LiveDataReactiveStreams.fromPublisher(recipeClicked.toFlowable(BackpressureStrategy.DROP))
    val recipeClicked: MutableLiveData<RecipeInfo> = MutableLiveData()
    val queryText: BehaviorRelay<String> = BehaviorRelay.createDefault("")
    val searchTrigger: Relay<String> = mRecipeRepository.searchRecipe

    /**
     * Power adapter binds Observable<List></List><Movie>> to Movie Card
     *
     *
     * Nice library
     * https://github.com/NextFaze/power-adapters
    </Movie> */
    // UI Binder for Recycler Adapter
    // will replace full list contents with each event
    val recipeAdapter: PowerAdapter
        get() {
            val recipeBinder: Binder<RecipeInfo, View> =
                    ViewHolderBinder.create(R.layout.recipe_card,
                            { v -> RecipeViewHolder(v) }, // create view holder
                            { _, t, h, _ -> h.bindViewHolder(t, recipeClicked)  }) // bind recipe to view holder
            val builder = ObservableAdapterBuilder(recipeBinder)
            builder.contents(mRecipeRepository.recipeList)
            return builder.build()
        }

    fun connectSearch() = mRecipeRepository.connectSearch()
}
