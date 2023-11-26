//package services;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//public class CreatingDB {
//    public static void main(String[] args) {
//        Statement stmt = null;
//        Scanner scanner = new Scanner(System.in);
//        String input = scanner.nextLine();
//        if (input.equals("y") || input.equals("Y")) {
//            try {
//                JDBC.connect();
//                stmt = JDBC.connection.createStatement();
//                // Drop Tables
//                String drop1 = "DROP TABLE IF EXISTS admins ";
//                stmt.executeUpdate(drop1);
//                String drop2 = "DROP TABLE IF EXISTS tickets ";
//                stmt.executeUpdate(drop2);
//                String drop3 = "DROP TABLE IF EXISTS records";
//                stmt.executeUpdate(drop3);
//                String drop4 = "DROP TABLE IF EXISTS doctors";
//                stmt.executeUpdate(drop4);
//                String drop5 = "DROP TABLE IF EXISTS patients ";
//                stmt.executeUpdate(drop5);
//                String drop6 = "DROP TABLE IF EXISTS users";
//                stmt.executeUpdate(drop6);
//                System.out.println("Data deleted");
//                // Create Tables
//                String usersTable = "CREATE TABLE users " +
//                        "(id INTEGER NOT NULL AUTO_INCREMENT, " +
//                        " login VARCHAR(20) NOT NULL, " +
//                        " password INTEGER NOT NULL, " +
//                        " PRIMARY KEY (id));";
//                stmt.executeUpdate(usersTable);
//                String adminsTable = "CREATE TABLE admins" +
//                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
//                        " user_id INTEGER NOT NULL, " +
//                        " rightsToRedactPatients BOOLEAN DEFAULT FALSE, " +
//                        " rightsToDeletePatients BOOLEAN DEFAULT FALSE, " +
//                        " rightsToRedactDoctors BOOLEAN DEFAULT FALSE, " +
//                        " rightsToDeleteDoctors BOOLEAN DEFAULT FALSE, " +
//                        " rightsToRedactAdmins BOOLEAN DEFAULT FALSE, " +
//                        " rightsToDeleteAdmins BOOLEAN DEFAULT FALSE, " +
//                        " FOREIGN KEY (user_id) REFERENCES users(id))";
//                stmt.executeUpdate(adminsTable);
//                String doctorsTable = "CREATE TABLE doctors " +
//                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
//                        "user_id INTEGER NOT NULL," +
//                        "firstName VARCHAR(20) NOT NULL, " +
//                        "lastName VARCHAR(20) NOT NULL, " +
//                        "middleName VARCHAR(20), " +
//                        "speciality VARCHAR(30), " +
//                        "shift INTEGER, " +
//                        "email VARCHAR(40), " +
//                        " FOREIGN KEY (user_id) REFERENCES users(id))";
//                stmt.executeUpdate(doctorsTable);
//                String patientsTable = "CREATE TABLE patients " +
//                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
//                        "user_id INTEGER NOT NULL," +
//                        "firstName VARCHAR(20) NOT NULL, " +
//                        "lastName VARCHAR(20) NOT NULL, " +
//                        "middleName VARCHAR(20), " +
//                        "address VARCHAR(30), " +
//                        "sex VARCHAR(1), " +
//                        "email VARCHAR(40), " +
//                        "phoneNumber VARCHAR(20), " +
//                        " FOREIGN KEY (user_id) REFERENCES users(id))";
//                stmt.executeUpdate(patientsTable);
//                String recordsTable = "CREATE TABLE records " +
//                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
//                        "patient_id INTEGER NOT NULL, " +
//                        "doctor_id INTEGER NOT NULL, " +
//                        "record TEXT NOT NULL, " +
//                        "dateOfRecord DATETIME NOT NULL, " +
//                        "FOREIGN KEY (patient_id) REFERENCES patients(id), " +
//                        "FOREIGN KEY (doctor_id) REFERENCES doctors(id))";
//                stmt.executeUpdate(recordsTable);
//                String ticketsTable = "CREATE TABLE tickets " +
//                        "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
//                        "doctor_id INTEGER NOT NULL, " +
//                        "patient_id INTEGER, " +
//                        "dateOfTaking DATETIME, " +
//                        "TicketDate DATETIME NOT NULL, " +
//                        "FOREIGN KEY (patient_id) REFERENCES patients(id), " +
//                        "FOREIGN KEY (doctor_id) REFERENCES doctors(id))";
//                stmt.executeUpdate(ticketsTable);
//                String userCreation = "INSERT INTO users ( id, login, password)" +
//                        "VALUES (1, 'admin', " + "admin".hashCode() + ")";
//                stmt.executeUpdate(userCreation);
//                String adminCreation = "INSERT INTO admins (user_id, rightsToRedactPatients," +
//                        "rightsToDeletePatients, rightsToRedactDoctors, rightsToDeleteDoctors," +
//                        "rightsToRedactAdmins, rightsToDeleteAdmins)" +
//                        "VALUES (1, true, true, true, true, true, true)";
//                stmt.executeUpdate(adminCreation);
//            } catch (SQLException se) {
//                // Handle errors for JDBC
//                se.printStackTrace();
//            } finally {
//                // Finally block, used to close resources
//                if (stmt != null) {
//                    JDBC.close();
//                }
//            }
//        }
//    }
//}
