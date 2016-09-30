package com.yury.goal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yury.goal.R;
import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Project;

/**
 * Created by deyvidyury on 6/09/16.
 */
public class ProjectAdapter extends BaseAdapter {
    private Context context;

    public ProjectAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Project project = Manager.getInstance().getProjects().get(position);
        View view = LayoutInflater.from(context).inflate(R.layout.project_adapter,parent,false);
        TextView projectProgress = (TextView)view.findViewById(R.id.projectProgress);
        TextView projrectBudget = (TextView)view.findViewById(R.id.projectBudget);
        TextView projectDaysLeft = (TextView)view.findViewById(R.id.projectDaysLeft);

        projectProgress.setText(project.getProgress()+"");
        projrectBudget.setText(project.getBudget()+"");
        projectDaysLeft.setText("20 days");
        return view;
    }
}
