import java.io.*;
import java.util.Objects;

/*   Клас Прийом
 * - використовуєтьсяя для зберігання даних про доктора, пацієнта та час прийому*/
public class Appointment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private String timeSlot;

    public Appointment(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Appointment(Doctor doctor, Patient patient, String timeSlot) {
        this.doctor = doctor;
        this.patient = patient;
        this.timeSlot = timeSlot;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        if (this.patient == null)
            return "Time: " + timeSlot + "\nDoctor: " + doctor.getName() +
                    "\nPatient: —";
        return "Time: " + timeSlot + "\nDoctor: " + doctor.getName() +
                "\nPatient: " + patient.getName();
    }

}
