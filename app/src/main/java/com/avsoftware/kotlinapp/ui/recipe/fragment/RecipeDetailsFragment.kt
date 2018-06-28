package com.avsoftware.kotlinapp.ui.recipe.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.RecipeDetailsFragmentBinding
import com.nextfaze.poweradapters.PowerAdapter

class RecipeDetailsFragment: Fragment() {

    private lateinit var mViewBinding: RecipeDetailsFragmentBinding

    private lateinit var mRecipe: RecipeInfo

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mRecipe = RecipeInfo()

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_details_fragment, container, false)
        mViewBinding.setLifecycleOwner(this)

        mViewBinding.recipe = mRecipe

        mViewBinding.ingredients = PowerAdapter.EMPTY

        return mViewBinding.root
    }

}