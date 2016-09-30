package com.yury.goal.classes;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Project {
    private static int ID = 0;

    private String name;
    private Date startDate;
    private Date endDate;
    private double budget;
    private List<Section> sections;
    private double progress;
    private Status status;
    private int projectId;

    public Project(String name, Date startDate, Date endDate, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.projectId = ID;
        ID+=1;
        sections = new ArrayList<Section>();
        Section mySection = new Section("My Tasks");
        sections.add(mySection);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getStringStartDate(){
        return startDate.getDay()+"/"+(startDate.getMonth()+1)+"/"+startDate.getYear();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getStringEndDate(){
        return endDate.getDay()+"/"+(endDate.getMonth()+1)+"/"+endDate.getYear();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section){
        this.sections.add(section);
    }

    public void addSection(List<Section> sections){
        this.sections.addAll(sections);
    }

    public double getProgress(){
        double totalTasks = 0;
        double tasksDone = 0;
        for (Section e: sections) {
            if(e.getTasks().size() == 0){
                return 0;
            } else {
                for(Task t : e.getTasks()){
                    totalTasks++;
                    if(t.getStatus() == Status.DONE){
                        tasksDone++;
                    }
                }
            }
        }

        return (tasksDone/totalTasks)*100;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        SimpleDateFormat sf = new SimpleDateFormat("dd/mm/yyyyy");
        return getName()+", from: "+sf.format(getStartDate())+" to "+sf.format(getEndDate());
    }

    public int getDaysLeft(){
        return Days.daysBetween(new DateTime(), new DateTime(endDate.getTime())).getDays();
    }

    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<Task>();

        for(Section section : sections){
            tasks.addAll(section.getTasks());
        }

        return tasks;
    }
}
