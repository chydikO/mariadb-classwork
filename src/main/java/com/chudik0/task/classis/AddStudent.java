package com.chudik0.task.classis;

import java.sql.*;
import java.util.Scanner;

public class AddStudent {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost/academy";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            int choice;
            do {
                displayMenu();
                Scanner scanner = new Scanner(System.in);
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showAllStudents(conn);
                        break;
                    case 2:
                        addNewStudent(conn, scanner);
                        break;
                    case 0:
                        System.out.println("До побачення!");
                        break;
                    default:
                        System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } while (choice != 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printGroups(Connection conn) throws SQLException {
        String query = "SELECT id, name FROM groups";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Список існуючих груп:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + " - " + name);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("-".repeat(30));
        System.out.println("Виберіть дію:");
        System.out.println("1. Показати всіх студентів");
        System.out.println("2. Додати студента");
        System.out.println("0. Вийти");
        System.out.println("-".repeat(30));
    }

    private static void addNewStudent(Connection conn, Scanner scanner) throws SQLException {

        System.out.print("Введіть ім'я студента: ");
        String firstName = scanner.nextLine();

        System.out.print("Введіть прізвище студента: ");
        String lastName = scanner.nextLine();

        printGroups(conn);
        System.out.print("Введіть ідентифікатор групи студента: ");
        int groupId = scanner.nextInt();

        addStudent(conn, firstName, lastName, groupId);
    }

    private static void addStudent(Connection conn, String firstName, String lastName, int groupId) {
        String query = "INSERT INTO students (first_name, last_name, group_id) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, groupId);
            pstmt.executeUpdate();
            System.out.println("Студент успішно доданий до бази даних.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void showAllStudents(Connection conn) throws SQLException {
        String query = """ 
                            SELECT students.id, students.first_name, students.last_name, `groups`.name FROM students, groups 
                                WHERE students.group_id = `groups`.id
                       """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Список студентів:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String group = rs.getString("name");
                System.out.println(id + " - " + firstName + " " + lastName + " (Група " + group + ")");
            }
        }
    }
}
