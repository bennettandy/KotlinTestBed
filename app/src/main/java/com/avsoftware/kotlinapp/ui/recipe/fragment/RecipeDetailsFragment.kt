package com.avsoftware.kotlinapp.ui.recipe.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.kotlinapp.R
import com.avsoftware.kotlinapp.databinding.RecipeDetailsFragmentBinding
import com.avsoftware.kotlinapp.ui.recipe.RecipeDetailsViewModel

class RecipeDetailsFragment: Fragment() {

    private lateinit var mViewBinding: RecipeDetailsFragmentBinding

    private val mViewModel = RecipeDetailsViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mViewBinding = DataBindingUtil.inflate(inflater, R.layout.recipe_details_fragment, container, false)
        mViewBinding.setLifecycleOwner(this)
        mViewBinding.viewModel = mViewModel
        mViewBinding.recycler.layoutManager = LinearLayoutManager(context)

        val recipe: RecipeInfo? = this.arguments?.getParcelable("recipe")
        if (recipe != null){
            mViewModel.setRecipeDetails(recipe)
        }


        return mViewBinding.root
    }

}