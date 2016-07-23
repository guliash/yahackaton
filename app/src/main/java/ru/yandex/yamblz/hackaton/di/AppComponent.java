package ru.yandex.yamblz.hackaton.di;

import android.os.Handler;

import javax.inject.Singleton;

import dagger.Component;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.storage.WordsStorage;
import ru.yandex.yamblz.hackaton.ui.views.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Dictionary dictionary();

    Handler mainThreadHandler();

    WordsStorage wordsStorage();

    void inject(MainActivity activity);

}
