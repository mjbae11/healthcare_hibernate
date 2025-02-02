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

    @OneToMany
            (mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany (mappedBy = "patients",
                cascade = {CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    private List<Doctor> doctors = new ArrayList<>();

}
