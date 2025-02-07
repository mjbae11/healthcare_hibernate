package org.example.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "Appointments")
public class Appointment
{
    @Id
    @EqualsAndHashCode.Include
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
