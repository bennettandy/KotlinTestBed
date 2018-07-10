package com.avsoftware.kotlinapp.dagger;

import com.avsoftware.data.dagger.DataComponent;
import com.avsoftware.kotlinapp.KotlinApp;
import com.avsoftware.kotlinapp.dagger.modules.UIModule;
import com.avsoftware.kotlinapp.ui.cocktail.CocktailMainFragment;
import com.avsoftware.kotlinapp.ui.recipe.fragment.RecipeSearchFragment;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(dependencies = DataComponent.class, modules = {UIModule.class})
public interface ApplicationComponent {
    void inject(@NotNull KotlinApp kotlinApp);
    void inject(@NotNull RecipeSearchFragment recipeSearchFragment);
    void inject(@NotNull CocktailMainFragment notificationsFragment);
}
