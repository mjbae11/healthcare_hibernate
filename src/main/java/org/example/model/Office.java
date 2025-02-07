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
@Table(name = "Offices")
public class Office
{
    @Id
    @EqualsAndHashCode.Include
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
