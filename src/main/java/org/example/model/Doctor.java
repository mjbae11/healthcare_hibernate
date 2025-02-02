package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


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

    @OneToMany
            (mappedBy = "doctor",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @OneToOne
            (mappedBy = "doctor",
            cascade = CascadeType.ALL)
    private Office office;

    @ManyToMany (cascade = {CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable (name = "Doctor_Patient",
                joinColumns = @JoinColumn(name = "DoctorID"),
                inverseJoinColumns = @JoinColumn(name = "PatientID"))
    private List<Patient> patients = new ArrayList<>();

}