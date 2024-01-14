/*Клас Пацієнт
 * - використовується для зберігання даних про пацієнта
 * - має методи: додавання висновку лікаря до списку висновків(використовується як медична картка) та виведення всіх висновків(перегляд мед. картки)*/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Patient extends ClinicalMember implements Serializable {
    private List<MedicalJudgment> judgments = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();


    public Patient(String name, String dateOfBirth, String phoneNumber, String address, String email, String password) {
        super(name, dateOfBirth, phoneNumber, address, email, password);
    }

    public void addJudgment(MedicalJudgment judgment) {
        judgments.add(judgment);
    }

    public void showJudgments() {
        if (judgments == null) {
            System.out.println("You do not have a medical judgments yet");
        }
        for (MedicalJudgment i : judgments) {
            System.out.println("______________");
            System.out.println(i);
            if (i.getAnalyses() != null) {
                System.out.println("Analyses:\n" + i.getAnalyses());
            }
        }
    }

    public void makeAppointment(Doctor doctor) {
        if (!doctor.freeAppointmentTimeSlot())
            System.out.println("Sorry, there are no free appointments, try again tomorrow.");
        else {
            System.out.print("Choose the appointment number that you need\n>");
            int ch = new Scanner(System.in).nextInt();
            doctor.getAppointments()[ch - 1].setPatient(this);
            appointments.add(new Appointment(doctor, this, doctor.getAppointments()[ch - 1].getTimeSlot()));
            System.out.println("Making an appointment is done\nYou have an appointment for " + doctor.getAppointments()[ch - 1].getTimeSlot());
        }
    }

    public void cancelAppointment(Doctor doctor) {
        for (Appointment i : appointments) {
            if (i.getDoctor() == doctor) {
                doctor.getAppointments()[appointments.indexOf(i)].setPatient(null);
                appointments.remove(i);
                break;
            }
        }
    }

    public List<MedicalJudgment> getJudgments() {
        return judgments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nDate of birthday: " + getDateOfBirth() + "\nPhone number: " + getPhoneNumber() + "\nAddress" + getAddress()
                + "\nEmail: " + getEmail();
    }

}
