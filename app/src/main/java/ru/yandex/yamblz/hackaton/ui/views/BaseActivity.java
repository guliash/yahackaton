package ru.yandex.yamblz.hackaton.ui.views;

import android.support.v7.app.AppCompatActivity;

import ru.yandex.yamblz.hackaton.MyApplication;
import ru.yandex.yamblz.hackaton.di.AppComponent;

public class BaseActivity extends AppCompatActivity {

    protected AppComponent getAppComponent() {
        return ((MyApplication)getApplicationContext()).getAppComponent();
    }

}
