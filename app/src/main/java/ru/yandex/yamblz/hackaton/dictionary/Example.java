package ru.yandex.yamblz.hackaton.dictionary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("text")
    private String mText;

    @SerializedName("tr")
    private List<Translation> mTranslations;

    /**
     * Текст примера
     */
    public String getText() {
        return mText;
    }

    /**
     * Список переводов примера
     */
    public List<Translation> getTranslations() {
        return mTranslations;
    }
}
