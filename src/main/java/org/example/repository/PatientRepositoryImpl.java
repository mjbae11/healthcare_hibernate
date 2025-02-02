package org.example.repository;

import org.example.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class PatientRepositoryImpl
{

    private SessionFactory sessionFactory;

    public PatientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createPatient(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        }
    }

    public Patient getPatientById(int patientId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Patient.class, patientId);
        }
    }

    public void updatePatient(Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        }
    }

    public void deletePatient(int patientId)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class, patientId);
            if (patient != null)
            {
                session.delete(patient);
            }
            transaction.commit();
        }
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//
//            // Clear appointments first
//            String appointmentHql = "delete from Appointment where patient.patientId = :pid";
//            session.createQuery(appointmentHql)
//                    .setParameter("pid", patientId)
//                    .executeUpdate();
//
//            // Then delete patient
//            String patientHql = "delete from Patient where patientId = :pid";
//            session.createQuery(patientHql)
//                    .setParameter("pid", patientId)
//                    .executeUpdate();
//
//            transaction.commit();
//        }
    }

    public List<Patient> getAllPatients() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Patient", Patient.class).list();
        }
    }
}
