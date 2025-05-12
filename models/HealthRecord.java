// models/HealthRecord.java
package models;

public class HealthRecord {
    private String date;
    private String symptoms;
    private String diagnosis;
    private String medications;
    private String notes;

    public HealthRecord(String date, String symptoms, String diagnosis, String medications, String notes) {
        this.date = date;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medications = medications;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedications() {
        return medications;
    }

    public String getNotes() {
        return notes;
    }
}