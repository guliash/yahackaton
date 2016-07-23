package ru.yandex.yamblz.hackaton.di;

import dagger.Component;
import ru.yandex.yamblz.hackaton.ui.views.ComposeTranslationFragment;
import ru.yandex.yamblz.hackaton.ui.views.VocalizeFragment;

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(ComposeTranslationFragment composeTranslationFragment);
    void inject(VocalizeFragment composeTranslationFragment);
}
