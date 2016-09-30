package com.yury.goal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Task;

import java.text.SimpleDateFormat;

public class TaskActivity extends AppCompatActivity {
    private int projPosition;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        projPosition = getIntent().getIntExtra("project",projPosition);
        position = getIntent().getIntExtra("position",position);

        Task task = Manager.getInstance().getProjects().get(projPosition).getTasks().get(position);

        TextView txtTaskName = (TextView)findViewById(R.id.taskName);
        txtTaskName.setText(task.getName());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        TextView txtStartDate = (TextView)findViewById(R.id.taskStartDate);
        txtStartDate.setText(sdf.format(task.getStartDate()));

        TextView txtFinishDate = (TextView)findViewById(R.id.taskFinishDate);
        txtFinishDate.setText(sdf.format(task.getEndDate()));

        TextView txtDescription = (TextView)findViewById(R.id.taskDescription);
        txtDescription.setText(task.getDescription());
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
            Intent intent = new Intent(this,EditTaskActivity.class);
            intent.putExtra("project",projPosition);
            intent.putExtra("position",position);
            startActivity(intent);
            return true;
        } else if (id == R.id.deleteProject){
            new AlertDialog.Builder(this)
                    .setTitle("Delete Task")
                    .setMessage("Do you really want to delete this task?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getBaseContext(),"Task deleted",Toast.LENGTH_SHORT).show();

                            Task task = Manager.getInstance().getProjects().get(projPosition).getTasks().get(position);

                            Manager.getInstance().getProjects().get(projPosition).getTasks().remove(position);

                            finish();

                        }})
                    .setNegativeButton(android.R.string.no, null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
