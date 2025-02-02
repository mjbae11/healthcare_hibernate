package org.example.service;


import org.example.repository.AppointmentRepositoryImpl;
import org.example.repository.DoctorRepositoryImpl;
import org.example.repository.PatientRepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SetUpMain
{
    private final SessionFactory sessionFactory;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    // configure and setup
    public SetUpMain()
    {
        this.sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        this.patientService = new PatientService(patientRepository);
        this.doctorService = new DoctorService(doctorRepository);
        this.appointmentService = new AppointmentService(appointmentRepository);
    }


    public SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }

    public PatientService getPatientService()
    {
        return patientService;
    }

    public DoctorService getDoctorService()
    {
        return doctorService;
    }

    public AppointmentService getAppointmentService()
    {
        return appointmentService;
    }
}
