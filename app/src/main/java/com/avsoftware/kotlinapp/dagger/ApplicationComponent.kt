package com.avsoftware.kotlinapp.dagger

import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.dagger.modules.UIModule
import com.avsoftware.kotlinapp.di.RecipeSubComponent
import com.avsoftware.kotlinapp.di.RecipeSubModule
import com.avsoftware.kotlinapp.ui.cocktail.CocktailMainFragment
import com.avsoftware.kotlinapp.ui.recipe.RecipeRepository
import com.avsoftware.kotlinapp.ui.recipe.fragment.RecipeSearchFragment

import javax.inject.Singleton

import dagger.Component

@Component(dependencies = [(DataComponent::class)], modules = arrayOf(UIModule::class))
interface ApplicationComponent {

    fun inject(app: KotlinApp)

    fun inject(notificationsFragment: CocktailMainFragment)

    fun add(module: RecipeSubModule): RecipeSubComponent
}
