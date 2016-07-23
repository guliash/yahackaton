package ru.yandex.yamblz.hackaton.ui.presenters;

import ru.yandex.yamblz.hackaton.ui.views.VocalizeView;

public interface VocalizePresenter {

    void onViewAttach(VocalizeView view);

    void onViewDetach();

    void vocalize();

    void getWord();

    void checkAnswer(String answer);

}
