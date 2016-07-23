package ru.yandex.yamblz.hackaton.ui.views;

import android.support.v4.app.Fragment;

import ru.yandex.yamblz.hackaton.MyApplication;
import ru.yandex.yamblz.hackaton.di.AppComponent;

public class BaseFragment extends Fragment {

    protected AppComponent getAppComponent() {
        return ((MyApplication)getContext().getApplicationContext()).getAppComponent();
    }

}
