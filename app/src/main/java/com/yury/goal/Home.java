package com.yury.goal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yury.goal.adapters.ProjectsAdapater;
import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Project;

public class Home extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listViewProjects;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Listview
        listViewProjects = (ListView)findViewById(R.id.listViewProjects);
        listViewProjects.setAdapter(new ProjectsAdapater(this,Manager.getInstance().getProjects()));
        listViewProjects.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Project project = Manager.getInstance().getProjects().get(position);
        Toast.makeText(this,project.getName(),Toast.LENGTH_SHORT).show();
    }
}
