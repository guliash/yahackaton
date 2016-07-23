package ru.yandex.yamblz.hackaton.ui.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.ui.adapters.MainFragmentAdapter;

public class MainFragment extends BaseFragment {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    private static final List<String> TASKS = new ArrayList<>();

    static {
        TASKS.add("1");
        TASKS.add("2");
        TASKS.add("3");
        TASKS.add("4");
        TASKS.add("5");
        TASKS.add("6");
        TASKS.add("7");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MainFragmentAdapter(TASKS));

        return view;
    }
}
