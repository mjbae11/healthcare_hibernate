package org.example.service;

import org.example.model.Office;
import org.example.repository.OfficeRepositoryImpl;

import java.util.List;

public class OfficeService
{
    private OfficeRepositoryImpl officeRepository;

    public OfficeService(OfficeRepositoryImpl officeRepository)
    {
        this.officeRepository = officeRepository;
    }

    public void createOffice(Office office)
    {
        officeRepository.createOffice(office);
    }

    Office getOfficeById(int officeId)
    {
        return officeRepository.getOfficeById(officeId);
    }

    void updateOffice(int officeId)
    {
        officeRepository.updateOffice(officeId);
    }

    void deleteOffice(int officeId)
    {
        officeRepository.deleteOffice(officeId);
    }

    List<Office> getAllOffices()
    {
        return officeRepository.getAllOffices();
    }
}
