package com.avsoftware.kotlinapp.ui.recipe

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.RecipeDetailsFragmentBinding
import com.nextfaze.poweradapters.PowerAdapter

class RecipeDetailsActivity: AppCompatActivity() {

    private lateinit var mViewBinding: RecipeDetailsFragmentBinding

    private lateinit var mRecipe: RecipeInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRecipe = RecipeInfo()

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.recipe_details_fragment)
        mViewBinding.setLifecycleOwner(this)

        mViewBinding.recipe = mRecipe

        mViewBinding.ingredients = PowerAdapter.EMPTY
    }

}
