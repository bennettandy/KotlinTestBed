package com.avsoftware.kotlinapp.dagger

import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.dagger.modules.UIModule
import com.avsoftware.kotlinapp.ui.cocktail.CocktailMainFragment
import com.avsoftware.kotlinapp.ui.recipe.RecipeRepository
import com.avsoftware.kotlinapp.ui.recipe.fragment.RecipeSearchFragment

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(dependencies = [(DataComponent::class)], modules = arrayOf(UIModule::class))
interface ApplicationComponent {
    fun inject(kotlinApp: KotlinApp)
    fun inject(recipeSearchFragment: RecipeSearchFragment)
    fun inject(notificationsFragment: CocktailMainFragment)
    fun provideRecipeRepository(): RecipeRepository
}
