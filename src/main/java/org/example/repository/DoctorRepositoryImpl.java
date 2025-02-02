package org.example.repository;

import org.example.model.Doctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorRepositoryImpl
{
    private SessionFactory sessionFactory;

    public DoctorRepositoryImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    // Create (CRUD) method
    public void createDoctor(Doctor doctor)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        }
    }

    // GET by doctor's id
    public Doctor getDoctorById(int doctorId)
    {
        try (Session session = sessionFactory.openSession())
        {
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor == null)
            {
                return null;
            }
            return doctor;
        }
    }

    // UPDATE
    public void updateDoctor(Doctor doctor)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.update(doctor);
            transaction.commit();
        }
    }

    // DELETE
    public void deleteDoctor(int doctorid)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorid);
            if (doctor != null)
            {
                session.delete(doctor);
            }
            transaction.commit();
        }
    }

    // GET all the doctors
    public List<Doctor> getAllDoctors()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }
}
