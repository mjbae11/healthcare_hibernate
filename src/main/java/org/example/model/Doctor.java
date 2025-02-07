package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString (exclude = {"patients", "appointments", "office"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "Doctors")
public class Doctor
{
    @Id
    @EqualsAndHashCode.Include
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
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true)
    private Office office;

    @ManyToMany (cascade = {CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable (name = "Doctor_Patient",
                joinColumns = @JoinColumn(name = "DoctorID"),
                inverseJoinColumns = @JoinColumn(name = "PatientID"))
    private Set<Patient> patients = new HashSet<>();

}