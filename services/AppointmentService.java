// services/AppointmentService.java
package services;

import models.Appointment;
import datastructures.LinkedList;
import datastructures.Queue;

public class AppointmentService {
    private LinkedList<Appointment> appointments;
    private Queue<Appointment> pendingAppointments;

    public AppointmentService() {
        appointments = new LinkedList<>();
        pendingAppointments = new Queue<>();
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
        if (appointment.getStatus().equals("Pending")) {
            pendingAppointments.enqueue(appointment);
        }
    }

    public LinkedList<Appointment> getPatientAppointments(String patientId) {
        LinkedList<Appointment> result = new LinkedList<>();
        
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                result.add(appointment);
            }
        }
        
        return result;
    }

    public LinkedList<Appointment> getDoctorAppointments(String doctorId) {
        LinkedList<Appointment> result = new LinkedList<>();
        
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId)) {
                result.add(appointment);
            }
        }
        
        return result;
    }

    public LinkedList<Appointment> getDoctorPendingAppointments(String doctorId) {
        LinkedList<Appointment> result = new LinkedList<>();
        
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId) && appointment.getStatus().equals("Pending")) {
                result.add(appointment);
            }
        }
        
        return result;
    }
    
    public int getAppointmentCount() {
        return appointments.size();
    }
}