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
@Table(name = "Offices")
public class Office
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "OfficeID")
    private int officeId;

    @Column (name = "Location")
    private String location;

    @Column (name = "Phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "DoctorID")
    private Doctor doctor;
}
