package ru.yandex.yamblz.hackaton.ui;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.dictionary.Dictionary;

public class MainActivity extends BaseActivity {

    @Inject
    Dictionary dictionary;

    @Inject
    Handler uiHandler;

    private Handler workerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getAppComponent().inject(this);
        ButterKnife.bind(this);
        initWorkerHandler();
        workerHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<String> langs = dictionary.getLangs();
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

    }

    private void initWorkerHandler() {
        HandlerThread handlerThread = new HandlerThread("worker");
        handlerThread.start();
        workerHandler = new Handler(handlerThread.getLooper());
    }
}
