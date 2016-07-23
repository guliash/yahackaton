package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Task;

public class MainActivity extends BaseActivity implements MainFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_frame_layout, new MainFragment()).commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.text_primary));
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
            showPopupMenu(findViewById(id));
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.inflate(R.menu.main_popup_menu);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.statistic:

                                return true;
                            case R.id.words:

                                return true;
                            default:
                                return false;
                        }
                    }
                });
        popupMenu.show();
    }

    private void showFragment(String task) {
        Fragment fragment = null;
        switch (task) {
            case Task.COMPOSE_TRANSLATION:
                fragment = new ComposeTranslationFragment();
                break;
            case Task.VOCALIZE:
                fragment = new VocalizeFragment();
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
