package ru.yandex.yamblz.hackaton.ui.views;

public interface ComposeTranslationView {
    void showQuiz(String word, String translate);

    void showWrongAnswer();

    void showCorrectAnswer();
}
