package ru.yandex.yamblz.hackaton.ui;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;

public class MainActivity extends BaseActivity {

    @Inject
    Dictionary dictionary;

    @BindView(R.id.text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dictionary = getAppComponent().dictionary();
        textView.setText(dictionary.getLangs().toString());

    }
}
