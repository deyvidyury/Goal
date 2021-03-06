package com.yury.goal.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yury.goal.R;
import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deyvidyury on 27/08/16.
 */
public class TasksAdapter extends BaseAdapter {
    private final Context context;
    private List<Task> tasks = new ArrayList<Task>();

    public TasksAdapter(Context context,int position) {
        this.context = context;
        tasks = Manager.getInstance().getProjects().get(position).getTasks();
    }

    @Override
    public int getCount() {
        return tasks != null ? tasks.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla view
        View view = LayoutInflater.from(context).inflate(R.layout.tasks_adapter,parent,false);
        // Faz findViewById das views que precisa atualizar
        TextView taskName = (TextView)view.findViewById(R.id.taskName);
        TextView taskProgress = (TextView)view.findViewById(R.id.taskProgress);
        //Atualiza os valores das views
        Task task = tasks.get(position);
        taskName.setText(task.getName());
        taskProgress.setText(task.getDaysLeft()+" days");
        Log.d("TASK",getCount()+"");
        return view;
    }
}
