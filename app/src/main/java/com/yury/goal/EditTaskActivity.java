package com.yury.goal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yury.goal.classes.Manager;
import com.yury.goal.classes.Project;
import com.yury.goal.classes.Status;
import com.yury.goal.classes.Task;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditTaskActivity extends AppCompatActivity {
    private String taskName;
    private Date startDate;
    private Date finishDate;
    private String description;

    DatePickerDialog datePickerDialog;

    private int projPosition;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        projPosition = getIntent().getIntExtra("project",projPosition);
        position = getIntent().getIntExtra("position",position);

        Project project = Manager.getInstance().getProjects().get(projPosition);
        final Task task = project.getSections().get(0).getTasks().get(position);

        TextView projectName = (TextView)findViewById(R.id.projectName);
        projectName.setText(project.getName());

        EditText etTaskName = (EditText)findViewById(R.id.etTaskName);
        etTaskName.setText(task.getName());

        final EditText etStartDate = (EditText)findViewById(R.id.startDate);
        etStartDate.setText(task.getStringStartDate());
        etStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int year = task.getStartDate().getYear();
                final int month = task.getStartDate().getMonth();
                int day = task.getStartDate().getDay();

                datePickerDialog = new DatePickerDialog(EditTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etStartDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        final EditText etFinishDate = (EditText)findViewById(R.id.finishDate);
        etFinishDate.setText(task.getStringEndDate());
        etFinishDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int year = task.getEndDate().getYear();
                final int month = task.getEndDate().getMonth();
                int day = task.getEndDate().getDay();

                datePickerDialog = new DatePickerDialog(EditTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etFinishDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        EditText etDescription = (EditText)findViewById(R.id.description);
        etDescription.setText(task.getDescription());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_project_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveProject:
                saveTask();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveTask(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        EditText etTaskName = (EditText)findViewById(R.id.etTaskName);
        taskName = etTaskName.getText().toString();

        EditText etStartDate = (EditText)findViewById(R.id.startDate);
        try {
            startDate = sdf.parse(etStartDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EditText etFinishDate = (EditText)findViewById(R.id.finishDate);
        try {
            finishDate = sdf.parse(etFinishDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        EditText etDescription = (EditText)findViewById(R.id.description);
        description = etDescription.getText().toString();

        if(taskName.trim().equals("")){
            etTaskName.setError("You need to enter a name");
        } else if (etStartDate.getText().toString().trim().equals("")){
            etStartDate.setError("A start date is needed.");
        } else if (etFinishDate.getText().toString().trim().equals("")){
            etFinishDate.setError("A end date is needed.");
        } else if (Days.daysBetween(new DateTime(startDate),new DateTime(finishDate)).getDays() < 0){
            etFinishDate.setError("End day must be after start date.");
        } else {
            Project project = Manager.getInstance().getProjects().get(projPosition);
            Task task = project.getSections().get(0).getTasks().get(position);
            task.update(taskName,startDate,finishDate,description);

            Intent intent = new Intent(this,ProjectActivity.class);
            startActivity(intent);

            Toast.makeText(this,"Task succesfully updated.",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
