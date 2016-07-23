package ru.yandex.yamblz.hackaton.di;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.dictionary.YaDictionary;

@Module
public class AppModule {

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    Dictionary provideDictionary(OkHttpClient okHttpClient, Gson gson) {
        return new YaDictionary(okHttpClient, gson);
    }

}
