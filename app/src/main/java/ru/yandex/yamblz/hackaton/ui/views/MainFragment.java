package ru.yandex.yamblz.hackaton.ui.views;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.yandex.yamblz.hackaton.R;
import ru.yandex.yamblz.hackaton.core.Task;
import ru.yandex.yamblz.hackaton.ui.adapters.MainFragmentAdapter;

public class MainFragment extends BaseFragment implements MainFragmentAdapter.Callbacks {

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    private Callbacks mCallbacks;

    public interface Callbacks {
        void onTaskSelected(String task);
    }

    private static final List<String> TASKS = new ArrayList<>();

    static {
        TASKS.add(Task.COMPOSE_TRANSLATION);
        TASKS.add("2");
        TASKS.add("3");
        TASKS.add("4");
        TASKS.add("5");
        TASKS.add("6");
        TASKS.add("7");
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(!(context instanceof Callbacks)) {
            throw new RuntimeException("Should implement interface");
        }
        mCallbacks = (Callbacks)context;
        getActivity().setTitle("Режим обучения");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MainFragmentAdapter(TASKS, this));
        return view;
    }

    @Override
    public void onClick(String task) {
        mCallbacks.onTaskSelected(task);
    }
}
