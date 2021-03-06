package com.avsoftware.data.dagger;

import com.avsoftware.data.BuildConfig;
import com.avsoftware.data.gson.DateDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class Retrofit2Module {

    private final static int TIMEOUT_CONNECT = 10; // 10 secs
    private final static int TIMEOUT_READ = 15; // 15 secs
    private final static int TIMEOUT_WRITE = 15; // 15 secs

    @Provides
    static Retrofit.Builder provideRetrofitBuilder(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()//
                .addConverterFactory(GsonConverterFactory.create(gson))//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//
                .client(okHttpClient);
    }

    @Provides
    static Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Provides
    static OkHttpClient.Builder provideOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // Set Timeouts
        builder.connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS) //
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS) //
                .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS);
        return builder;
    }

    @Provides
    static OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder, @Named("debugInterceptor") Interceptor debugInterceptor) {
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(debugInterceptor);
        }
        return builder.build();
    }

    @Provides
    @Named("debugInterceptor")
    static Interceptor provideDebuggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // configure the amount of HTTP log info we get in logcat
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

}
