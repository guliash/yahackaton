package ru.yandex.yamblz.hackaton.ui.presenters;

import ru.yandex.yamblz.hackaton.ui.views.ComposeTranslationView;

public class ChooseTranslationPresenterImpl implements ChooseTranslationPresenter {

    private ComposeTranslationView mView;

    @Override
    public void onViewAttach(ComposeTranslationView view) {
        this.mView = view;
    }

    @Override
    public void onViewDetach() {
        this.mView = null;
    }
}
