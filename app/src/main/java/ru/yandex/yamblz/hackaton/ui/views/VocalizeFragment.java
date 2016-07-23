package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.flexbox.FlexboxLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Task;
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

    @BindView(R.id.backspace)
    ImageButton backspace;

    @BindView(R.id.compose_progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.bnt_close)
    ImageButton buttonClose;

    @BindView(R.id.btn_skip)
    ImageView buttonSkip;

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

        progressBar.setMax(Task.WORDS_IN_ROUND);

        return view;
    }

    @OnClick(R.id.btn_skip)
    void btnSkipClick() {
        moveToNext();
    }

    @OnClick(R.id.bnt_close)
    void btnCloseClick() {
        getActivity().getSupportFragmentManager().popBackStack();
    }

    @OnClick(R.id.vocalize)
    public void vocalize() {
        presenter.vocalize();
    }

    private void moveToNext() {
        presenter.getWord();
        answer.setError(null);
        answer.setText("");
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
                            presenter.checkAnswer(answer.getText().toString());
                        }
                    }
                }
            });
            view.setText(Character.toString(ch));
            lettersBox.addView(view);
        }
    }

    @Override
    public void showCorrect() {
        Snackbar.make(answer, getString(R.string.correct), Snackbar.LENGTH_LONG).show();
        progressBar.setProgress(progressBar.getProgress() + 1);
        if (progressBar.getProgress() == Task.WORDS_IN_ROUND) {
            getActivity().getSupportFragmentManager().popBackStack();
        }
        moveToNext();
    }

    @Override
    public void showWrong() {
        answer.setError(getString(R.string.wrong));
        answer.setText("");
    }


    @OnClick(R.id.backspace)
    void onBackspaceClick() {
        String str = answer.getText().toString();
        if(str.length() > 0) {
            answer.setText(str.substring(0, answer.length() - 1));
        }
    }
}
