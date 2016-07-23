package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;

import ru.yandex.yamblz.hackaton.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new MainFragment()).commit();
    }
}
