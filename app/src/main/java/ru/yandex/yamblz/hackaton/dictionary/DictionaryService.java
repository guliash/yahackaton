package ru.yandex.yamblz.hackaton.dictionary;

import retrofit2.http.GET;

public interface DictionaryService {
    @GET("getLangs")
    Call<List<String>> 
}
