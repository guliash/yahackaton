package ru.yandex.yamblz.hackaton.di;

import dagger.Component;
import ru.yandex.yamblz.hackaton.ui.views.ComposeTranslationFragment;

@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(ComposeTranslationFragment composeTranslationFragment);
}
