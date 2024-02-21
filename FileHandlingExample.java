package DAY_18;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Define the Student class with Serializable interface to enable object serialization
class Student implements Serializable {
    private int id;
    private String name;
    private int age;

    // Constructor to initialize student attributes
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Override toString method for better output representation
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

// Main class for file handling operations
public class FileHandlingExample {

    // Method to serialize a list of students and save it to a file
    private static void serializeStudents(List<Student> students, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(students); // Write the list of students to the file
            System.out.println("Serialization successful.");
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage()); // Handle IO exceptions
        }
    }
    // Method to deserialize a list of students from a file
    private static List<Student> deserializeStudents(String fileName)
    {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            students = (List<Student>) ois.readObject(); // Read the list of students from the file
            System.out.println("Deserialization successful.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage()); // Handle IO and class not found exceptions
        }
        return students;
    }
    public static void main(String[] args)
    {
        Scanner sc =new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        System.out.println("Enter how many students you want to enter");
        int n=sc.nextInt();
        for(int i=1;i<=n;i++)
        {
            students.add(addStudent());
        }
        System.out.println("Objects before serialization :");
        System.out.println(students);
        String fileName = "students.ser";

        // Serialize the list of students
        serializeStudents(students, fileName);

        // Deserialize the list of students
        List<Student> deserializedStudents = deserializeStudents(fileName);

        // Display the deserialized students
        for (Student student : deserializedStudents) {
            System.out.println(student);
        }
    }
    public static Student addStudent()
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the id of the student");
        int id=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the name of the student");
        String name=sc.nextLine();
        System.out.println("Enter the age of the student");
        int age = sc.nextInt();
        Student stud =  new Student(id,name,age);
        return stud;
    }
}
