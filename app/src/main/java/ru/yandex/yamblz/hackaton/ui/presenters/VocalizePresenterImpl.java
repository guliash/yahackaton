package ru.yandex.yamblz.hackaton.ui.presenters;

import android.os.Handler;

import java.util.concurrent.Executor;

import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.yamblz.hackaton.core.Word;
import ru.yandex.yamblz.hackaton.dictionary.DicResult;
import ru.yandex.yamblz.hackaton.dictionary.Helper;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;
import ru.yandex.yamblz.hackaton.ui.views.VocalizeView;

public class VocalizePresenterImpl implements VocalizePresenter {

    private VocalizeView view;

    private SpeechKit speechKit;
    private Handler handler;
    private Executor workerExecutor;
    private WordsStorage wordsStorage;

    private Word word;

    public VocalizePresenterImpl(WordsStorage wordsStorage, SpeechKit speechKit, Handler handler,
                                 Executor workerExecutor) {
        this.wordsStorage = wordsStorage;
        this.speechKit = speechKit;
        this.handler = handler;
        this.workerExecutor = workerExecutor;
    }

    @Override
    public void onViewAttach(VocalizeView view) {
        this.view = view;
    }

    @Override
    public void onViewDetach() {
        view = null;
    }

    @Override
    public void vocalize() {
        if(word != null) {
            Vocalizer vocalizer = Vocalizer.createVocalizer(Vocalizer.Language.ENGLISH, word.getText(),
                    true);
            vocalizer.start();
        }
    }

    @Override
    public void getWord() {
        workerExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final Word word = wordsStorage.getRandomWord("en");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        postWord(word);
                    }
                });
            }
        });
    }

    private void postWord(Word word) {
        if(view != null) {
            this.word = word;
            view.showWord(word);
        }
    }
}
