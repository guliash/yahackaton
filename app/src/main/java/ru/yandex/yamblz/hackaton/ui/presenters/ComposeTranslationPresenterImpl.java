package ru.yandex.yamblz.hackaton.ui.presenters;

import android.os.Handler;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import ru.yandex.yamblz.hackaton.core.Word;
import ru.yandex.yamblz.hackaton.dictionary.DicResult;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.dictionary.Helper;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;
import ru.yandex.yamblz.hackaton.ui.views.ComposeTranslationView;

public class ComposeTranslationPresenterImpl implements ComposeTranslationPresenter {

    private ComposeTranslationView mView;

    private WordsStorage wordsStorage;
    private Dictionary dictionary;
    private Handler handler;
    private Executor workerExecutor;

    private String mTranslation;

    public ComposeTranslationPresenterImpl(WordsStorage wordsStorage, Dictionary dictionary,
                                           Handler handler, Executor workerExecutor) {
        this.wordsStorage = wordsStorage;
        this.dictionary = dictionary;
        this.handler = handler;
        this.workerExecutor = workerExecutor;
    }

    @Override
    public void onViewAttach(ComposeTranslationView view) {
        this.mView = view;
    }

    @Override
    public void onViewDetach() {
        this.mView = null;
    }

    @Override
    public void getQuiz() {
        workerExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final Word word = wordsStorage.getRandomWord("en");
                DicResult dicResult = dictionary.lookup("en-ru", word.getText(), null, 0);
                final String translation = Helper.getTranslation(dicResult);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        postQuiz(word.getText(), translation);
                    }
                });
            }
        });
    }

    private void postQuiz(String word, String translation) {
        this.mTranslation = translation;
        if(mView != null) {
            mView.showQuiz(word, translation);
        }
    }

    @Override
    public void checkQuiz(String answer) {
        if(answer.equals(mTranslation)) {
            mView.showCorrectAnswer();
        } else {
            mView.showWrongAnswer();
        }
    }

}
