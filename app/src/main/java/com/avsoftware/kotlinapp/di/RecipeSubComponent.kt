package com.avsoftware.kotlinapp.di

import com.avsoftware.kotlinapp.ui.recipe.fragment.RecipeDetailsFragment
import com.avsoftware.kotlinapp.ui.recipe.fragment.RecipeSearchFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(RecipeSubModule::class))
interface RecipeSubComponent {

    fun inject(fragment: RecipeSearchFragment)
    fun inject(fragment: RecipeDetailsFragment)
}