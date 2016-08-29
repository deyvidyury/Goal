package com.yury.goal.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Section {
    private static int ID = 0;
    private String name;
    private List<Task> tasks;
    private double progress;
    private int projectId;
    private int sectionId;

    public Section(String name) {
        tasks = new ArrayList<Task>();
        this.name = name;
        this.sectionId = ID;
        ID += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSectionId() {
        return sectionId;
    }

    public double getProgress(){
        double totalTasks = tasks.size();
        double tasksDone = 0;
        for(Task t : tasks){
            if(t.getStatus() == Status.DONE){
                tasksDone++;
            }
        }

        return (tasksDone / totalTasks) * 100;
    }
}
