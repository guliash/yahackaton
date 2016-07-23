package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {

    @SerializedName("text")
    private String mText;

    @SerializedName("pos")
    private String mPos;

    @SerializedName("gen")
    private String mGen;

    @SerializedName("num")
    private String mNum;

    @SerializedName("ts")
    private String mTs;

    @SerializedName("tr")
    private List<Translation> mTranslations;

    /**
     * Исходное слово
     */
    public String getText() {
        return mText;
    }

    /**
     * Часть речи
     */
    @Nullable
    public String getPos() {
        return mPos;
    }

    /**
     * Транскрипция
     */
    public String getTs() {
        return mTs;
    }

    /**
     * Список переводов
     */
    public List<Translation> getTranslations() {
        return mTranslations;
    }

    /**
     * Пол
     */
    @Nullable
    public String getGen() {
        return mGen;
    }

    /**
     * Число
     */
    @Nullable
    public String getNum() {
        return mNum;
    }
}
