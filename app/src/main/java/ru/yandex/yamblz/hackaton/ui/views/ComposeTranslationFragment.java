package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.di.DaggerFragmentComponent;
import ru.yandex.yamblz.hackaton.di.FragmentComponent;
import ru.yandex.yamblz.hackaton.dictionary.Helper;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenter;

public class ComposeTranslationFragment extends BaseFragment implements ComposeTranslationView {

    @Inject
    ComposeTranslationPresenter presenter;

    @BindView(R.id.word)
    TextView wordTextView;


    @BindView(R.id.letters)
    FlexboxLayout lettersBox;

    @BindView(R.id.answer)
    EditText answer;

    @BindView(R.id.backspace)
    ImageButton backspace;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentComponent component = DaggerFragmentComponent.builder().appComponent(getAppComponent()).build();
        component.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onViewAttach(this);
        presenter.getQuiz();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onViewDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compose_fragmemt, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showQuiz(String word, String translate) {
        wordTextView.setText(word);
        addButtons(translate);
    }

    @Override
    public void showWrongAnswer() {
        answer.setError(getString(R.string.wrong));
        answer.setText("");
    }

    @Override
    public void showCorrectAnswer() {
        Snackbar.make(answer, getString(R.string.correct), Snackbar.LENGTH_LONG).show();
        presenter.getQuiz();
        answer.setError(null);
        answer.setText("");
    }

    private void addButtons(final String translate) {
        lettersBox.removeAllViews();
        char[] shuffled = Helper.shuffle(translate.toCharArray());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        for(char ch : shuffled) {
            final Button view = (Button)inflater.inflate(R.layout.letter, lettersBox, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(answer.getText().length() < translate.length()) {
                        Vocalizer vocalizer = Vocalizer.createVocalizer("en", view.getText().toString(), true);
                        answer.setText(answer.getText().toString() + view.getText().toString());
                        if(answer.getText().length() == translate.length()) {
                            presenter.checkQuiz(answer.getText().toString());
                        }
                    }
                }
            });
            view.setText(Character.toString(ch));
            lettersBox.addView(view);
        }
    }

    @OnClick(R.id.backspace)
    void onBackspaceClick() {
        String str = answer.getText().toString();
        if(str.length() > 0) {
            answer.setText(str.substring(0, answer.length() - 1));
        }
    }


}
