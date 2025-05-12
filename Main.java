// Main.java
import java.util.Scanner;
import models.*;
import services.*;
import datastructures.*;
import utils.ConsoleColors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static AuthService authService = new AuthService();
    private static DoctorService doctorService = new DoctorService();
    private static PatientService patientService = new PatientService();
    private static AppointmentService appointmentService = new AppointmentService();

    public static void main(String[] args) {
        initializeData();
        displayWelcomeScreen();
        mainMenu();
    }

    private static void initializeData() {
        //no 
    }

    private static void displayWelcomeScreen() {
        System.out.println(ConsoleColors.BLUE_BOLD + "====================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD + "       HEALTH CONNECT SYSTEM        " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD + "====================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "Your health management solution" + ConsoleColors.RESET);
        System.out.println();
    }

    private static void mainMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println(ConsoleColors.YELLOW + "\nMAIN MENU" + ConsoleColors.RESET);
            System.out.println("1. Login");
            System.out.println("2. Register New User");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    exit = true;
                    System.out.println(ConsoleColors.GREEN + "Thank you for using Health Connect System. Goodbye!" + ConsoleColors.RESET);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private static void login() {
        System.out.println(ConsoleColors.BLUE + "\n--- LOGIN ---" + ConsoleColors.RESET);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        User user = authService.authenticate(username, password);
        
        if (user != null) {
            System.out.println(ConsoleColors.GREEN + "Login successful!" + ConsoleColors.RESET);
            if (user instanceof Doctor) {
                doctorMenu((Doctor) user);
            } else if (user instanceof Patient) {
                patientMenu((Patient) user);
            }
        } else {
            System.out.println(ConsoleColors.RED + "Invalid username or password. Please try again." + ConsoleColors.RESET);
        }
    }

    private static void register() {
        System.out.println(ConsoleColors.BLUE + "\n--- REGISTER NEW USER ---" + ConsoleColors.RESET);
        System.out.println("Select user type:");
        System.out.println("1. Doctor");
        System.out.println("2. Patient");
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        String id;
        User newUser;
        
        switch (choice) {
            case 1:
                id = "D" + String.format("%03d", doctorService.getDoctorCount() + 1);
                newUser = new Doctor(id, username, password);
                System.out.println(ConsoleColors.GREEN + "Doctor registered successfully with ID: " + id + ConsoleColors.RESET);
                doctorService.addDoctor((Doctor) newUser);
                break;
            case 2:
                id = "P" + String.format("%03d", patientService.getPatientCount() + 1);
                newUser = new Patient(id, username, password, id, choice, id);
                System.out.println(ConsoleColors.GREEN + "Patient registered successfully with ID: " + id + ConsoleColors.RESET);
                patientService.addPatient((Patient) newUser);
                break;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice. Registration cancelled." + ConsoleColors.RESET);
                return;
        }
        
        authService.register(newUser);
        System.out.println("Please login with your new credentials.");
    }

    private static void doctorMenu(Doctor doctor) {
        boolean logout = false;
        while (!logout) {
            System.out.println(ConsoleColors.YELLOW + "\nDOCTOR MENU - Welcome Dr. " + doctor.getName() + " (ID: " + doctor.getId() + ")" + ConsoleColors.RESET);
            System.out.println("1. View My Profile");
            System.out.println("2. Update My Profile");
            System.out.println("3. View Pending Appointments");
            System.out.println("4. Manage Appointments");
            System.out.println("5. View Patient Information");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewDoctorProfile(doctor);
                    break;
                case 2:
                    updateDoctorProfile(doctor);
                    break;
                case 3:
                    viewPendingAppointments(doctor);
                    break;
                case 4:
                    manageAppointments(doctor);
                    break;
                case 5:
                    viewPatientInformation();
                    break;
                case 6:
                    logout = true;
                    System.out.println(ConsoleColors.GREEN + "Logged out successfully." + ConsoleColors.RESET);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private static void patientMenu(Patient patient) {
        boolean logout = false;
        while (!logout) {
            System.out.println(ConsoleColors.YELLOW + "\nPATIENT MENU - Welcome " + patient.getName() + " (ID: " + patient.getId() + ")" + ConsoleColors.RESET);
            System.out.println("1. View My Profile");
            System.out.println("2. Update My Profile");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. View My Appointments");
            System.out.println("6. Add Health Record");
            System.out.println("7. View My Health Records");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    viewPatientProfile(patient);
                    break;
                case 2:
                    updatePatientProfile(patient);
                    break;
                case 3:
                    viewDoctors();
                    break;
                case 4:
                    bookAppointment(patient);
                    break;
                case 5:
                    viewPatientAppointments(patient);
                    break;
                case 6:
                    addHealthRecord(patient);
                    break;
                case 7:
                    viewHealthRecords(patient);
                    break;
                case 8:
                    logout = true;
                    System.out.println(ConsoleColors.GREEN + "Logged out successfully." + ConsoleColors.RESET);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private static void viewDoctorProfile(Doctor doctor) {
        System.out.println(ConsoleColors.BLUE + "\n--- DOCTOR PROFILE ---" + ConsoleColors.RESET);
        System.out.println("ID: " + doctor.getId());
        System.out.println("Name: " + doctor.getName());
        System.out.println("Specialty: " + doctor.getSpecialty());
        System.out.println("Email: " + doctor.getEmail());
        System.out.println("Phone: " + doctor.getPhone());
        System.out.println("Years of Experience: " + doctor.getYearsExperience());
        System.out.println(ConsoleColors.BLUE + "---------------------" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void updateDoctorProfile(Doctor doctor) {
        System.out.println(ConsoleColors.BLUE + "\n--- UPDATE DOCTOR PROFILE ---" + ConsoleColors.RESET);
        System.out.print("Name (current: " + doctor.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) doctor.setName(name);
        
        System.out.print("Specialty (current: " + doctor.getSpecialty() + "): ");
        String specialty = scanner.nextLine();
        if (!specialty.isEmpty()) doctor.setSpecialty(specialty);
        
        System.out.print("Email (current: " + doctor.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) doctor.setEmail(email);
        
        System.out.print("Phone (current: " + doctor.getPhone() + "): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) doctor.setPhone(phone);
        
        System.out.print("Years of Experience (current: " + doctor.getYearsExperience() + "): ");
        String yearsStr = scanner.nextLine();
        if (!yearsStr.isEmpty()) {
            try {
                int years = Integer.parseInt(yearsStr);
                doctor.setYearsExperience(years);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid number format. Years of experience not updated." + ConsoleColors.RESET);
            }
        }
        
        System.out.println(ConsoleColors.GREEN + "Profile updated successfully!" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void viewPendingAppointments(Doctor doctor) {
        System.out.println(ConsoleColors.BLUE + "\n--- PENDING APPOINTMENTS ---" + ConsoleColors.RESET);
        
        LinkedList<Appointment> pendingAppointments = appointmentService.getDoctorPendingAppointments(doctor.getId());
        
        if (pendingAppointments.isEmpty()) {
            System.out.println("No pending appointments.");
        } else {
            int index = 1;
            for (Appointment appointment : pendingAppointments) {
                Patient patient = patientService.getPatientById(appointment.getPatientId());
                System.out.println(index + ". Date: " + appointment.getDate() + " | Time: " + appointment.getTime() +
                                 " | Patient: " + (patient != null ? patient.getName() : "Unknown") +
                                 " | Reason: " + appointment.getReason() +
                                 " | Status: " + appointment.getStatus());
                index++;
            }
        }
        
        pressEnterToContinue();
    }

    private static void manageAppointments(Doctor doctor) {
        System.out.println(ConsoleColors.BLUE + "\n--- MANAGE APPOINTMENTS ---" + ConsoleColors.RESET);
        
        LinkedList<Appointment> pendingAppointments = appointmentService.getDoctorPendingAppointments(doctor.getId());
        
        if (pendingAppointments.isEmpty()) {
            System.out.println("No pending appointments to manage.");
            pressEnterToContinue();
            return;
        }
        
        int index = 1;
        for (Appointment appointment : pendingAppointments) {
            Patient patient = patientService.getPatientById(appointment.getPatientId());
            System.out.println(index + ". Date: " + appointment.getDate() + " | Time: " + appointment.getTime() +
                             " | Patient: " + (patient != null ? patient.getName() : "Unknown") +
                             " | Reason: " + appointment.getReason());
            index++;
        }
        
        System.out.print("Enter appointment number to manage (0 to cancel): ");
        int appointmentIndex = getIntInput() - 1;
        
        if (appointmentIndex >= 0 && appointmentIndex < pendingAppointments.size()) {
            Appointment selectedAppointment = pendingAppointments.get(appointmentIndex);
            
            System.out.println("\nSelect action:");
            System.out.println("1. Approve appointment");
            System.out.println("2. Reject appointment");
            System.out.println("3. Suggest new time");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    selectedAppointment.setStatus("Approved");
                    System.out.println(ConsoleColors.GREEN + "Appointment approved successfully!" + ConsoleColors.RESET);
                    break;
                case 2:
                    selectedAppointment.setStatus("Rejected");
                    System.out.println(ConsoleColors.RED + "Appointment rejected." + ConsoleColors.RESET);
                    break;
                case 3:
                    System.out.print("Suggest new date (YYYY-MM-DD): ");
                    String newDate = scanner.nextLine();
                    System.out.print("Suggest new time (HH:MM): ");
                    String newTime = scanner.nextLine();
                    
                    selectedAppointment.setStatus("Time Changed");
                    selectedAppointment.setDate(newDate);
                    selectedAppointment.setTime(newTime);
                    selectedAppointment.setDoctorNotes("Doctor suggested new time: " + newDate + " at " + newTime);
                    
                    System.out.println(ConsoleColors.GREEN + "New time suggested successfully!" + ConsoleColors.RESET);
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. No changes made." + ConsoleColors.RESET);
            }
        } else if (appointmentIndex != -1) {
            System.out.println(ConsoleColors.RED + "Invalid appointment number." + ConsoleColors.RESET);
        }
        
        pressEnterToContinue();
    }

    private static void viewPatientInformation() {
        System.out.println(ConsoleColors.BLUE + "\n--- PATIENT SEARCH ---" + ConsoleColors.RESET);
        System.out.print("Enter patient ID or name to search: ");
        String searchTerm = scanner.nextLine();
        
        Patient patient = null;
        
        // First try by ID
        patient = patientService.getPatientById(searchTerm);
        
        // If not found, try by name
        if (patient == null) {
            LinkedList<Patient> patients = patientService.searchPatientsByName(searchTerm);
            
            if (patients.isEmpty()) {
                System.out.println(ConsoleColors.RED + "No patients found matching the search." + ConsoleColors.RESET);
                pressEnterToContinue();
                return;
            } else if (patients.size() == 1) {
                patient = patients.getFirst();
            } else {
                System.out.println("\nMultiple patients found:");
                int index = 1;
                for (Patient p : patients) {
                    System.out.println(index + ". " + p.getName() + " (ID: " + p.getId() + ")");
                    index++;
                }
                
                System.out.print("Select patient number: ");
                int patientIndex = getIntInput() - 1;
                
                if (patientIndex >= 0 && patientIndex < patients.size()) {
                    patient = patients.get(patientIndex);
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid selection." + ConsoleColors.RESET);
                    pressEnterToContinue();
                    return;
                }
            }
        }
        
        // Display patient information
        System.out.println(ConsoleColors.BLUE + "\n--- PATIENT INFORMATION ---" + ConsoleColors.RESET);
        System.out.println("ID: " + patient.getId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Phone: " + patient.getPhone());
        
        // Display health records
        System.out.println(ConsoleColors.BLUE + "\n--- HEALTH RECORDS ---" + ConsoleColors.RESET);
        LinkedList<HealthRecord> records = patient.getHealthRecords();
        
        if (records.isEmpty()) {
            System.out.println("No health records available.");
        } else {
            for (HealthRecord record : records) {
                System.out.println("Date: " + record.getDate());
                System.out.println("Symptoms: " + record.getSymptoms());
                System.out.println("Diagnosis: " + record.getDiagnosis());
                System.out.println("Medications: " + record.getMedications());
                System.out.println("Notes: " + record.getNotes());
                System.out.println("--------------------------");
            }
        }
        
        pressEnterToContinue();
    }

    private static void viewPatientProfile(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- PATIENT PROFILE ---" + ConsoleColors.RESET);
        System.out.println("ID: " + patient.getId());
        System.out.println("Name: " + patient.getName());
        System.out.println("Age: " + patient.getAge());
        System.out.println("Blood Type: " + patient.getBloodType());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Phone: " + patient.getPhone());
        System.out.println(ConsoleColors.BLUE + "---------------------" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void updatePatientProfile(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- UPDATE PATIENT PROFILE ---" + ConsoleColors.RESET);
        System.out.print("Name (current: " + patient.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) patient.setName(name);
        
        System.out.print("Age (current: " + patient.getAge() + "): ");
        String ageStr = scanner.nextLine();
        if (!ageStr.isEmpty()) {
            try {
                int age = Integer.parseInt(ageStr);
                patient.setAge(age);
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid number format. Age not updated." + ConsoleColors.RESET);
            }
        }
        
        System.out.print("Blood Type (current: " + patient.getBloodType() + "): ");
        String bloodType = scanner.nextLine();
        if (!bloodType.isEmpty()) patient.setBloodType(bloodType);
        
        System.out.print("Email (current: " + patient.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) patient.setEmail(email);
        
        System.out.print("Phone (current: " + patient.getPhone() + "): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) patient.setPhone(phone);
        
        System.out.println(ConsoleColors.GREEN + "Profile updated successfully!" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void viewDoctors() {
        System.out.println(ConsoleColors.BLUE + "\n--- DOCTOR DIRECTORY ---" + ConsoleColors.RESET);
        
        System.out.println("Search by:");
        System.out.println("1. View all doctors (alphabetically)");
        System.out.println("2. Search by specialty");
        System.out.println("3. Search by name");
        System.out.print("Enter your choice: ");
        
        int choice = getIntInput();
        LinkedList<Doctor> doctors = null;
        
        switch (choice) {
            case 1:
                doctors = doctorService.getAllDoctorsSorted();
                break;
            case 2:
                System.out.print("Enter specialty: ");
                String specialty = scanner.nextLine();
                doctors = doctorService.searchDoctorsBySpecialty(specialty);
                break;
            case 3:
                System.out.print("Enter doctor name or starting letter: ");
                String searchTerm = scanner.nextLine();
                doctors = doctorService.searchDoctorsByName(searchTerm);
                break;
            default:
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                return;
        }
        
        if (doctors.isEmpty()) {
            System.out.println("No doctors found matching your criteria.");
        } else {
            System.out.println("\nDoctors:");
            for (Doctor doctor : doctors) {
                System.out.println("ID: " + doctor.getId() + " | Name: " + doctor.getName() + " | Specialty: " + doctor.getSpecialty());
            }
        }
        
        pressEnterToContinue();
    }

    private static void bookAppointment(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- BOOK APPOINTMENT ---" + ConsoleColors.RESET);
        
        // First select doctor
        System.out.println("Select a doctor:");
        LinkedList<Doctor> doctors = doctorService.getAllDoctorsSorted();
        
        if (doctors.isEmpty()) {
            System.out.println("No doctors available in the system.");
            pressEnterToContinue();
            return;
        }
        
        int index = 1;
        for (Doctor doctor : doctors) {
            System.out.println(index + ". " + doctor.getName() + " - " + doctor.getSpecialty());
            index++;
        }
        
        System.out.print("Enter doctor number: ");
        int doctorIndex = getIntInput() - 1;
        
        if (doctorIndex < 0 || doctorIndex >= doctors.size()) {
            System.out.println(ConsoleColors.RED + "Invalid selection." + ConsoleColors.RESET);
            pressEnterToContinue();
            return;
        }
        
        Doctor selectedDoctor = doctors.get(doctorIndex);
        
        // Get appointment details
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter appointment time (HH:MM): ");
        String time = scanner.nextLine();
        
        System.out.print("Enter reason for appointment: ");
        String reason = scanner.nextLine();
        
        // Create and save appointment
        Appointment appointment = new Appointment(
            "A" + String.format("%03d", appointmentService.getAppointmentCount() + 1),
            patient.getId(),
            selectedDoctor.getId(),
            date,
            time,
            reason,
            "Pending"
        );
        
        appointmentService.addAppointment(appointment);
        
        System.out.println(ConsoleColors.GREEN + "Appointment booked successfully! Status: Pending approval" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void viewPatientAppointments(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- MY APPOINTMENTS ---" + ConsoleColors.RESET);
        
        LinkedList<Appointment> appointments = appointmentService.getPatientAppointments(patient.getId());
        
        if (appointments.isEmpty()) {
            System.out.println("You have no appointments.");
        } else {
            for (Appointment appointment : appointments) {
                Doctor doctor = doctorService.getDoctorById(appointment.getDoctorId());
                System.out.println("ID: " + appointment.getId() +
                                 " | Doctor: " + (doctor != null ? doctor.getName() : "Unknown") +
                                 " | Date: " + appointment.getDate() +
                                 " | Time: " + appointment.getTime() +
                                 " | Status: " + appointment.getStatus());
                
                if (appointment.getDoctorNotes() != null && !appointment.getDoctorNotes().isEmpty()) {
                    System.out.println("Doctor Notes: " + appointment.getDoctorNotes());
                }
                System.out.println("--------------------------");
            }
        }
        
        pressEnterToContinue();
    }

    private static void addHealthRecord(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- ADD HEALTH RECORD ---" + ConsoleColors.RESET);
        
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        
        System.out.print("Enter symptoms: ");
        String symptoms = scanner.nextLine();
        
        System.out.print("Enter diagnosis (if any): ");
        String diagnosis = scanner.nextLine();
        
        System.out.print("Enter medications (if any): ");
        String medications = scanner.nextLine();
        
        System.out.print("Enter additional notes: ");
        String notes = scanner.nextLine();
        
        HealthRecord record = new HealthRecord(date, symptoms, diagnosis, medications, notes);
        patient.addHealthRecord(record);
        
        System.out.println(ConsoleColors.GREEN + "Health record added successfully!" + ConsoleColors.RESET);
        pressEnterToContinue();
    }

    private static void viewHealthRecords(Patient patient) {
        System.out.println(ConsoleColors.BLUE + "\n--- MY HEALTH RECORDS ---" + ConsoleColors.RESET);
        
        LinkedList<HealthRecord> records = patient.getHealthRecords();
        
        if (records.isEmpty()) {
            System.out.println("You have no health records.");
        } else {
            for (HealthRecord record : records) {
                System.out.println("Date: " + record.getDate());
                System.out.println("Symptoms: " + record.getSymptoms());
                System.out.println("Diagnosis: " + record.getDiagnosis());
                System.out.println("Medications: " + record.getMedications());
                System.out.println("Notes: " + record.getNotes());
                System.out.println("--------------------------");
            }
        }
        
        pressEnterToContinue();
    }

    private static int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            return value;
        } catch (NumberFormatException e) {
            return -1; // Return invalid value
        }
    }

    private static void pressEnterToContinue() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }
}