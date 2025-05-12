// services/DoctorService.java
package services;

import models.Doctor;
import models.Patient;
import datastructures.LinkedList;
import datastructures.BinarySearchTree;
import java.util.Comparator;

public class DoctorService {
    private LinkedList<Doctor> doctors;
    private BinarySearchTree<Doctor> doctorsBST;

    public DoctorService() {
        doctors = new LinkedList<>();
        doctorsBST = new BinarySearchTree<>((d1, d2) -> {
            String name1 = (d1.getName() == null || d1.getName().isEmpty()) ? d1.getId() : d1.getName();
            String name2 = (d2.getName() == null || d2.getName().isEmpty()) ? d2.getId() : d2.getName();
            return name1.compareTo(name2);
        });
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        doctorsBST.insert(doctor);
        System.out.println("Doctor added: " + doctor.getName());
    }

    public Doctor getDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public LinkedList<Patient> getDoctorAppointments(String doctorId) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor != null) {
            return doctor.getAppointmentQueue();
        }
        return null;
    }

    public boolean addPatientAppointment(String doctorId, Patient patient) {
        Doctor doctor = getDoctorById(doctorId);
        if (doctor != null) {
            doctor.addAppointment(patient);
            return true;
        }
        return false;
    }

    public LinkedList<Doctor> getAllDoctorsSorted() {
        LinkedList<Doctor> sortedDoctors = new LinkedList<>();
        doctorsBST.inOrderTraversal(sortedDoctors::add);
        return sortedDoctors;
    }

    public LinkedList<Doctor> searchDoctorsByName(String searchTerm) {
        LinkedList<Doctor> result = new LinkedList<>();
        searchTerm = searchTerm.toLowerCase();

        for (Doctor doctor : doctors) {
            if (doctor.getName().toLowerCase().contains(searchTerm) || 
                doctor.getName().toLowerCase().startsWith(searchTerm)) {
                result.add(doctor);
            }
        }

        // Sort the results alphabetically by name
        Doctor[] array = new Doctor[result.size()];
        int index = 0;
        for (Doctor doctor : result) {
            array[index++] = doctor;
        }

        quickSort(array, 0, array.length - 1);

        LinkedList<Doctor> sortedResult = new LinkedList<>();
        for (Doctor doctor : array) {
            sortedResult.add(doctor);
        }

        return sortedResult;
    }

    public LinkedList<Doctor> searchDoctorsBySpecialty(String specialty) {
        LinkedList<Doctor> result = new LinkedList<>();
        specialty = specialty.toLowerCase();

        for (Doctor doctor : doctors) {
            if (doctor.getSpecialty().toLowerCase().contains(specialty)) {
                result.add(doctor);
            }
        }

        // Sort the results alphabetically by name
        Doctor[] array = new Doctor[result.size()];
        int index = 0;
        for (Doctor doctor : result) {
            array[index++] = doctor;
        }

        quickSort(array, 0, array.length - 1);

        LinkedList<Doctor> sortedResult = new LinkedList<>();
        for (Doctor doctor : array) {
            sortedResult.add(doctor);
        }

        return sortedResult;
    }

    private void quickSort(Doctor[] arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);

            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    private int partition(Doctor[] arr, int low, int high) {
        Doctor pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j].getName().compareTo(pivot.getName()) <= 0) {
                i++;

                Doctor temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Doctor temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public int getDoctorCount() {
        return doctors.size();
    }
}