package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.yandex.yamblz.hackaton.R;

public class ComposeTranslationFragment extends BaseFragment implements ComposeTranslationView {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compose_fragmemt, container, false);
        return view;
    }

    @Override
    public void showQuiz(String word, List<String> translations) {
        //TODO
    }
}
