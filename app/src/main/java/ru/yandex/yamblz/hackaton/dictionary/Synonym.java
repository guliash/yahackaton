package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Synonym {

    @SerializedName("text")
    private String mText;

    @SerializedName("pos")
    private String mPos;

    @SerializedName("gen")
    private String mGen;

    /**
     * Текст синонима
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
     * Пол
     */
    @Nullable
    public String getGen() {
        return mGen;
    }
}
