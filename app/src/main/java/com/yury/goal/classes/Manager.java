package com.yury.goal.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Manager {
    private List<Project> projects;

    private static Manager ourInstance = new Manager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
        projects = new ArrayList<Project>();

        Project project1 = new Project("Project A",new Date(2016,1,1),new Date(2016,10,01),1000.0);
        Project project2 = new Project("Project B",new Date(2016,2,1),null,0);

        Section section1Project2 = new Section("Section A");
        Section section2Project2 = new Section("Section B");

        Task task1project1 = new Task("Task 1",new Date(2016,1,1),new Date(2016,1,10),Status.TODO,"This is the task 1 for project 1");
        Task task2project1 = new Task("Task 2",new Date(2016,1,3),new Date(2016,3,13),Status.TODO,"This is the task 2 for project 1");
        Task task3project1 = new Task("Task 3",new Date(2016,1,5),new Date(2016,5,10),Status.TODO,"This is the task 3 for project 1");

        Task task1project2 = new Task("Task 1",new Date(2016,1,1),new Date(2016,1,10),Status.TODO,"This is the task 1 for project 2");
        Task task2project2 = new Task("Task 2",new Date(2016,2,3),new Date(2016,3,13),Status.TODO,"This is the task 2 for project 2");
        Task task3project2 = new Task("Task 3",new Date(2016,3,5),new Date(2016,5,10),Status.TODO,"This is the task 3 for project 2");
        Task task4project2 = new Task("Task 4",new Date(2016,4,5),new Date(2016,5,10),Status.TODO,"This is the task 4 for project 2");
        Task task5project2 = new Task("Task 5",new Date(2016,5,5),new Date(2016,5,10),Status.TODO,"This is the task 5 for project 2");

        project1.getSections().get(0).addTask(task1project1);
        project1.getSections().get(0).addTask(task2project1);
        project1.getSections().get(0).addTask(task3project1);

        section1Project2.addTask(task1project2);
        section1Project2.addTask(task2project2);

        section2Project2.addTask(task3project2);
        section2Project2.addTask(task4project2);
        section2Project2.addTask(task5project2);

        project2.addSection(section1Project2);
        project2.addSection(section2Project2);

        projects.add(project1);
        projects.add(project2);
    }

    public List<Project> getProjects(){
        return this.projects;
    }

    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<Task>();
        for(Project project : projects){
            for(Section section : project.getSections()){
                tasks.addAll(section.getTasks());
            }
        }
        return tasks;
    }
}
