package com.avsoftware.data.recipe;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by abennett on 12/03/2018.
 */

public interface RecipeApi {

    Single<List<Recipe>> getRecipes(RecipeSearchRequest request);

    Single<RecipeDetails> getRecipeDetails(String recipeId);
}