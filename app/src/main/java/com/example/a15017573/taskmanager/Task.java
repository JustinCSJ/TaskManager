package com.example.a15017573.taskmanager;

import java.io.Serializable;

/**
 * Created by 15017573 on 26/5/2017.
 */

public class Task implements Serializable {
    private int id;
    private String taskName;
    private String taskDesc;

    public Task(int id, String taskName, String taskDesc) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;

    }

    public String getTaskName() {
        return taskName;
    }

    public int getId() {
        return id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    @Override
    public String toString() {
        return "ID:" + id + taskName + ", " + taskDesc;
    }
}
