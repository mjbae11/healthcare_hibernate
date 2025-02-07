package org.example.service;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.AppointmentRepositoryImpl;
import org.example.repository.DoctorRepositoryImpl;
import org.example.repository.PatientRepositoryImpl;

import java.util.List;

public class AppointmentService
{
    private final AppointmentRepositoryImpl appointmentRepository;
    private final DoctorRepositoryImpl doctorRepository;
    private final PatientRepositoryImpl patientRepository;

    public AppointmentService(AppointmentRepositoryImpl appointmentRepository, DoctorRepositoryImpl doctorRepository, PatientRepositoryImpl patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public void createAppointment(Appointment appointment) {
        appointmentRepository.createAppointment(appointment);
    }

    public Appointment getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public void updateAppointment(Appointment appointment,  Patient newPatient, Doctor newDoctor) {
        if (appointment != null)
        {
            Doctor doctor = doctorRepository.getDoctorById(appointment.getDoctor().getDoctorId());
            Patient patient = patientRepository.getPatientById(appointment.getPatient().getPatientId());
//
            boolean hasOtherAppointments = appointmentRepository.hasOtherAppointmentsBetween(doctor.getDoctorId(), patient.getPatientId());

            if (!hasOtherAppointments)
            {
                // Delete the appointment Doctor and Patient, with update
                doctorRepository.removePatientFromDoctor(doctor.getDoctorId(), patient);
                patientRepository.removeDoctorFromPatient(patient.getPatientId(), doctor);
                doctorRepository.updateDoctor(doctor);
                patientRepository.updatePatient(patient);
                // Add new doctor and new patient to appointment and update
                doctorRepository.addPatientToDoctor(newDoctor.getDoctorId(), newPatient);
                patientRepository.addDoctorToPatient(newPatient.getPatientId(), newDoctor);
                doctorRepository.updateDoctor(newDoctor);
                patientRepository.updatePatient(newPatient);
                // Finally update the appointment to the newDoctor and newPatient
                appointment.setPatient(newPatient);
                appointment.setDoctor(newDoctor);
                appointmentRepository.updateAppointment(appointment);
            }
        }
    }

//    public void deleteAppointment(int id) {
//        appointmentRepository.deleteAppointment(id);
//    }

    public void deleteAppointment(int id)
    {
        Appointment appointment = appointmentRepository.getAppointmentById(id);
        if (appointment != null)
        {
            Doctor doctor = doctorRepository.getDoctorById(appointment.getDoctor().getDoctorId());
            Patient patient = patientRepository.getPatientById(appointment.getPatient().getPatientId());
//            Patient patient = appointment.getPatient();
//            Doctor doctor = appointment.getDoctor();

            appointmentRepository.deleteAppointment(id);
            boolean hasOtherAppointments = appointmentRepository.hasOtherAppointmentsBetween(doctor.getDoctorId(), patient.getPatientId());

            if (!hasOtherAppointments)
            {
                doctorRepository.removePatientFromDoctor(doctor.getDoctorId(), patient);
                patientRepository.removeDoctorFromPatient(patient.getPatientId(), doctor);
                doctorRepository.updateDoctor(doctor);
                patientRepository.updatePatient(patient);
            }
        }
    }
    // Used on the service layer, isn't used in the main at all
//    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId)
//    {
//        return appointmentRepository.hasOtherAppointmentsBetween(doctorId, patientId);
//    }
}