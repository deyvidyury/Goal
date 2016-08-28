package com.yury.goal.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Project {
    private String name;
    private Date startDate;
    private Date endDate;
    private double budget;
    private List<Section> sections;
    private double progress;

    public Project(String name, Date startDate, Date endDate, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
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

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
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
            for(Task t : e.getTasks()){
                totalTasks++;
                if(t.getStatus() == Status.DONE){
                    tasksDone++;
                }
            }
        }

        return (tasksDone/totalTasks)*100;
    }
}
