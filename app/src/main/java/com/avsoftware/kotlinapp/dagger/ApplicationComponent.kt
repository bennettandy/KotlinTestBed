package com.avsoftware.kotlinapp.dagger

import com.avsoftware.data.dagger.DataComponent
import com.avsoftware.kotlinapp.KotlinApp
import com.avsoftware.kotlinapp.di.RecipeSubComponent
import com.avsoftware.kotlinapp.di.RecipeSubModule
import com.avsoftware.kotlinapp.ui.cocktail.CocktailMainFragment

import dagger.Component

@Component(dependencies = [(DataComponent::class)], modules = emptyArray())
interface ApplicationComponent {

    fun inject(app: KotlinApp)

    fun inject(notificationsFragment: CocktailMainFragment)

    fun add(module: RecipeSubModule): RecipeSubComponent
}
