package ru.yandex.yamblz.hackaton.ui.presenters;

import ru.yandex.yamblz.hackaton.ui.views.ChooseTranslationView;

public class ChooseTranslationPresenterImpl implements ChooseTranslationPresenter {

    private ChooseTranslationView mView;

    @Override
    public void onViewAttach(ChooseTranslationView view) {
        this.mView = view;
    }

    @Override
    public void onViewDetach() {
        this.mView = null;
    }
}
