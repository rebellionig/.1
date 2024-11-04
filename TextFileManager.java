package student.java.homework.weeks.week3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class TextFileManager {

    public static void main(String[] args) {
        String folder = "C:\\Users\\salke\\ddd"; // Specify your main folder here
        File dir = new File(folder);
        File[] textFiles = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (textFiles == null || textFiles.length == 0) {
            System.out.println("No text files found in the specified folder.");
            return;
        }

        System.out.println("Available text files:");
        for (int i = 0; i < textFiles.length; i++) {
            System.out.println((i + 1) + ". " + textFiles[i].getName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of the file you want to open: ");

        int choice = scanner.nextInt() - 1;

        if (choice < 0 || choice >= textFiles.length) {
            System.out.println("Invalid choice, please try again.");
            scanner.close();
            return;
        }

        File selectedFile = textFiles[choice];
        displayFileContents(selectedFile);

        scanner.nextLine(); // Consume the leftover newline
        System.out.print("Enter your message to append to the file: ");
        String userMessage = scanner.nextLine();
        appendToFile(selectedFile, userMessage);

        scanner.close();
    }

    private static void displayFileContents(File file) {
        System.out.println("File Contents:");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void appendToFile(File file, String message) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + System.lineSeparator());
            System.out.println("Message appended to " + file.getName());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}




