package com.avsoftware.kotlinapp.ui.recipe;

import android.arch.lifecycle.MutableLiveData;

import com.avsoftware.domain.recipe.RecipeInfo;
import com.avsoftware.domain.recipe.RecipeProvider;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class RecipeRepository {
    // regex to obtain recipe id from recipe url
    public final MutableLiveData<Boolean> isRefreshing; // is refreshing flag for UI
    public final MutableLiveData<Boolean> didError; // error flag for UI

    public final BehaviorRelay<List<RecipeInfo>> recipeList;
    public final PublishRelay<String> searchRecipe;

    // progress info
    public final MutableLiveData<Integer> progressTarget; // Final progress value
    public final MutableLiveData<Integer> currentProgress; // current progress value
    // api
    private final RecipeProvider mRecipeProvider;

    public RecipeRepository(RecipeProvider provider) {
        mRecipeProvider = provider;
        recipeList = BehaviorRelay.create();
        searchRecipe = PublishRelay.create();
        isRefreshing = new MutableLiveData<>();
        progressTarget = provider.getProgressTarget();
        currentProgress = provider.getCurrentProgress();
        didError = new MutableLiveData<>(); // error flag
    }

    public Completable connectSearch() {
        return searchRecipe
                .doOnNext(s -> Timber.d("Search Recipe %s", s))
                .debounce(200, TimeUnit.MILLISECONDS)
                .filter(s -> s.length() > 2)
                .distinctUntilChanged()
                .doOnNext(__ -> isRefreshing.postValue(true))
                .doOnNext(__ -> didError.postValue(false))
                .flatMapSingle(mRecipeProvider::searchRecipes)
                .doOnNext(recipeList)
                .doOnEach(__ -> isRefreshing.postValue(false))
                .doOnError(__ -> didError.postValue(true))
                .ignoreElements()
                .subscribeOn(Schedulers.io());
    }
}
