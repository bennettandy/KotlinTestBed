package com.avsoftware.domain.recipe;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;


public class RecipeRepository {
    // regex to obtain recipe id from recipe url
    public final BehaviorRelay<Boolean> isRefreshing; // is refreshing flag for UI
    public final BehaviorRelay<Boolean> didError; // error flag for UI
    public final BehaviorRelay<List<RecipeInfo>> recipeList;
    public final BehaviorRelay<String> searchRecipe;
    // progress info
    public final BehaviorRelay<Integer> progressTarget; // Final progress value
    public final BehaviorRelay<Integer> currentProgress; // current progress value
    // api
    private final RecipeProvider mRecipeProvider;

    public RecipeRepository(RecipeProvider provider) {
        mRecipeProvider = provider;
        recipeList = BehaviorRelay.create();
        searchRecipe = BehaviorRelay.createDefault("");
        isRefreshing = BehaviorRelay.createDefault(false);
        progressTarget = BehaviorRelay.createDefault(0);
        currentProgress = BehaviorRelay.createDefault(0);
        didError = BehaviorRelay.createDefault(false); // error flag
    }

    public Completable connectSearch() {
        return searchRecipe
                .debounce(200, TimeUnit.MILLISECONDS)
                .filter(s -> s.length() > 2)
                .distinctUntilChanged()
                .flatMapSingle(mRecipeProvider::searchRecipes)
                .doOnNext(recipeList)
                .ignoreElements()
                .subscribeOn(Schedulers.io());
    }
}
