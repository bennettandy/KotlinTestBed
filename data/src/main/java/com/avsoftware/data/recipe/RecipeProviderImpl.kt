package com.avsoftware.data.recipe

import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.domain.recipe.RecipeProvider
import io.reactivex.Observable
import io.reactivex.Single

class RecipeProviderImpl(val recipeApi: RecipeApi) : RecipeProvider {

    override fun searchRecipes(searchString: String?): Single<List<RecipeInfo>> {

        return Single.just(searchString)
                .map { t: String -> RecipeSearchRequest(t) }
                .flatMap { request: RecipeSearchRequest ->
                    recipeApi.getRecipes(request)
                            .flatMap { recipes: MutableList<Recipe> ->
                                Observable.fromIterable(recipes)
                                        .flatMapSingle { recipe: Recipe ->
                                            Single.just(recipe)
                                                    .flatMap { r: Recipe -> recipeApi.getRecipeDetails(r.recipeId()) }
                                                    .map { details: RecipeDetails -> RecipeInfo(recipe.title, details.imageUrl) }
                                        }
                                        .toList()
                            }
                }
    }
}