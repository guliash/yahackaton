package ru.yandex.yamblz.hackaton.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.yandex.yamblz.hackaton.R;

public class MainFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER = 0;
    private final int FOOTER = 2;
    private final int TASK = 1;

    List<String> tasksList;

    public MainFragmentAdapter(List<String> list) {
        tasksList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TASK) {
            View view = layoutInflater.inflate(R.layout.task_button, parent, false);
            return new TaskHolder(view);
        } else if (viewType == HEADER) {
            View view = layoutInflater.inflate(R.layout.header, parent, false);
            return new Holder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.footer, parent, false);
            return new Holder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TASK) {
            ((TaskHolder) holder).name.setText(tasksList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        if (position == getItemCount() - 1)
            return FOOTER;
        return TASK;
    }

    @Override
    public int getItemCount() {
        return tasksList.size() + 2;
    }

    private static class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    private static class TaskHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public TaskHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.task_text);
        }
    }
}
