package org.example;

import org.example.model.Appointment;
import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.repository.PatientRepositoryImpl;
import org.example.service.AppointmentService;
import org.example.service.DoctorService;
import org.example.service.PatientService;
import org.example.service.SetUpMain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static SetUpMain setUpMain;



    public static void main(String[] args) {
        Main.setUpMain= new SetUpMain();
        // handling first menu
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do
        {
            System.out.println("1. Manage patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Exit");
            System.out.println("--------------------------------");
            System.out.print("Enter an number: " );
            int option;
            try
            {
                option = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
                option = 0; // Set default value
            }
            switch (option)
            {
                case 1:
                    managePatients(setUpMain.getPatientService(), scanner);
                    break;
                case 2:
                    manageDoctors(setUpMain.getDoctorService(), scanner);
                    break;
                case 3:
                    manageAppointments(setUpMain.getAppointmentService(), scanner);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Exiting...");
            }
        } while (!exit);
        // Close scanner at very end
        scanner.close();
        setUpMain.getSessionFactory().close();
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
    private static void manageAppointments(AppointmentService appointmentService, Scanner scanner)
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
                    Patient patient = setUpMain.getPatientService().getPatientById(scanner.nextInt());
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
                    Doctor doctor = setUpMain.getDoctorService().getDoctorById(scanner.nextInt());
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
                    System.out.println("Appointment created successfully.");
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
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    scanner.nextLine();  // consume newline
                    appointment = appointmentService.getAppointmentById(appointmentId);  // Use service here
                    if (appointment != null) {
                        System.out.print("Enter new Patient ID: ");
                        appointment.getPatient().setPatientId(scanner.nextInt());
                        scanner.nextLine(); // clear line
                        System.out.print("Enter new Doctor ID: ");
                        appointment.getDoctor().setDoctorId(scanner.nextInt());
                        scanner.nextLine(); // clear line
                        System.out.print("Enter new Appointment Date: ");
                        appointment.setAppointmentDate(scanner.nextLine());
                        System.out.print("Enter new notes: ");
                        appointment.setNotes(scanner.nextLine());
                        appointmentService.updateAppointment(appointment);  // Use service here
                        System.out.println("Appointment updated successfully.");
                    } else {
                        System.out.println("Appointment not found.");
                    }
                    break;
                // 4. Delete Appointment
                case 4:
                    // Application calls the service layer, not the repository directly
                    System.out.print("Enter Appointment ID: ");
                    appointmentId = scanner.nextInt();
                    appointmentService.deleteAppointment(appointmentId);  // Use service here
                    System.out.println("Appointment deleted successfully.");
                    break;
                case 5:
                    // Exiting manage doctors menu
                    exit = true;
            }
        } while (!exit);
    }
}