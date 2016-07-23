package ru.yandex.yamblz.hackaton.dictionary;

import com.google.gson.annotations.SerializedName;

public class Meaning {

    @SerializedName("text")
    private String mText;

    /**
     * Текст значения
     */
    public String getText() {
        return mText;
    }
}
