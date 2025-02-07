package org.example.repository;

import org.example.model.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class OfficeRepositoryImpl
{
    private final SessionFactory sessionFactory;

    // Constructor
    public OfficeRepositoryImpl(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    // Create a new office
    public void createOffice(Office office)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.save(office);
            transaction.commit();
        }
    }

    // Finds an Office Object by its id
    public Office getOfficeById(int officeId)
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.get(Office.class, officeId);
        }
    }

    // update existing office details
    public void updateOffice(Office office)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            session.update(office);
            transaction.commit();
        }
    }
    // Delete an office by Id
    public void deleteOffice(int officeId)
    {
        try (Session session = sessionFactory.openSession())
        {
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, officeId);
            if (office != null)
            {
                office.setDoctor(null);
                session.update(office);
                session.delete(office);
                transaction.commit();
            }

        }
    }

    // retrieve all the offices
    public List<Office> getAllOffices()
    {
        try (Session session = sessionFactory.openSession())
        {
            return session.createQuery("from Office", Office.class).list();
        }
    }
}
