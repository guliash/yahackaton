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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Task;
import ru.yandex.yamblz.hackaton.di.DaggerFragmentComponent;
import ru.yandex.yamblz.hackaton.di.FragmentComponent;
import ru.yandex.yamblz.hackaton.dictionary.Helper;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenter;

public class ComposeTranslationFragment extends BaseFragment implements ComposeTranslationView {

    @Inject
    ComposeTranslationPresenter presenter;

    @BindView(R.id.word)
    TextView wordTextView;

    @BindView(R.id.bnt_close)
    ImageButton buttonClose;

    @BindView(R.id.btn_skip)
    ImageView buttonSkip;

    @BindView(R.id.compose_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.letters)
    FlexboxLayout lettersBox;

    @BindView(R.id.answer)
    EditText answer;

    List<View> list;

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
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNext();
            }
        });
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        progressBar.setMax(Task.WORDS_IN_ROUND);
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
        progressBar.setProgress(progressBar.getProgress() + 1);
        if (progressBar.getProgress() == Task.WORDS_IN_ROUND) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
        moveToNext();
    }

    private void moveToNext() {
        presenter.getQuiz();
        answer.setError(null);
        answer.setText("");
    }

    private void addButtons(final String translate) {
        lettersBox.removeAllViews();
        char[] shuffled = Helper.shuffle(translate.toCharArray());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        list = new ArrayList<>();
        for(char ch : shuffled) {
            final Button view = (Button)inflater.inflate(R.layout.letter, lettersBox, false);

            view.setTextColor(getResources().getColor(R.color.text_primary));
            view.setBackgroundColor(getResources().getColor(R.color.background_enabled));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setEnabled(false);
                    ((Button)v).setTextColor(getResources().getColor(R.color.text_secondary));
                    v.setBackgroundColor(getResources().getColor(R.color.background_disabled));
                    if(answer.getText().length() < translate.length()) {
                        answer.setText(answer.getText().toString() + view.getText().toString());
                        if(answer.getText().length() == translate.length()) {
                            presenter.checkQuiz(answer.getText().toString());
                        }
                    }
                }
            });
            view.setText(Character.toString(ch));
            lettersBox.addView(view);
            list.add(view);
        }
    }

    void onBackspaceClick() {
        String str = answer.getText().toString();
        if(str.length() > 0) {
            answer.setText(str.substring(0, answer.length() - 1));
        }
    }


}
