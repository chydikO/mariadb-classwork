package com.chudik0.task.home;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private final Connection connection;

    public TaskDao(Connection connection) {
        this.connection = connection;
    }

    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (description) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, task.getDescription());
            statement.executeUpdate();
        }
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id")
                        , resultSet.getString("description")
                        , resultSet.getBoolean("status"));
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, boolean status) throws SQLException {
        String query = "UPDATE tasks SET status = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, status);
            statement.setInt(2, taskId);
            statement.executeUpdate();
        }
    }

    public void deleteTask(int taskId) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        }
    }
}