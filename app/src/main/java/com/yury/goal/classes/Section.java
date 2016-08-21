package com.yury.goal.classes;

import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Section {
    private String name;
    private List<Task> tasks;
    private double progress;

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
