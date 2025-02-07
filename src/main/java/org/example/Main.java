package org.example;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Office;
import org.example.model.Patient;
import org.example.repository.AppointmentRepositoryImpl;
import org.example.repository.DoctorRepositoryImpl;
import org.example.repository.OfficeRepositoryImpl;
import org.example.repository.PatientRepositoryImpl;
import org.example.service.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

//    private static SetUpMain setUpMain;

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();

        // Repositories
        DoctorRepositoryImpl doctorRepository = new DoctorRepositoryImpl(sessionFactory);
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        AppointmentRepositoryImpl appointmentRepository = new AppointmentRepositoryImpl(sessionFactory);
        OfficeRepositoryImpl officeRepository = new OfficeRepositoryImpl(sessionFactory);
        // Services
        DoctorService doctorService = new DoctorService(doctorRepository);
        PatientService patientService = new PatientService(patientRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository,doctorRepository,patientRepository);
        OfficeService officeService = new OfficeService(officeRepository);
        // Main Menu
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nHealthcare Management System");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Manage Offices");
            System.out.println("5. Exit");
            System.out.print("Please enter a number:");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    managePatients(patientService, scanner);
                    break;
                case 2:
                    manageDoctors(doctorService, scanner);
                    break;
                case 3:
                    manageAppointments(appointmentService, patientService, doctorService, scanner);
                    break;
                case 4:
                    manageOffices(officeService, doctorService, scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Healthcare Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        sessionFactory.close();
        scanner.close();
    }

    private static void managePatients(PatientService patientService, Scanner scanner)
    {
        boolean exit = false;
        // Do while loop to handles menu mis selection and repitition.
        do
        {

            System.out.println("1. Create Patient");
            System.out.println("2. Read Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Back to Main Menu");
            System.out.println("--------------------------------");
            System.out.print("Enter an number: " );
            int option;
            try
            {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
                option = 0; // Set default value
            }
            switch (option) // need to input int or resets
            {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Patient newPatient = new Patient();
                    System.out.print("Enter first name: ");
                    newPatient.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newPatient.setLastName(scanner.nextLine());
                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
                    newPatient.setDateOfBirth(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newPatient.setEmail(scanner.nextLine());
                    System.out.print("Enter phone number: ");
                    newPatient.setPhoneNumber(scanner.nextLine());
                    patientService.createPatient(newPatient);  // Use service here
                    System.out.println("Patient created successfully.");
                    break;
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    Patient readPatient = patientService.getPatientById(scanner.nextInt());  // Use service here
                    if (readPatient != null)
                    {
                        System.out.println("Patient ID: " + readPatient.getPatientId());
                        System.out.println("Name: " + readPatient.getFirstName() + " " + readPatient.getLastName());
                        System.out.println("Date of Birth: " + readPatient.getDateOfBirth());
                        System.out.println("Email: " + readPatient.getEmail());
                        System.out.println("Phone: " + readPatient.getPhoneNumber());
                    } else
                    {
                        System.out.println("Patient not found.");
                    }
                    break;
                // UPDATE patient
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    Patient updatePatient = patientService.getPatientById(scanner.nextInt());  // Use service here
                    scanner.nextLine(); // Clear line
                    if (updatePatient != null)
                    {
                        System.out.print("Enter new first name: ");
                        updatePatient.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        updatePatient.setLastName(scanner.nextLine());
                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
                        updatePatient.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        updatePatient.setEmail(scanner.nextLine());
                        System.out.print("Enter new phone number: ");
                        updatePatient.setPhoneNumber(scanner.nextLine());
                        patientService.updatePatient(updatePatient);  // Use service here
                        System.out.println("Patient updated successfully.");
                    } else
                    {
                        System.out.println("Patient not found.");
                    }
                    break;
                // DELETE PATIENT
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    if (patientService.getPatientById(patientId) != null)
                    {
                        patientService.deletePatient(patientId);
                        System.out.println("Patient deleted successfully.");
                    } else
                    {
                        System.err.println("Did not delete successfully");
                    }
                    break;
                case 5:
                    // Exiting managePatients
                    exit = true;
            }

        } while (!exit);
    }

    // for manage doctors menu option
    private static void manageDoctors(DoctorService doctorService, Scanner scanner)
    {
        boolean exit = false;
        do
        {
            System.out.println("1. Create Doctor");
            System.out.println("2. Read Doctor");
            System.out.println("3. Update Doctor");
            System.out.println("4. Delete Doctor");
            System.out.println("5. Back to Main Menu");
            System.out.println("--------------------------------");
            System.out.print("Enter an number: " );
            int option;
            try
            {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
                option = 0; // Set default value
            }
            switch (option) // need to input int or resets
            {
                case 1:
                    // Application calls the service layer, not the repository directly
                    Doctor newDoctor = new Doctor();
                    System.out.print("Enter first name: ");
                    newDoctor.setFirstName(scanner.nextLine());
                    System.out.print("Enter last name: ");
                    newDoctor.setLastName(scanner.nextLine());
                    System.out.print("Enter email: ");
                    newDoctor.setEmail(scanner.nextLine());
                    System.out.print("Enter speciality: ");
                    newDoctor.setSpeciality(scanner.nextLine());
                    doctorService.createDoctor(newDoctor);  // Use service here
                    System.out.println("Doctor created successfully.");
                    break;
                // 2. Read Doctor
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    Doctor doctor = doctorService.getDoctorById(scanner.nextInt());  // Use service here
                    if (doctor != null) {
                        System.out.println("Doctor ID: " + doctor.getDoctorId());
                        System.out.println("Name: " + doctor.getFirstName() + " " + doctor.getLastName());
                        System.out.println("Email: " + doctor.getEmail());
                        System.out.println("Specialty: " + doctor.getSpeciality());
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                // 3. Update Doctor
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    Doctor updateDoctor = doctorService.getDoctorById(scanner.nextInt());// Use service here
                    scanner.nextLine();  // consume newline
                    if (updateDoctor != null) {
                        System.out.print("Enter new first name: ");
                        updateDoctor.setFirstName(scanner.nextLine());
                        System.out.print("Enter new last name: ");
                        updateDoctor.setLastName(scanner.nextLine());
                        System.out.print("Enter new email: ");
                        updateDoctor.setEmail(scanner.nextLine());
                        System.out.print("Enter new specialty: ");
                        updateDoctor.setSpeciality(scanner.nextLine());
                        doctorService.updateDoctor(updateDoctor);  // Use service here
                        System.out.println("Doctor updated successfully.");
                    } else {
                        System.out.println("Doctor not found.");
                    }
                    break;
                // 4. Delete Doctor
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    if (doctorService.getDoctorById(doctorId) != null)
                    {
                        doctorService.deleteDoctor(doctorId);
                        System.out.println("Doctor deleted successfully.");
                    } else
                    {
                        System.err.println("Did not delete successfully");
                    }
                    break;
                case 5:
                    // Exiting manage doctors menu
                    exit = true;
            }
        } while (!exit);
    }


    // manage appointments
    private static void manageAppointments(AppointmentService appointmentService, PatientService patientService, DoctorService doctorService, Scanner scanner)
    {
        boolean exit = false;
        do
        {
            System.out.println("1. Create Appointment");
            System.out.println("2. Read Appointment");
            System.out.println("3. Update Appointment");
            System.out.println("4. Delete Appointment");
            System.out.println("5. Back to Main Menu");
            System.out.println("--------------------------------");
            System.out.print("Enter an number: " );
            int option;
            try
            {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
                option = 0; // Set default value
            }
            switch (option)
            {
                // create Appointment
                case 1:
                    // Application calls the service layer, not the repository directly
                    Appointment newAppointment = new Appointment();
                    System.out.print("Enter Patient ID: ");
                    int createPatientId = scanner.nextInt();
                    Patient patient = patientService.getPatientById(createPatientId);
                    // Exception Handling if patient exists
                    if (patient != null) {
                        newAppointment.setPatient(patient);
                    } else
                    {
                        System.err.println("Patient does not exist or there is an error");
                    }
                    // Clear line
                    scanner.nextLine();
                    System.out.print("Enter Doctor Id: ");
                    int createDoctorId = scanner.nextInt();
                    Doctor doctor = doctorService.getDoctorById(createDoctorId);
                    // Exception Handling if patient exists
                    if (doctor != null) {
                        newAppointment.setDoctor(doctor);
                    } else
                    {
                        System.err.println("Doctor does not exist or there is an error");
                    }
                    // Clear line
                    scanner.nextLine();
                    System.out.print("Enter appointment date (YYYY-MM-DD): ");
                    newAppointment.setAppointmentDate(scanner.nextLine());
                    System.out.print("Enter notes: ");
                    newAppointment.setNotes(scanner.nextLine());
                    appointmentService.createAppointment(newAppointment);  // Use service here
                    // Adding appointment to respective patient doctor relationship
                    doctorService.addPatientToDoctor(createDoctorId, patient);
                    patientService.addDoctorToPatient(createPatientId, doctor);
                    System.out.println("Appointment created successfully and updated associated doctors and patients.");
                    break;
                // 2. Read Appointment
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    Appointment appointment = appointmentService.getAppointmentById(appointmentId);  // Use service here
                    if (appointment != null) {
                        System.out.println("Appointment ID: " + appointment.getAppointmentId());
                        System.out.println("Patient Id: " + appointment.getPatient().getPatientId());
                        System.out.println("Doctor ID: " + appointment.getDoctor().getDoctorId());
                        System.out.println("Appointment Date: " + appointment.getAppointmentDate());
                        System.out.println("Notes: " + appointment.getNotes());
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                // 3. Update Appointment
                case 3:
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    // Get current appointment
                    appointment = appointmentService.getAppointmentById(appointmentId);
                    if (appointment != null) {
                        System.out.print("Enter new Patient ID: ");
                        // Save for the new appointment
                        Patient newPatient = patientService.getPatientById(scanner.nextInt());
                        scanner.nextLine(); // clear line
                        System.out.print("Enter new Doctor ID: ");
                        // Save for the new appointment
                        Doctor newDoctor = doctorService.getDoctorById(scanner.nextInt());
                        scanner.nextLine(); // clear line
                        System.out.print("Enter new Appointment Date: ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter new notes: ");
                        appointment.setNotes(scanner.nextLine());
                        // Update Appointment using newDoctor and newPatient
                        appointmentService.updateAppointment(appointment, newPatient, newDoctor);
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    break;
                // 4. Delete Appointment
                case 4:
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    if (appointmentService.getAppointmentById(appointmentId) != null)
                    {
                        appointmentService.deleteAppointment(appointmentId);
                        System.out.println("Appointment deleted successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    break;
                case 5:
                    // Exiting manage doctors menu
                    exit = true;
            }
        } while (!exit);
    }

    public static void manageOffices(OfficeService officeService, DoctorService doctorService, Scanner scanner)
    {
        boolean exit = false;
        do
        {
            System.out.println("1. Create Office");
            System.out.println("2. Read Office");
            System.out.println("3. Update Office");
            System.out.println("4. Delete Office");
            System.out.println("5. List all Offices");
            System.out.println("6. Back to Main Menu");
            System.out.println("--------------------------------");
            System.out.print("Enter an number: " );
            int option;
            try
            {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
                option = 0; // Set default value
            }
            switch (option)
            {
                // create Office
                case 1:
                    Office office = new Office();
                    System.out.print("Enter Location: ");
                    office.setLocation(scanner.nextLine());
                    System.out.print("Enter Phone number (ex. 1234567890):");
                    office.setPhone(scanner.nextLine());
                    System.out.print("Enter Doctor Id: ");
                    Doctor doctor = doctorService.getDoctorById(scanner.nextInt());
                    // Exception Handling if Doctor exists
                    if (doctor != null) {
                        office.setDoctor(doctor);
                    } else
                    {
                        System.err.println("Doctor does not exist or there is an error");
                        break;
                    }
                    // Clear line
                    scanner.nextLine();
                    officeService.createOffice(office);
                    System.out.println("Office created successfully.");
                    break;
                // 2. Get Office by id
                case 2:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    Office readOffice = officeService.getOfficeById(scanner.nextInt());  // Use service here
                    if (readOffice != null)
                    {
                        System.out.println("Office ID: " + readOffice.getOfficeId());
                        System.out.println("Office Location: " + readOffice.getLocation());
                        System.out.println("Phone Number: " + readOffice.getPhone());
                        System.out.println("Doctor ID: " + readOffice.getDoctor().getDoctorId());
                    } else
                    {
                        System.out.println("Office not found.");
                    }
                    break;
                // UPDATE office
                case 3:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    Office updateOffice = officeService.getOfficeById(scanner.nextInt());  // Use service here
                    scanner.nextLine(); // Clear line
                    if (updateOffice != null)
                    {
                        System.out.print("Enter New Location: ");
                        updateOffice.setLocation(scanner.nextLine());
                        System.out.print("Enter New Phone number (ex. 1234567890):");
                        updateOffice.setPhone(scanner.nextLine());
                        System.out.print("Enter New Doctor Id: ");
                        Doctor updateDoctor = doctorService.getDoctorById(scanner.nextInt());
                        // Exception Handling if doctor exists
                        if (updateDoctor != null) {
                            updateOffice.setDoctor(updateDoctor);
                        } else
                        {
                            System.err.println("Doctor does not exist or there is an error");
                        }
                        // Clear line
                        scanner.nextLine();
                        officeService.updateOffice(updateOffice);
                    } else
                    {
                        System.out.println("Office not found.");
                    }
                    break;
                // DELETE Office
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Office ID: ");
                    int officeId = scanner.nextInt();
                    scanner.nextLine(); // clear line
                    if (officeService.getOfficeById(officeId) != null)
                    {
                        // Remove reference to associated entity (doctor)
                        officeService.deleteOffice(officeId);
                        System.out.println("Office deleted successfully.");
                    } else
                    {
                        System.err.println("Did not delete successfully");
                    }
                    break;
                case 5:
                    System.out.println("Listing All Offices:");
                    if (officeService.getAllOffices().isEmpty())
                    {
                        System.out.println("List is empty, add an Office or try again");
                    } else
                    {
                        officeService.getAllOffices().forEach(System.out::println);
                    }
                    break;
                case 6:
                    // Exiting manageOffices
                    exit = true;
            }
        } while (!exit);
    }

}
