package ru.yandex.yamblz.hackaton.dictionary;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translation {
    @SerializedName("text")
    private String mText;

    @SerializedName("pos")
    private String mPos;

    @SerializedName("gen")
    private String mGen;

    @SerializedName("syn")
    private List<Synonym> mSynonyms;

    /**
     * Текст перевода
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

    /**
     * Список синонимов
     */
    public List<Synonym> getSynonyms() {
        return mSynonyms;
    }
}
