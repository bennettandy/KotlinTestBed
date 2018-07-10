package com.avsoftware.data.dagger;

import com.avsoftware.domain.recipe.RecipeProvider;

import dagger.Component;

@Component(modules = { Retrofit2Module.class, RecipeModule.class, CocktailModule.class })
public interface DataComponent {

    RecipeProvider provideRecipeProvider();
}
