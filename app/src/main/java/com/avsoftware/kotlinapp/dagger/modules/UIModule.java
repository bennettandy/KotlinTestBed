package com.avsoftware.kotlinapp.dagger.modules;

import com.avsoftware.domain.recipe.RecipeRepository;
import com.avsoftware.kotlinapp.ui.recipe.RecipeSearchViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    @Provides
    @Singleton
    static RecipeSearchViewModel provideSearchActivityViewModel(RecipeRepository repository){
        return new RecipeSearchViewModel(repository);
    }
}
