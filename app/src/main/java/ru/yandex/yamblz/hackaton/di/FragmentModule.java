package ru.yandex.yamblz.hackaton.di;

import android.os.Handler;

import java.util.concurrent.Executor;

import dagger.Module;
import dagger.Provides;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenter;
import ru.yandex.yamblz.hackaton.ui.presenters.ComposeTranslationPresenterImpl;

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
}
