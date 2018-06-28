package com.avsoftware.kotlinapp.ui.recipe

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.ActivityRecipeDetailsBinding
import com.avsoftware.kotlinapp.databinding.IngredientListItemBinding
import com.nextfaze.poweradapters.AdapterBuilder
import com.nextfaze.poweradapters.PowerAdapter
import com.nextfaze.poweradapters.binding.ViewHolder
import com.nextfaze.poweradapters.binding.ViewHolderBinder
import com.nextfaze.poweradapters.rxjava2.ObservableAdapterBuilder

class RecipeDetailsActivity: AppCompatActivity() {

    private lateinit var mViewBinding: ActivityRecipeDetailsBinding

    private lateinit var mRecipe: RecipeInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecipe = RecipeInfo()

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details)
        mViewBinding.setLifecycleOwner(this)

        mViewBinding.recipe = mRecipe

        mViewBinding.ingredients = PowerAdapter.EMPTY
    }

}
