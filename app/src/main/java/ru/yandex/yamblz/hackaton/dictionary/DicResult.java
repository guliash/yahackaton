package ru.yandex.yamblz.hackaton.dictionary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DicResult {

    @SerializedName("def")
    private List<Article> mArticles;

    /**
     * Статьи
     */
    public List<Article> getArticles() {
        return mArticles;
    }
}
