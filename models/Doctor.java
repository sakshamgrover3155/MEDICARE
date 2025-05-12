// models/Doctor.java
package models;

import datastructures.LinkedList;

public class Doctor extends User {
    private String specialty;
    private int yearsExperience;
    private LinkedList<Patient> appointmentQueue;

    public Doctor(String id, String username, String password) {
        super(id, username, password);
        this.specialty = "";
        this.yearsExperience = 0;
        this.appointmentQueue = new LinkedList<>();
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public LinkedList<Patient> getAppointmentQueue() {
        return appointmentQueue;
    }

    public void addAppointment(Patient patient) {
        appointmentQueue.add(patient);
    }

    public boolean removeAppointment(Patient patient) {
        return appointmentQueue.remove(patient);
    }
}
