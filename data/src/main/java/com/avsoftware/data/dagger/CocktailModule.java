package com.avsoftware.data.dagger;

import com.avsoftware.data.cocktail.CocktailApi;
import com.avsoftware.data.cocktail.CocktailApiImpl;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
class CocktailModule {

    private static final String DEV_API_KEY = "1";

    private static final String COCKTAIL_DB_BASE_URL = "https://www.thecocktaildb.com/api/json/v1/" + DEV_API_KEY + "/";

    @Provides
    @Named("cocktail")
    static Retrofit provideCocktailRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(COCKTAIL_DB_BASE_URL).build();
    }
    
    @Provides
    static CocktailApi provideCocktailApi(@Named("cocktail") Retrofit retrofit){
        return new CocktailApiImpl(retrofit);
    }
    
}
