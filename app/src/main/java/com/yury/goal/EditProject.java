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
import com.yury.goal.classes.Section;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditProject extends AppCompatActivity {
    private String projectName;
    private Date startDate;
    private Date finishDate;
    private double budget;
    private String section;
    private int position = 0;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);


        position = getIntent().getIntExtra("position",position);

        final Project project = Manager.getInstance().getProjects().get(position);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        EditText etProjectName = (EditText)findViewById(R.id.etProjectName);
        etProjectName.setText(project.getName());

        final EditText etStartDate = (EditText)findViewById(R.id.startDate);
        etStartDate.setText(project.getStringStartDate());
        etStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int year = project.getStartDate().getYear();
                final int month = project.getStartDate().getMonth();
                int day = project.getStartDate().getDay();

                datePickerDialog = new DatePickerDialog(EditProject.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etStartDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        final EditText etFinishDate = (EditText)findViewById(R.id.finishDate);
        etFinishDate.setText(project.getStringEndDate());
        etFinishDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int year = project.getEndDate().getYear();
                final int month = project.getEndDate().getMonth();
                int day = project.getEndDate().getDay();

                datePickerDialog = new DatePickerDialog(EditProject.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etFinishDate.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        EditText etBudget = (EditText)findViewById(R.id.budget);
        etBudget.setText(project.getBudget()+"");

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
                saveProject();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveProject(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);

        EditText etProjectName = (EditText)findViewById(R.id.etProjectName);
        projectName = etProjectName.getText().toString();

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

        EditText etBudget = (EditText)findViewById(R.id.budget);
        budget = Double.parseDouble(etBudget.getText().toString().equals("") ? "0" : etBudget.getText().toString());

        EditText etSection = (EditText)findViewById(R.id.sectionName);
        section = etSection.getText().toString();

        if(projectName.trim().equals("")){
            etProjectName.setError("You need to enter a project name");
        } else if (etStartDate.getText().toString().trim().equals("")){
            etStartDate.setError("A start date is needed.");
        } else if (etFinishDate.getText().toString().trim().equals("")){
            etFinishDate.setError("A end date is needed.");
        } else if (Days.daysBetween(new DateTime(startDate),new DateTime(finishDate)).getDays() < 0){
            etFinishDate.setError("End day must be after start date.");
        } else {
            Project project = Manager.getInstance().getProjects().get(position);

            project.setName(projectName);
            project.setStartDate(startDate);
            project.setEndDate(finishDate);
            project.setBudget(budget);

            Toast.makeText(this,"Project succesfully updated.",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ProjectActivity.class);
            finish();
            startActivity(intent);
        }
    }
}
