package ru.yandex.yamblz.hackaton.di;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.yandex.yamblz.hackaton.MyApplication;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.dictionary.DictionaryService;
import ru.yandex.yamblz.hackaton.dictionary.YaDictionary;
import ru.yandex.yamblz.hackaton.storage.DbWordStorage;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;

@Module
public class AppModule {

    private MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return (Context)application;
    }

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

    @Provides
    @Singleton
    Executor provideWorkerExecutor() {
        return new ThreadPoolExecutor(4, 10, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1));
    }

    @Provides
    @Singleton
    WordsStorage provideWordsStorage(Context context) {
        return new DbWordStorage(context);
    }


}
