package com.avsoftware.kotlinapp.ui.recipe

import android.databinding.DataBindingUtil
import android.view.View
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.databinding.RecipeCardBinding
import com.jakewharton.rxrelay2.Relay
import com.nextfaze.poweradapters.binding.ViewHolder

internal class RecipeViewHolder(view: View) : ViewHolder(view) {
    // Elvis operator, assign existing binding or Bind a fresh one
    private val recipeCardBinding: RecipeCardBinding = DataBindingUtil.getBinding(view) ?: RecipeCardBinding.bind(view)

    fun bindViewHolder(recipe: RecipeInfo, recipeUrlClicked: Relay<RecipeInfo>) {
        //recipeCardBinding.recipeDetails = wrapper.details // bind recipe for display
        recipeCardBinding.recipeInfo = recipe
        recipeCardBinding.setRecipeClicked { _ -> recipeUrlClicked.accept(recipe) } // on click handler passes recipe parameter to Relay
    }
}