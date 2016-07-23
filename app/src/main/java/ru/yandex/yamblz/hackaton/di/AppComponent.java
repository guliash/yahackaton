package ru.yandex.yamblz.hackaton.di;

import android.os.Handler;

import javax.inject.Singleton;

import dagger.Component;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;
import ru.yandex.yamblz.hackaton.ui.MainActivity;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Dictionary dictionary();

    Handler mainThreadHandler();

    void inject(MainActivity activity);

}
