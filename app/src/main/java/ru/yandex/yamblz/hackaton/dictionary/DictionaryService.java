package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DictionaryService {
    @GET("getLangs")
    Call<List<String>> getLangs(@Query("key") String key);

    @GET("lookup")
    Call<DicResult> lookup(@Query("key") String key, @Query("lang")String lang, @Query("text")String text,
                           @Query("ui")@Nullable String ui, @Query("flags")int flags);
}
