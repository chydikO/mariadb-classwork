package com.chudik0.task.home;

public class Task {
    private static int lastId = 0;
    private final int id;
    private final String description;
    private boolean status;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = false;
    }

    public Task(int id, String description, boolean status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
