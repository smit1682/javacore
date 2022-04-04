package smit.motadata.sms.interpreter;

import smit.motadata.sms.data.Student;

import smit.motadata.sms.service.StudentService;
import smit.motadata.sms.util.Methods;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Interpreter {

    final static Scanner keyboard = new Scanner(System.in);
    static Methods methods = new Methods();

    public static void start() {

        boolean isRunning = true;

        while (isRunning) {

            System.out.println("""
                    1) Enter New Students
                    2) Filter's for Students
                    3) All Student
                    4) Update Student Name
                    4) exit""");

            int choice = keyboard.nextInt(); // TODO: 29/03/22 name?

            switch (choice) {
                case 1:
                    enterStudents();
                    break;
                case 2:     //filter
                    filter();
                case 3:    // all student
                    printAll();
                    break;
                case 4:
                    updateStudent();
                case 5:      // exit
                    isRunning = false;
                    break;
                default:
                    System.out.println("Enter valid Number");
            }
        }

    }



    public static void enterStudents() {

        boolean flag = true;
        int numberOfStudent = 0;

        while (flag) {
            try {

                System.out.print("Total number of student: ");
                numberOfStudent = keyboard.nextInt();
                keyboard.nextLine();
                flag = false;

            } catch (Exception exception) {
                System.out.println("Please enter valid number");
            }
        }


        for (int i = 0; i < numberOfStudent; i++) {

            System.out.print("Enter Name: ");
            String name = keyboard.nextLine();

            System.out.print("Enter Age: ");
            int age = keyboard.nextInt();
            keyboard.nextLine();

            System.out.print("Enter Email: ");
            String email = keyboard.nextLine();


            boolean isvalid = methods.validMail(email);
            while (!isvalid) {

                System.out.print("Enter valid Email: ");
                email = keyboard.nextLine();
                isvalid = methods.validMail(email);

            }

            Student student = new Student(name, age);

            int id = student.getStudentId();

            StudentService.insertToDatabase(id, student);

        }
    }

    public static void printAll() {

        HashMap<Integer, Student> databaseCopy = StudentService.readAllFromDatabase();

        for (Map.Entry<Integer, Student> data : databaseCopy.entrySet())
            System.out.println(data.getKey() + "value: " + data.getValue());

    }


    public static void filter() {
        {
            System.out.println("Name Start with: ");
            String filter = keyboard.next();
            HashMap<Integer, Student> m = StudentService.readByRegex(filter);

            for (Map.Entry<Integer, Student> map : m.entrySet()) {
                System.out.println("Id: " + map.getKey() + "Name: " + map.getValue().getStudentName());
            }

        }


    }

    public static void updateStudent() {


        System.out.println("Enter ID to update ");
        int id = keyboard.nextInt();
        System.out.println("New name: ");
        String name = keyboard.nextLine();
        if (StudentService.updateStudentByID(id, name)) {
            System.out.println("Successful");
        }
        System.out.println("not successful");
    }


}


