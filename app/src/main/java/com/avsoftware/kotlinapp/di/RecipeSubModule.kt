package com.avsoftware.kotlinapp.di

import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import com.avsoftware.domain.recipe.RecipeProvider
import com.avsoftware.kotlinapp.ui.recipe.RecipeRepository
import com.avsoftware.kotlinapp.ui.recipe.RecipeSearchViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RecipeSubModule( private val activity: FragmentActivity?){

    @Provides
    @Singleton
    fun provideSearchActivityViewModel(repository: RecipeRepository): RecipeSearchViewModel {
        return RecipeSearchViewModel(repository)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(provider: RecipeProvider): RecipeRepository {
        return RecipeRepository(provider)
    }
}