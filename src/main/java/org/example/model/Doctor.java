package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Doctors")
public class Doctor
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID")
    private int doctorId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Speciality")
    private String speciality;

    @Column(name = "Email")
    private String email;

    // many to many
    // doctors patients
    // one to many
    // cascade
    // appointments
    @OneToMany
            (mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "Appointments",
//            joinColumns = @JoinColumn(name = "DoctorID"),
//            inverseJoinColumns = @JoinColumn(name = "PatientID")
//    )
//    private List<Patient> patients = new ArrayList<>();

    //Constructors
    public Doctor() {}

    public Doctor(String firstName, String lastName, String speciality, String email, List<Appointment> appointments)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.speciality = speciality;
        this.email = email;
        this.appointments = appointments;
    }


    public int getDoctorId()
    {
        return doctorId;
    }

    public void setDoctorId(int doctorId)
    {
        this.doctorId = doctorId;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public void setSpeciality(String speciality)
    {
        this.speciality = speciality;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

//    public List<Patient> getPatients()
//    {
//        return patients;
//    }
//
//    public void setPatients(List<Patient> patients)
//    {
//        this.patients = patients;
//    }

    public List<Appointment> getAppointments()
    {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments)
    {
        this.appointments = appointments;
    }
}