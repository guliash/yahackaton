package ru.yandex.yamblz.hackaton.di;

import android.os.Handler;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenter;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenterImpl;
import ru.yandex.yamblz.hackaton.ui.presenters.VocalizePresenter;
import ru.yandex.yamblz.hackaton.ui.presenters.VocalizePresenterImpl;

@Module
public class FragmentModule {

    @PerFragment
    @Provides
    public ComposeTranslationPresenter provideComposeTranslationPresenter(WordsStorage storage,
                                                                          Dictionary dictionary,
                                                                          Handler handler,
                                                                          Executor executor) {
        return new ComposeTranslationPresenterImpl(storage, dictionary, handler, executor);
    }

    @PerFragment
    @Provides
    public VocalizePresenter provideVocalizePresenter(WordsStorage storage, SpeechKit speechKit, Handler handler,
                                                      Executor executor) {
        return new VocalizePresenterImpl(storage, speechKit, handler, executor);
    }
}
