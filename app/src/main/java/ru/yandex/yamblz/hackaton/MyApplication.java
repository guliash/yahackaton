package ru.yandex.yamblz.hackaton;

import android.app.Application;

import ru.yandex.yamblz.hackaton.di.AppComponent;
import ru.yandex.yamblz.hackaton.di.AppModule;
import ru.yandex.yamblz.hackaton.di.DaggerAppComponent;

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
