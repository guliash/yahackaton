package ru.yandex.yamblz.hackaton.core;

public class Word {

    private String mText;

    private int mRating;

    private String mLang;

    public Word(String text, int rating, String lang) {
        mText = text;
        mRating = rating;
        this.mLang = lang;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        this.mLang = lang;
    }

    @Override
    public String toString() {
        return mText;
    }
}
