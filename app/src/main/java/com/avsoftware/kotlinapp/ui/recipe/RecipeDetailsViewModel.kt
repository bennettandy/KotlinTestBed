package com.avsoftware.kotlinapp.ui.recipe

import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.view.View
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.IngredientListItemBinding
import com.jakewharton.rxrelay2.BehaviorRelay
import com.nextfaze.poweradapters.PowerAdapter
import com.nextfaze.poweradapters.binding.Binder
import com.nextfaze.poweradapters.binding.ViewHolder
import com.nextfaze.poweradapters.binding.ViewHolderBinder
import com.nextfaze.poweradapters.rxjava2.ObservableAdapterBuilder

class RecipeDetailsViewModel {

    val title: ObservableField<String> = ObservableField()
    val recipeImageUrl: ObservableField<String> = ObservableField()
    private val ingredients: BehaviorRelay<List<String>> = BehaviorRelay.create()

    fun setRecipeDetails(details: RecipeInfo) {
        title.set(details.title)
        recipeImageUrl.set(details.imageUrl)
        ingredients.accept(details.ingredients)
    }

    val dashboardAdapter: PowerAdapter
        get() {
            val dashBinder: Binder<String, View> =
                    ViewHolderBinder.create(R.layout.ingredient_list_item,
                            { v -> ChildDashElementViewHolder(v) },
                            { _, t, h, _ -> h.bindViewHolder(t) })
            val builder = ObservableAdapterBuilder(dashBinder)
            builder.contents(ingredients)
            return builder.build()
        }
}

internal class ChildDashElementViewHolder(view: View) : ViewHolder(view) {
    // Elvis operator, assign existing binding or Bind a fresh one
    private val ingredientCardBinding: IngredientListItemBinding = DataBindingUtil.getBinding(view) ?: IngredientListItemBinding.bind(view)

    fun bindViewHolder(ingredient: String) {
        ingredientCardBinding.ingredient = ingredient
    }
}
