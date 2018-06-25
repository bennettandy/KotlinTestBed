package com.avsoftware.domain.recipe;

import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;



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

//    public Completable connectSearch() {
//
//        /**
//         * Ignore empty query and if we are already refreshing
//         */
//        ObservableTransformer<String, String> filterQuery = upstream ->
//                upstream
//                        .filter(query -> query.trim().length() > 0) // ignore any empty queries
//                        .filter(__ -> !isRefreshing.getValue()); // ignore if we are already refreshing
//
//        /**
//         * Call api with search query
//         */
//        ObservableTransformer<String, List<RecipeInfo>> searchRecipes = upstream ->
//                upstream.map(RecipeSearchRequest.Companion::newInstance) // populate request object
//                        .flatMapSingle(request -> mRecipeProvider.getRecipes(request)
//                                .subscribeOn(Schedulers.io())); // get recipe list from search results
//
//        /**
//         * Obtain recipe ID from recipe Url
//         */
//        ObservableTransformer<Recipe, String> recipeIdFromUrl = upstream ->
//                upstream.map(Recipe::getRecipeUrl)
//                        .map(url -> {
//                            Pattern pattern = Pattern.compile(RECIPE_ID_PATTERN); // extract recipe id from url
//                            Matcher matcher = pattern.matcher(url);
//                            if (matcher.find()) {
//                                return matcher.group(1);
//                            }
//                            return "";
//                        });
//
//        /**
//         * Compose RecipeDetails from API with Recipe received from search call
//         */
//        ObservableTransformer<List<Recipe>, List<RecipeWrapped>> addRecipeDetails = upstream ->
//                upstream
//                        .doOnNext(recipes -> progressTarget.set(recipes.size())) // set progress display target value
//                        .doOnNext(__ -> currentProgress.set(0)) // reset current progress to 0
//                        .flatMapSingle(recipes ->
//                                Observable.fromIterable(recipes) // for each recipe
//                                        .flatMap(recipe -> Observable.just(recipe)
//                                                .compose(recipeIdFromUrl) // obtain recipe id from url
//                                                .flatMapSingle(mRecipeProvider::getRecipeDetails) // request details
//                                                .map(recipeDetails -> new RecipeWrapped(recipe, recipeDetails) // combine data objects
//                                                ))
//                                        .doOnNext(__ -> currentProgress.set(currentProgress.get() + 1)) // increment progress
//                                        .toList()) // collect into List<RecipeWrapper>
//                                        .doOnEach(listNotification -> {
//                                            // reset progress here so it's never left partial due to error
//                                            progressTarget.set(0);
//                                            currentProgress.set(0);
//                                        });
//
//        /**
//         * Perform search and details lookups
//         */
//        return searchRecipe // search events
//                .compose(filterQuery)
//                .doOnNext(__ -> isRefreshing.set(true)) // set refreshing UI flag
//                .doOnNext(__ -> didError.set(false)) // reset error flag
//                .compose(searchRecipes)
//                .compose(addRecipeDetails)
//                .doOnNext(recipeList)
//                .doOnEach(__ -> isRefreshing.set(false)) // unset refreshing UI flag
//                .doOnError(Timber::e) // log any error, also prevents crash
//                .doOnError(__ -> didError.set(true)) // set ui error flag
//                .onErrorResumeNext(Observable.empty()) // return nothing on error
//                .retry() // keep observing even if we encounter an error
//                .ignoreElements(); // return clean completable for Activity to subscribe to
//
//    }

}
