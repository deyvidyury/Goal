package com.yury.goal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yury.goal.Fragments.ProjectFragment;
import com.yury.goal.R;
import com.yury.goal.adapters.ProjectsAdapter;
import com.yury.goal.adapters.TasksAdapter;
import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Project;
import com.yury.goal.classes.Task;

public class ProjectActivity extends AppCompatActivity {
    private int projPosition = 0;
    private TasksAdapter tasksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

//        String project = getIntent().getStringExtra("project");
//        getSupportActionBar().setTitle(project);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if(savedInstanceState == null){
//            ProjectFragment fragment = new ProjectFragment();
//            fragment.setArguments(getIntent().getExtras());
//            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.add(R.id.layoutFrag,fragment,"ProjectFragment");
//            fragmentTransaction.commit();
//        }

        projPosition = getIntent().getIntExtra("position",projPosition);


        Project project = Manager.getInstance().getProjects().get(projPosition);

        TextView projectName = (TextView)findViewById(R.id.projectName);
        TextView projectProgress = (TextView)findViewById(R.id.projectProgress);
        TextView projrectBudget = (TextView)findViewById(R.id.projectBudget);
        TextView projectDaysLeft = (TextView)findViewById(R.id.projectDaysLeft);

        projectName.setText(project.getName()+"");
        projectProgress.setText(project.getProgress()+" %");
        projrectBudget.setText("$ "+project.getBudget()+"");
        projectDaysLeft.setText("20 days left");

        ListView listView = (ListView)findViewById(R.id.listViewTasks);
        tasksAdapter = new TasksAdapter(this,projPosition);
        listView.setAdapter(tasksAdapter);
        listView.setOnItemClickListener(onItemClickTask());
    }

    @Override
    public void onResume() {
        super.onResume();
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tasksAdapter.notifyDataSetChanged();
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickTask(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TasksAdapter adapter = (TasksAdapter) parent.getAdapter();

                Task task = (Task)adapter.getItem(position);
                Intent intent = new Intent(getBaseContext(),TaskActivity.class);
                intent.putExtra("task",task.getName());
                intent.putExtra("project",projPosition);
                intent.putExtra("position",position);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.editProject){
            Intent intent = new Intent(this,EditProject.class);
            intent.putExtra("position",projPosition);
            startActivity(intent);
            return true;
        } else if (id == R.id.deleteProject){
            new AlertDialog.Builder(this)
                    .setTitle("Delete Project")
                    .setMessage("Do you really want to delete this project?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getBaseContext(),"Project deleted",Toast.LENGTH_SHORT).show();

                            Manager.getInstance().delete(projPosition);

                            finish();

                        }})
                    .setNegativeButton(android.R.string.no, null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void newTask(View view) {
        Intent intent = new Intent(this,NewTaskActivity.class);
        intent.putExtra("project",projPosition);
        startActivity(intent);
    }
}
