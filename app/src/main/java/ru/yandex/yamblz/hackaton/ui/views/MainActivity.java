package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Task;
import ru.yandex.yamblz.hackaton.ui.adapters.MainFragmentAdapter;

public class MainActivity extends BaseActivity implements MainFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new MainFragment()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFragment(String task) {
        Fragment fragment = null;
        switch (task) {
            case Task.COMPOSE_TRANSLATION:
                fragment = new ComposeTranslationFragment();
                break;
        }
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_layout, fragment)
                    .addToBackStack(null).commit();
        }
    }

    @Override
    public void onTaskSelected(String task) {
        showFragment(task);
    }
}
