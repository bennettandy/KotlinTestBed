package com.avsoftware.data.dagger;

import com.avsoftware.data.recipe.RecipeApi;
import com.avsoftware.data.recipe.RecipeApiImpl;
import com.avsoftware.data.recipe.RecipeProviderImpl;
import com.avsoftware.domain.recipe.RecipeProvider;
import com.avsoftware.domain.recipe.RecipeRepository;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
class RecipeModule {

    private static final String FOOD_2_FORK_BASE_URL = "http://food2fork.com";

    @Provides
    @Named("recipe")
    static Retrofit provideRecipeRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(FOOD_2_FORK_BASE_URL + "/").build();
    }

    @Provides
    static RecipeApi provideRecipeApi(@Named("recipe") Retrofit retrofit){
        return new RecipeApiImpl(retrofit);
    }

    @Provides
    static RecipeProvider provideRecipeProvider( RecipeApi api){
        return new RecipeProviderImpl(api);
    }

    @Provides
    static RecipeRepository provideRecipeRepository(RecipeProvider provider){
        return new RecipeRepository(provider);
    }
}
