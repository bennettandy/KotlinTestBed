package com.avsoftware.kotlinapp.ui.recipe

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.view.View
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.databinding.RecipeCardBinding
import com.nextfaze.poweradapters.binding.ViewHolder

internal class RecipeViewHolder(view: View) : ViewHolder(view) {
    // Elvis operator, assign existing binding or Bind a fresh one
    private val recipeCardBinding: RecipeCardBinding = DataBindingUtil.getBinding(view) ?: RecipeCardBinding.bind(view)

    fun bindViewHolder(recipe: RecipeInfo, recipeUrlClicked: MutableLiveData<RecipeInfo>) {
        //recipeCardBinding.recipeDetails = wrapper.details // bind recipe for display
        recipeCardBinding.recipeInfo = recipe
        recipeCardBinding.setRecipeClicked { _ -> recipeUrlClicked.postValue(recipe) } // on click handler passes recipe parameter to Relay
    }
}