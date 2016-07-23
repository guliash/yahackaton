package ru.yandex.yamblz.hackaton.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Dictionary dictionary();

}
