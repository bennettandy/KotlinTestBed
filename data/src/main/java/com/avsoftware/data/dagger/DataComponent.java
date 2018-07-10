package com.avsoftware.data.dagger;

import com.avsoftware.domain.recipe.RecipeRepository;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = { Retrofit2Module.class, RecipeModule.class, CocktailModule.class })
public interface DataComponent {

    RecipeRepository provideRecipeRepository();
}
