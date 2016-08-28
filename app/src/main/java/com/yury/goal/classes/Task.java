package com.yury.goal.classes;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by deyvidyury on 20/08/16.
 */
public class Task {
    private String name;
    private Date startDate;
    private Date endDate;
    private Status status;
    private String description;
    private List<Expend> expends;
    private double totalExpends;
    private int daysLeft;
    private int projectId;

    public Task(String name, Date startDate, Date endDate, Status status, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.expends = new ArrayList<Expend>();
        this.description = description;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Expend> getExpends() {
        return expends;
    }

    public void setExpends(List<Expend> expends) {
        this.expends = expends;
    }

    public double getTotalExpends(){
        double total = 0;
        for(Expend e : expends){
            total += e.getPrice();
        }
        return total;
    }

    public int getDaysLeft(){
        return Days.daysBetween(new DateTime(startDate.getTime()), new DateTime(endDate.getTime())).getDays();
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
