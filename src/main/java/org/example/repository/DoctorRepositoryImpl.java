package org.example.repository;

import jakarta.transaction.Transactional;
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
            return session.get(Doctor.class, doctorId);
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
    public void deleteDoctor(int doctorId)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
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
