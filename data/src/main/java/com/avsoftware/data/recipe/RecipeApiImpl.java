package com.avsoftware.data.recipe;

import com.avsoftware.data.BuildConfig;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RecipeApiImpl implements RecipeApi {

    private RecipeRetrofit mApi;

    public RecipeApiImpl(Retrofit retrofit) {
        mApi = retrofit.create(RecipeRetrofit.class);
    }

    @Override
    public Single<List<Recipe>> getRecipes(RecipeSearchRequest request) {
        return mApi
                .getRecipes(BuildConfig.FOOD_2_FORK_API_KEY, request.getQuery(), request.getSort(), request.getPage())
                .map(RecipeResponse::getRecipes)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<RecipeDetails> getRecipeDetails(String recipeId) {
        return mApi
                .getRecipe(BuildConfig.FOOD_2_FORK_API_KEY, recipeId)
                .map(RecipeDetailsResponse::getRecipe)
                .subscribeOn(Schedulers.io());
    }


    private interface RecipeRetrofit {

        @GET("api/search")
        Single<RecipeResponse> getRecipes(@Query("key") String apiKey, @Query("q") String query, @Query("sort") String sort, @Query("page") Integer page);

        @GET("api/get")
        Single<RecipeDetailsResponse> getRecipe(@Query("key") String apiKey, @Query("rId") String recipeId);
    }
}
