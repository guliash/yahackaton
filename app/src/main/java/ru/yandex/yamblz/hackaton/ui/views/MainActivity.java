package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.util.Log;

import ru.yandex.yamblz.hackaton.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAG", getAppComponent().wordsStorage().getWords().toString());

    }
}
