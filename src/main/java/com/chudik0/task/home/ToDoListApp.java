package com.chudik0.task.home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ToDoListApp {
    public static void main(String[] args) {
        final String url = "jdbc:mariadb://localhost/todolist";
        final String username = "root";
        final String password = "";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            TaskDao taskDao = new TaskDao(connection);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                int choice = getChoice(scanner);

                switch (choice) {
                    case 1:
                        System.out.print("Enter a description of the task: ");
                        String description = scanner.nextLine();
                        Task newTask = new Task(description);
                        taskDao.addTask(newTask);
                        System.out.println("Task added successfully!");
                        break;
                    case 2:
                        List<Task> tasks = taskDao.getAllTasks();
                        for (Task task : tasks) {
                            System.out.println(task.getId() + ". " + task.getDescription() + " (completed: " + (task.isStatus() ? " + " : " - ") + ")");
                        }
                        break;
                    case 3:
                        System.out.print("Enter the id of the task you want to mark as completed: ");
                        int taskId = scanner.nextInt();
                        scanner.nextLine();
                        taskDao.updateTaskStatus(taskId, true);
                        System.out.println("The task has been completed!");
                        break;
                    case 4:
                        System.out.print("Enter the id of the task, do you want to delete:");
                        int taskIdToDelete = scanner.nextInt();
                        scanner.nextLine();
                        taskDao.deleteTask(taskIdToDelete);
                        System.out.println("Task deleted successfully!");
                        break;
                    case 0:
                        System.out.println("By-By !! EXIT");
                        return;
                    default:
                        System.out.println("Wrong choice. Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getChoice(Scanner scanner) {
        displayMenu();
        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера
        return choice;
    }

    private static void displayMenu() {
        System.out.println("1. Add new task");
        System.out.println("2. View all tasks");
        System.out.println("3. Mark a task as completed");
        System.out.println("4. Delete task");
        System.out.println("0. Exit");
        System.out.println("-".repeat(30));
    }
}