package com.yury.goal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yury.goal.adapters.ProjectsAdapater;
import com.yury.goal.adapters.TasksAdapter;
import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Project;

import java.util.List;

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listViewProjects;
    private ListView listViewTasks;
    public static ProjectsAdapater projectsAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Listview for Projects
        listViewProjects = (ListView)findViewById(R.id.listViewProjects);
        projectsAdapater = new ProjectsAdapater(this,Manager.getInstance().getProjects());
        listViewProjects.setAdapter(projectsAdapater);
        listViewProjects.setOnItemClickListener(this);

        // ListView for Tasks
        listViewTasks = (ListView)findViewById(R.id.listViewTasks);
        listViewTasks.setAdapter(new TasksAdapter(this,Manager.getInstance().getTasks()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                projectsAdapater.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Project project = Manager.getInstance().getProjects().get(position);
        Toast.makeText(this,project.getName(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.newProject:
                startActivity(new Intent(this,NewProject.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
