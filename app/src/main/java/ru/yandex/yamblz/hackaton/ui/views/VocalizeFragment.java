package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.flexbox.FlexboxLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Word;
import ru.yandex.yamblz.hackaton.di.DaggerFragmentComponent;
import ru.yandex.yamblz.hackaton.dictionary.Helper;
import ru.yandex.yamblz.hackaton.ui.presenters.VocalizePresenter;

public class VocalizeFragment extends BaseFragment implements VocalizeView {

    @Inject
    VocalizePresenter presenter;

    @BindView(R.id.letters)
    FlexboxLayout lettersBox;

    @BindView(R.id.answer)
    EditText answer;

    @BindView(R.id.vocalize)
    ImageButton vocalize;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerFragmentComponent.builder().appComponent(getAppComponent()).build().inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttach(this);
        presenter.getWord();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vocalize, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.vocalize)
    public void vocalize() {
        presenter.vocalize();
    }

    @Override
    public void showWord(final Word word) {
        lettersBox.removeAllViews();
        char[] shuffled = Helper.shuffle(word.getText().toCharArray());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        for(char ch : shuffled) {
            final Button view = (Button)inflater.inflate(R.layout.letter, lettersBox, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(answer.getText().length() < word.getText().length()) {
                        answer.setText(answer.getText().toString() + view.getText().toString());
                        if(answer.getText().length() == word.getText().length()) {
                        }
                    }
                }
            });
            view.setText(Character.toString(ch));
            lettersBox.addView(view);
        }
    }
}
