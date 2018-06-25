package com.avsoftware.kotlinapp.dagger.modules;

import com.avsoftware.domain.recipe.RecipeRepository;
import com.avsoftware.kotlinapp.ui.recipe.SearchActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    @Provides
    static SearchActivityViewModel provideSearchActivityViewModel(RecipeRepository repository){
        return new SearchActivityViewModel(repository);
    }
}
