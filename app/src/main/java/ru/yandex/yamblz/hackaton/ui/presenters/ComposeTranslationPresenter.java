package ru.yandex.yamblz.hackaton.ui.presenters;

import ru.yandex.yamblz.hackaton.ui.views.ComposeTranslationView;

public interface ComposeTranslationPresenter {

    void onViewAttach(ComposeTranslationView view);

    void onViewDetach();

    void getQuiz();

    void checkQuiz(String answer);

}
