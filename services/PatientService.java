// services/PatientService.java
package services;

import models.Patient;
import datastructures.LinkedList;
import datastructures.BinarySearchTree;
import java.util.Comparator;

import java.util.Scanner;


public class PatientService {
    private LinkedList<Patient> patients;
    private BinarySearchTree<Patient> patientsBST;
    private static int patientIdCounter = 1;

    public PatientService() {
        patients = new LinkedList<>();
        patientsBST = new BinarySearchTree<>(Comparator.comparing(Patient::getName));
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        patientsBST.insert(patient);
    }

    public Patient getPatientById(String id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        return null;
    }

public Patient registerNewPatient(String username, String password) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter your full name: ");
    String name = scanner.nextLine();

    System.out.print("Enter your age: ");
    int age = Integer.parseInt(scanner.nextLine());

    System.out.print("Enter your disease: ");
    String disease = scanner.nextLine();

    String id = generateUniqueId();
    Patient patient = new Patient(id, username, password, name, age, disease);

    addPatient(patient);
    System.out.println("Patient registered successfully! Your ID is: " + id);
    return patient;
}

  private String generateUniqueId() {
    return "P" + (patientIdCounter++);
}


    public LinkedList<Patient> searchPatientsByName(String searchTerm) {
        LinkedList<Patient> result = new LinkedList<>();
        searchTerm = searchTerm.toLowerCase();
        
        for (Patient patient : patients) {
            if (patient.getName().toLowerCase().contains(searchTerm) || 
                patient.getName().toLowerCase().startsWith(searchTerm)) {
                result.add(patient);
            }
        }
        
        // Binary sort the results by name
        Patient[] array = new Patient[result.size()];
        int index = 0;
        for (Patient patient : result) {
            array[index++] = patient;
        }
        
        // Bubble sort (simple sorting algorithm for demonstration)
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].getName().compareTo(array[j + 1].getName()) > 0) {
                    Patient temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        
        LinkedList<Patient> sortedResult = new LinkedList<>();
        for (Patient patient : array) {
            sortedResult.add(patient);
        }
        
        return sortedResult;
    }
    
    public int getPatientCount() {
        return patients.size();
    }
    
}