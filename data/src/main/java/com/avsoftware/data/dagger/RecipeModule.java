package com.avsoftware.data.dagger;

import com.avsoftware.data.recipe.RecipeApi;
import com.avsoftware.data.recipe.RecipeApiImpl;
import com.avsoftware.data.recipe.RecipeProviderImpl;
import com.avsoftware.domain.recipe.RecipeProvider;
import com.avsoftware.domain.recipe.RecipeRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
class RecipeModule {

    @Provides
    static RecipeApi provideRecipeApi(Retrofit retrofit){
        return retrofit.create(RecipeApiImpl.class);
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
