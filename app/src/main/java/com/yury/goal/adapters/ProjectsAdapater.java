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

import java.util.List;

/**
 * Created by deyvidyury on 27/08/16.
 */
public class ProjectsAdapater extends BaseAdapter{
    private List<Project> projects = Manager.getInstance().getProjects();
    private Context context;

    public ProjectsAdapater(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return projects != null ? projects.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return projects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla view
        View view = LayoutInflater.from(context).inflate(R.layout.project_adapter,parent,false);
        // Faz findViewById das views que precisa atualizar
        TextView projectName = (TextView)view.findViewById(R.id.projectName);
        TextView projectProgress = (TextView)view.findViewById(R.id.projectProgress);
        //Atualiza os valores das views
        Project project = projects.get(position);
        projectName.setText(project.getName());
        projectProgress.setText(project.getProgress()+"%");
        return view;
    }

    public List getProjects(){
        return projects;
    }
}
