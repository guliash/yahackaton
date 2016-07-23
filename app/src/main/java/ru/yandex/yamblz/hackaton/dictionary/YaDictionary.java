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

    private static final String SERVER = "dictionary.yandex.net";
    //api/v1/dicservice.json

    private OkHttpClient mNetworkClient;
    private Gson mGson;

    @Inject
    public YaDictionary(OkHttpClient networkClient, Gson gson) {
        this.mNetworkClient = networkClient;
        this.mGson = gson;
    }

    @Override
    @Nullable
    public List<String> getLangs() {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host(SERVER)
                .addPathSegment("api/v1/dicservice.json/getLangs")
                .addEncodedQueryParameter("key", API_KEY).build();
        Log.e("TAG", httpUrl.toString());
        Request request = new Request.Builder().url(httpUrl).build();
        try {
            Response response = mNetworkClient.newCall(request).execute();
            return mGson.fromJson(response.body().string(), new TypeToken<List<String>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DicResult lookup(String lang, String text, @Nullable String ui, int flags) {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .addPathSegment(SERVER)
                .addPathSegment("lookup")
                .addEncodedQueryParameter("lang", lang)
                .addEncodedQueryParameter("text", text)
                .addEncodedQueryParameter("ui", ui)
                .addEncodedQueryParameter("flags", Integer.toString(flags)).build();
        Request request = new Request.Builder().url(httpUrl).build();
        try {
            Response response = mNetworkClient.newCall(request).execute();
            return mGson.fromJson(response.body().string(), DicResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
