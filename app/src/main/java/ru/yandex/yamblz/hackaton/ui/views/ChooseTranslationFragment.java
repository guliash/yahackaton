package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.yandex.yamblz.hackaton.ui.BaseFragment;

public class ChooseTranslationFragment extends BaseFragment implements ChooseTranslationView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showQuiz(String word, List<String> translations) {
        //TODO
    }
}