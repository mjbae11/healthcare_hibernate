package org.example.service;

import org.example.model.Office;
import org.example.repository.DoctorRepositoryImpl;
import org.example.repository.OfficeRepositoryImpl;

import java.util.List;

public class OfficeService
{
    private final OfficeRepositoryImpl officeRepository;


    public OfficeService(OfficeRepositoryImpl officeRepository)
    {
        this.officeRepository = officeRepository;
    }

    public void createOffice(Office office)
    {
        officeRepository.createOffice(office);
    }

    public Office getOfficeById(int officeId)
    {
        return officeRepository.getOfficeById(officeId);
    }

    public void updateOffice(Office office)
    {
        officeRepository.updateOffice(office);
    }

    public void deleteOffice(int officeId)
    {
        officeRepository.deleteOffice(officeId);
    }

    public List<Office> getAllOffices()
    {
        return officeRepository.getAllOffices();
    }
}
