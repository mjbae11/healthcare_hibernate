package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "Appointments")
public class Appointment
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID")
    private int appointmentId;


    @ManyToOne
    @JoinColumn(name = "PatientID",
            nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "DoctorID",
            nullable = false)
    private Doctor doctor;

    // Format of string: YYYY-MM-DD
    @Column(name = "AppointmentDate")
    private String appointmentDate;

    @Column(name = "Notes")
    private String notes;

}
