package org.example.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Appointments")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;

//    // many to one
//    @Column(name = "PatientId")
//    private int patientId;
//
//    // many to one
//    @Column(name = "DoctorId")
//    private int doctorId;

    @ManyToOne
    @JoinColumn(name="PatientID",
            nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="DoctorID",
            nullable = false)
    private Doctor doctor;

    // Format of string: YYYY-MM-DD
    @Column(name = "AppointmentDate")
    private String appointmentDate;

    @Column(name = "Notes")
    private String notes;



    public Appointment() {}

    public Appointment(Patient patient, Doctor doctor, String appointmentDate, String notes)
    {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.notes = notes;
    }

    // Getters and Setters
    public int getAppointmentId()
    {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId)
    {
        this.appointmentId = appointmentId;
    }


    public Patient getPatient()
    {
        return patient;
    }

    public int getPatientId()
    {
        return patient.getPatientId();
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public void setPatientId(int patientId)
    {
        this.patient.setPatientId(patientId);
    }

    public Doctor getDoctor()
    {
        return doctor;
    }

    public int getDoctorId()
    {
        return doctor.getDoctorId();
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

    public void setDoctorId(int doctorId)
    {
        this.doctor.setDoctorId(doctorId);
    }


    public String getAppointmentDate()
    {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate)
    {
        this.appointmentDate = appointmentDate;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }
}
