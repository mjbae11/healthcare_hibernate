package org.example.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Patients")
public class Patient
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PatientID")
    private int patientId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "DateOfBirth")
    private String dateOfBirth;

    @Column(name = "Email")
    private String email;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    // 1 to many
    // set<appointment>
    // patient
    @OneToMany
            (mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "Appointments",
//            joinColumns = @JoinColumn(name = "PatientID"),
//            inverseJoinColumns = @JoinColumn(name = "DoctorID")
//    )
//    private List<Doctor> doctors = new ArrayList<>();


    /*
    Don't need a many to many relationship?
     */
    // many to many
    // Set<Doctor>
        // mappedby patients
    //    @ManyToMany
    //    private Set<Doctor> doctors;

    // Constructors
    public Patient() {}

    public Patient(String firstName, String lastName, String dateOfBirth, String email, String phoneNumber, List<Appointment> appointments)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.appointments = appointments;
//        this.doctors = doctors;
    }



    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Appointment> getAppointments()
    {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments)
    {
        this.appointments = appointments;
    }

//    public List<Doctor> getDoctors()
//    {
//        return doctors;
//    }
//
//    public void setDoctors(List<Doctor> doctors)
//    {
//        this.doctors = doctors;
//    }


    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
