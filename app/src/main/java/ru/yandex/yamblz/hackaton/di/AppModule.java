package ru.yandex.yamblz.hackaton.di;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.dictionary.DictionaryService;
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
    DictionaryService provideDictionaryService(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl("https://dictionary.yandex.net/api/v1/dicservice.json/")
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build()
                .create(DictionaryService.class);
    }

    @Singleton
    @Provides
    Dictionary provideDictionary(DictionaryService dictionaryService) {
        return new YaDictionary(dictionaryService);
    }

    @Provides
    @Singleton
    Handler provideMainThreadHandler() {
        return new Handler(Looper.getMainLooper());
    }

}
