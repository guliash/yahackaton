package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YaDictionary implements Dictionary {

    private static final String API_KEY = "dict.1.1.20160723T044019Z.0b30322dc973db67.75c83fd81d1b234c6db3b5ae2161d6827535be1e";

    private DictionaryService mService;

    @Inject
    public YaDictionary(DictionaryService service) {
        this.mService = service;
    }

    @Override
    @Nullable
    public List<String> getLangs() {
        try {
            return mService.getLangs(API_KEY).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public DicResult lookup(String lang, String text, @Nullable String ui, int flags) {
        try {
            return mService.lookup(API_KEY, lang, text, ui, flags).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
