package com.avsoftware.data.recipe

import com.avsoftware.domain.recipe.RecipeInfo
import com.avsoftware.domain.recipe.RecipeProvider
import io.reactivex.Observable
import io.reactivex.Single

class RecipeProviderImpl(val recipeApi: RecipeApi) : RecipeProvider() {

    override fun searchRecipes(searchString: String?) = Single.just(searchString)
            .doOnSubscribe() { _ ->
                progressTarget.postValue(0)
                currentProgress.postValue(0)
            }
            .map { t: String -> RecipeSearchRequest(t) }
            .flatMap { request: RecipeSearchRequest ->
                recipeApi.getRecipes(request)
                        .doOnSuccess { t: MutableList<Recipe>? ->
                            progressTarget.postValue(t?.size ?: 0)
                        }
                        .flatMap { recipes: MutableList<Recipe> ->
                            Observable.fromIterable(recipes)
                                    .flatMapSingle { recipe: Recipe ->
                                        Single.just(recipe)
                                                .flatMap { r: Recipe -> recipeApi.getRecipeDetails(r.recipeId()) }
                                                .map { details: RecipeDetails -> RecipeInfo(recipe.title,
                                                        details.imageUrl, details.ingredients) }
                                                .doOnSuccess { _ ->
                                                    with(currentProgress) {
                                                        // todo: progress never seems to reach max, due to post being asynchronous?
                                                        postValue(value?.plus(1) ?: 0)
                                                    }
                                                }
                                    }
                                    .toList()
                                    .doOnEvent({ t1, t2 ->
                                        progressTarget.postValue(0)
                                        currentProgress.postValue(0)
                                    })
                        }
            }
}