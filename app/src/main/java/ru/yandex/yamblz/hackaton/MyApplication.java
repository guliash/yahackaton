package ru.yandex.yamblz.hackaton;

import android.app.Application;

import ru.yandex.yamblz.hackaton.di.AppComponent;
import ru.yandex.yamblz.hackaton.di.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
