import java.io.Serializable;
import java.util.Scanner;

public class Doctor extends ClinicalStaffMember implements Serializable {
    private int cabinetNumber;
    private Appointment[] appointments = new Appointment[18];
    private Clinic clinic;

    public Doctor(String name, String dateOfBirth, String phoneNumber, String address, String email, String position,
                  String password, String diplomaID, int cabinetNumber, Clinic c) {
        super(name, dateOfBirth, phoneNumber, address, email, position, password, diplomaID);
        this.appointments = setTimeSlotAppointment();
        this.cabinetNumber = cabinetNumber;
        this.clinic = c;
    }

    public void cancelPatientAppointmentForToday() {
        for (Appointment i : appointments) {
            i.setPatient(null);
            i.setDoctor(null);
        }
    }

    public int getCabinetNumber() {
        return cabinetNumber;
    }

    public void setCabinetNumber(int cabinetNumber) {
        this.cabinetNumber = cabinetNumber;
    }

    public void updateAppointment(Patient patient, int ch) {
        Scanner scan = new Scanner(System.in);
        int nurseIndex = -1;

        System.out.print("Write the date, conclusion, treatment on each new line.\n>");
        MedicalJudgment j = new MedicalJudgment(scan.nextLine(), scan.nextLine(), scan.nextLine(), this, patient);
        System.out.print("Do you want make a referral to analysis?\n1-yes\n2-no\n>");
        int n = scan.nextInt();
        if (n == 1) {
            AnalysisType type;
            System.out.println("Specify the type of analysis:\n" +
                    "1 - blood test\n" +
                    "2 - urine analysis\n" +
                    "3 - smear analysis\n>");
            int val = scan.nextInt();
            System.out.println("Write title the analysis: ");
            String title = new Scanner(System.in).nextLine();

            while (true) {
                switch (val) {
                    case 1 -> {
                        type = AnalysisType.BLOOD_TEST;
                        nurseIndex = clinic.getRandomNurseByType(type);
                        MedicalAnalysis a = new MedicalAnalysis(patient, title, type, clinic.getAllNurses().get(nurseIndex));
                        clinic.getAllNurses().get(nurseIndex).addAnalysis(a);
                        j.addAnalyses(a);
                    }
                    case 2 -> {
                        type = AnalysisType.URINALYSIS;
                        nurseIndex = clinic.getRandomNurseByType(type);
                        MedicalAnalysis b = new MedicalAnalysis(patient, title, type, clinic.getAllNurses().get(nurseIndex));
                        clinic.getAllNurses().get(nurseIndex).addAnalysis(b);
                        j.addAnalyses(b);
                    }
                    case 3 -> {
                        type = AnalysisType.SMEAR_ANALYSIS;
                        nurseIndex = clinic.getRandomNurseByType(type);
                        MedicalAnalysis c = new MedicalAnalysis(patient, title, type, clinic.getAllNurses().get(nurseIndex));
                        clinic.getAllNurses().get(nurseIndex).addAnalysis(c);
                        j.addAnalyses(c);
                    }
                    default -> {
                        System.out.println("You have entered an invalid value");
                    }
                }

                System.out.println("Referral for analysis successfully established");
                System.out.print("Specify the type of analysis:\n" +
                        "1 - blood test\n" +
                        "2 - urine analysis\n" +
                        "3 - smear analysis\n" +
                        "0 - exit\n>");
                val = scan.nextInt();
                if (val == 0) {
                    break;
                }
                System.out.print("Write title the analysis: ");
                title = new Scanner(System.in).nextLine();
            }
        }
        patient.addJudgment(j);
        this.appointments[ch].setPatient(null);
        for (Appointment i : patient.getAppointments()) {
            if (i.equals(this.appointments[ch])) {
                patient.getAppointments().remove(i);
                break;
            }
        }
        System.out.println("Application is over");
    }

    public Appointment[] setTimeSlotAppointment() {
        int size = appointments.length;
        Appointment[] appointments1 = new Appointment[size];
        String[] arr = {"8:00 - 8:19", "8:20 - 8:39", "8:40 - 8:59", "9:00 - 9:19", "9:20 - 9:39", "9:40 - 9:59", "10:00 - 10:19",
                "10:20 - 10:39", "10:40 - 10:59", "11:00 - 11:19", "11:20 - 11:39", "11:40 - 11:59", "13:00 - 13:19", "13:20 - 13:39", "13:40 - 13:59",
                "14:00 - 14:19", "14:20 - 14:39", "14:40 - 14:59"};
        for (int i = 0; i < 18; i++) {
            appointments1[i] = new Appointment(arr[i]);
            appointments1[i].setDoctor(this);
        }
        return appointments1;
    }

    public boolean freeAppointmentTimeSlot() {
        int itr = 0, c = 0;
        for (Appointment i : appointments) {
            if (i.getDoctor() == null) {
                c++;
            }
        }
        if (c == appointments.length)
            return false;
        System.out.println("Appointments Dr." + this.getName());
        for (Appointment i : appointments) {
            if (i.getPatient() == null) {
                itr++;
                System.out.println(itr + ") " + i.getTimeSlot());
            }
        }
        if (itr == 0)
            return false;
        return true;
    }

    public Appointment[] getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nDate of Birthday: " + getDateOfBirth() + "\nPhone Number: " + getPhoneNumber() + "\nAddress: " + getAddress() +
                "\nEmail: " + getEmail() + "\nPosition: " + getPosition() + "\nCabinet: " + cabinetNumber;
    }
}
