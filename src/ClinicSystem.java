import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class ClinicSystem {
    private static int index;
    private static int member;
    private Clinic clinic;

    public ClinicSystem(Clinic c) {
        this.clinic = c;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void start() throws IOException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.print("Would you like to login(1) or register(2)?\n>");
        int choose = scan.nextInt();

        while (choose != 0) {
            switch (choose) {
                case 1 -> {
                    System.out.print("Please, enter phone number\n>");
                    String phone = new Scanner(System.in).nextLine();
                    System.out.print("Please, enter password\n>");
                    String password = new Scanner(System.in).nextLine();
                    if (!logIn(phone, password)) {
                        System.out.println("The data entered is incorrect.\n Try again");
                    } else {
                        switch (member) {
                            case 1 -> {
                                Doctor doctor = (Doctor) clinic.getStaffMembers().get(index);
                                System.out.print("Hello Dr. " + doctor.getName() + " what do you want do?\n" +
                                        "1 - update appointment\n" +
                                        "2 - show all appointments\n" +
                                        "3 - cancel all appointments\n" +
                                        "4 - show all info about me\n" +
                                        "5 - change personal data\n" +
                                        "6 - delete account\n" +
                                        "0 - exit\n>");
                                Appointment[] arr = doctor.getAppointments();
                                int f = new Scanner(System.in).nextInt();
                                while (f != 0) {
                                    switch (f) {
                                        case 1 -> {
                                            // оновлення даних про прийом
                                            System.out.println("Find the appointment, and enter their number.");
                                            // виведення всіх прийомів для пошуку потрібного
                                            for (int i = 0, j = 1; i < arr.length; i++, j++) {
                                                System.out.println("____\n" + j + " " + arr[i]);
                                            }
                                            System.out.print(">");
                                            int ch = new Scanner(System.in).nextInt(); // введення номера прийому для оновлення даних про прийом
                                            doctor.updateAppointment(arr[ch - 1].getPatient(), ch - 1);
                                            System.out.println("____________________");
                                        }
                                        case 2 -> {
                                            for (Appointment ap : arr) {
                                                System.out.println("____\n" + ap);
                                            }
                                            System.out.println("____________________");
                                        }
                                        case 3 -> {
                                            doctor.cancelPatientAppointmentForToday();
                                            System.out.println("____________________");
                                        }
                                        case 4 -> {
                                            System.out.println(doctor);
                                            System.out.println("____________________");
                                        }
                                        case 5 -> {
                                            scanChangeData(doctor);
                                            System.out.println("____________________");
                                        }
                                        case 6 -> {
                                            System.out.print("Are you sure? yes(1) no(2)\n>");
                                            if (new Scanner(System.in).nextInt() == 1) {
                                                clinic.getStaffMembers().remove(doctor);
                                            }
                                            System.out.println("____________________");
                                        }
                                    }
                                    System.out.print("What do you want do?\n" +
                                            "1 - update appointment\n" +
                                            "2 - show all appointments\n" +
                                            "3 - cancel all appointments\n" +
                                            "4 - show all info about me\n" +
                                            "5 - change personal data\n" +
                                            "6 - delete account\n" +
                                            "0 - exit\n>");
                                    f = new Scanner(System.in).nextInt();
                                }
                            }
                            case 2 -> {
                                Nurse nurse = (Nurse) clinic.getStaffMembers().get(index);
                                System.out.print("Hello " + nurse.getName() + " what do you want do?\n" +
                                        "1 - show all the analyses to be processed\n" +
                                        "2 - update analyses\n" +
                                        "3 - show all info about me\n" +
                                        "4 - change personal data\n" +
                                        "5 - delete account\n" +
                                        "0 - exit\n>");
                                int b = new Scanner(System.in).nextInt();
                                while (b != 0) {
                                    switch (b) {
                                        case 1 -> {
                                            for (MedicalAnalysis analysis : nurse.getAnalyses())
                                                System.out.println("____\n" + analysis.getPatient() + "\n" + analysis.getTitle() + "\n" + analysis.getType());
                                            System.out.println("____________________");
                                        }
                                        case 2 -> {
                                            nurse.analysis();
                                            System.out.println("____________________");
                                        }
                                        case 3 -> {
                                            System.out.println(nurse);
                                        }
                                        case 4 -> {
                                            scanChangeData(nurse);
                                            System.out.println("____________________");
                                        }
                                        case 5 -> {
                                            System.out.print("Are you sure? yes(1) no(2)\n>");
                                            if (new Scanner(System.in).nextInt() == 1) {
                                                clinic.getStaffMembers().remove(nurse);
                                            }
                                            System.out.println("____________________");
                                        }
                                    }
                                    System.out.print("What do you want do?\n" +
                                            "1 - show all the analyses to be processed\n" +
                                            "2 - update analyses\n" +
                                            "3 - show all info about me\n" +
                                            "4 - change personal data\n" +
                                            "5 - delete account\n" +
                                            "0 - exit\n>");
                                    b = new Scanner(System.in).nextInt();
                                }
                            }
                            case 3 -> {
                                Patient patient = clinic.getPatients().get(index);
                                System.out.print("Hello " + patient.getName() + "!\nWhat do you want do?\n" +
                                        "1 - show judgments\n" +
                                        "2 - make appointment\n" +
                                        "3 - cancel appointment\n" +
                                        "4 - show all info about me\n" +
                                        "5 - change personal data\n" +
                                        "6 - delete account\n" +
                                        "0 - exit\n>");
                                int g = new Scanner(System.in).nextInt();
                                while (g != 0) {
                                    switch (g) {
                                        case 1 -> {
                                            patient.showJudgments();
                                            System.out.println("____________________");
                                        }
                                        case 2 -> {
                                            int h = 0;
                                            System.out.println("Please, enter the doctor`s specialization");
                                            String specialization = new Scanner(System.in).nextLine();
                                            for (ClinicalStaffMember itr : clinic.getStaffMembers()) {
                                                if (itr.getPosition().equals(specialization) && itr instanceof Doctor) {
                                                    System.out.println(clinic.getStaffMembers().indexOf(itr) + " " + itr.getName());
                                                    h++;
                                                }
                                            }
                                            if (h == 0) {
                                                System.out.println("Sorry, we don't have a doctor in the right specialty");
                                            } else {
                                                System.out.println("Please, enter the doctor`s number that you wish to visit");
                                                int sc = new Scanner(System.in).nextInt();
                                                for (ClinicalStaffMember iter : clinic.getStaffMembers()) {
                                                    if (clinic.getStaffMembers().indexOf(iter) == sc) {
                                                        clinic.getPatients().get(index).makeAppointment((Doctor) iter);
                                                        break;
                                                    }
                                                }
                                            }
                                            System.out.println("____________________");
                                        }
                                        case 3 -> {
                                            int as = 1;
                                            for (Appointment i : clinic.getPatients().get(index).getAppointments()) {
                                                System.out.println(as + " " + i);
                                            }
                                            System.out.println("Enter the appointment number that you want canceled");
                                            int indApp = new Scanner(System.in).nextInt();
                                            patient.cancelAppointment((Doctor) clinic.getStaffMembers().
                                                    get(clinic.getStaffMembers().indexOf(patient.getAppointments().
                                                            get(indApp - 1).getDoctor())));
                                            System.out.println("____________________");
                                        }
                                        case 4 -> {
                                            System.out.println(patient);
                                        }
                                        case 5 -> {
                                            scanChangeData(patient);
                                            System.out.println("____________________");
                                        }
                                        case 6 -> {
                                            System.out.println("Are you sure? yes(1)\n>");
                                            if (new Scanner(System.in).nextInt() == 1)
                                                clinic.getPatients().remove(patient);
                                            System.out.println("____________________");
                                        }
                                    }
                                    System.out.print("What do you want do?\n" +
                                            "1 - show judgments\n" +
                                            "2 - make appointment\n" +
                                            "3 - cancel appointment\n" +
                                            "4 - show all info about me\n" +
                                            "5 - change personal data\n" +
                                            "6 - delete account\n" +
                                            "0 - exit\n>");
                                    g = new Scanner(System.in).nextInt();
                                }
                            }
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Who do you want to register as?\n" +
                            "1 - Doctor\n" +
                            "2 - Nurse\n" +
                            "3 - Patient\n>");
                    switch (new Scanner(System.in).nextInt()) {
                        case 1 -> {
                            System.out.print("Please enter the name\n>");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Please enter the phone number\n>");
                            String phone = new Scanner(System.in).nextLine();
                            if (checkRegistered(name, phone))
                                System.out.println("You are already registered, please log in");
                            else {
                                System.out.print("Please enter the date of birth\n>");
                                String dateOfBirth = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the email\n>");
                                String email = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the address\n>");
                                String address = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the position\n>");
                                String position = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the password\n>");
                                String password = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the diploma ID\n>");
                                String diploma = new Scanner(System.in).nextLine();
                                int q = checkClinicalMembers(phone, email);
                                if (q == 1) {
                                    System.out.print("Phone number must contain 10 digits, try again\n>");
                                    phone = new Scanner(System.in).nextLine();
                                } else if (q == 2) {
                                    System.out.print("The email address must contain the @ symbol, try again\n>");
                                    email = new Scanner(System.in).nextLine();
                                }
                                if (!checkDiploma(diploma)) {
                                    System.out.print("Your diploma ID invalid, try again\n>");
                                    diploma = new Scanner(System.in).nextLine();
                                }
                                int cabinet = new Random().nextInt(100);
                                for (ClinicalStaffMember i : clinic.getStaffMembers()) {
                                    if (i instanceof Doctor && ((Doctor) i).getCabinetNumber() == cabinet)
                                        cabinet++;
                                }
                                clinic.getStaffMembers().add(new Doctor(name, dateOfBirth, phone, address, email, position, password, diploma, cabinet, clinic));
                            }
                            System.out.println("____________________");
                        }
                        case 2 -> {
                            System.out.print("Please enter the name\n>");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Please enter the phone number\n>");
                            String phone = new Scanner(System.in).nextLine();
                            if (checkRegistered(name, phone))
                                System.out.println("You are already registered, please log in");
                            else {
                                System.out.print("Please enter the date of birth\n>");
                                String dateOfBirth = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the email\n>");
                                String email = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the address\n>");
                                String address = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the position\n>");
                                String position = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the password\n>");
                                String password = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the diploma ID\n>");
                                String diploma = new Scanner(System.in).nextLine();
                                AnalysisType type = null;
                                System.out.print("Enter the type of analysis you will process\n1 - blood\n2 - urinal\n3 - smear\n>");
                                switch (new Scanner(System.in).nextInt()) {
                                    case 1 -> {
                                        type = AnalysisType.BLOOD_TEST;
                                    }
                                    case 2 -> {
                                        type = AnalysisType.URINALYSIS;
                                    }
                                    case 3 -> {
                                        type = AnalysisType.SMEAR_ANALYSIS;
                                    }
                                }
                                int m = checkClinicalMembers(phone, email);
                                if (m == 1) {
                                    System.out.print("Phone number must contain 10 digits, try again\n>");
                                    phone = new Scanner(System.in).nextLine();
                                } else if (m == 2) {
                                    System.out.print("The email address must contain the @ symbol, try again\n>");
                                    email = new Scanner(System.in).nextLine();
                                }
                                if (!checkDiploma(diploma)) {
                                    System.out.print("Your diploma ID invalid, try again\n>");
                                    diploma = new Scanner(System.in).nextLine();
                                }
                                clinic.getStaffMembers().add(new Nurse(name, dateOfBirth, phone, address, email, position, type, password, diploma));
                            }
                            System.out.println("____________________");
                        }
                        case 3 -> {
                            System.out.print("Please enter the name\n>");
                            String name = new Scanner(System.in).nextLine();
                            System.out.print("Please enter the phone number\n>");
                            String phone = new Scanner(System.in).nextLine();
                            if (checkRegistered(name, phone))
                                System.out.println("You are already registered, please log in");
                            else {
                                System.out.print("Please enter the date of birth\n>");
                                String dateOfBirth = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the email\n>");
                                String email = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the address\n>");
                                String address = new Scanner(System.in).nextLine();
                                System.out.print("Please enter the password\n>");
                                String password = new Scanner(System.in).nextLine();
                                int p = checkClinicalMembers(phone, email);
                                if (p == 1) {
                                    System.out.print("Phone number must contain 10 digits, try again\n>");
                                    phone = new Scanner(System.in).nextLine();
                                } else if (p == 2) {
                                    System.out.print("The email address must contain the @ symbol, try again\n>");
                                    email = new Scanner(System.in).nextLine();
                                }
                                clinic.getPatients().add(new Patient(name, dateOfBirth, phone, address, email, password));
                            }
                            System.out.println("____________________");
                        }
                    }
                }
            }
            System.out.print("Would you like to login(1), register(2) or close(0)?\n>");
            choose = new Scanner(System.in).nextInt();
        }
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ClinicData.data"));
        out.writeObject(clinic);
        out.close();
    }

    private boolean logIn(String phone, String password) {
        // визначення ролі користувача, спочатку перевіряємо серед мед.працівників, потім серед пацієнтів
        for (ClinicalStaffMember itr : clinic.getStaffMembers()) {
            if (itr instanceof Doctor && itr.getPhoneNumber().equals(phone) && itr.getPassword().equals(password)) {
                member = 1;
                index = clinic.getStaffMembers().indexOf(itr);
                return true;
            }
            if (itr instanceof Nurse && itr.getPhoneNumber().equals(phone) && itr.getPassword().equals(password)) {
                member = 2;
                index = clinic.getStaffMembers().indexOf(itr);
                return true;
            }
        }
        for (Patient iter : clinic.getPatients()) {
            if (iter.getPhoneNumber().equals(phone) && iter.getPassword().equals(password)) {
                member = 3;
                index = clinic.getPatients().indexOf(iter);
                return true;
            }
        }
        return false;
    }

    public void scanChangeData(ClinicalMember clinicalMember) {
        Scanner scan = new Scanner(System.in);
        System.out.print("What do you want change?\n1 - email\n2 - phone number\n3 - address\n4 - name\n5 - password\n>");
        switch (new Scanner(System.in).nextInt()) {
            case 1 -> {
                System.out.print("Enter the new email address\n>");
                clinicalMember.setEmail(new Scanner(System.in).nextLine());
            }
            case 2 -> {
                System.out.print("Enter the new phone number\n>");
                clinicalMember.setPhoneNumber(new Scanner(System.in).nextLine());
            }
            case 3 -> {
                System.out.print("Enter the new address\n>");
                clinicalMember.setAddress(new Scanner(System.in).nextLine());
            }
            case 4 -> {
                System.out.print("Enter the new name\n>");
                clinicalMember.setName(new Scanner(System.in).nextLine());
            }
            case 5 -> {
                System.out.print("Enter the new password\n>");
                clinicalMember.setPassword(new Scanner(System.in).nextLine());
            }
            default -> System.out.println("You entered an invalid value");
        }
    }

    public int checkClinicalMembers(String phoneNumber, String email) {
        int checked = 0;
        if (phoneNumber.length() != 10)
            checked = 1;
        if (!email.contains("@"))
            checked = 2;
        return checked;
    }

    public boolean checkRegistered(String name, String phone) {
        for (ClinicalStaffMember itr : clinic.getStaffMembers()) {
            if (itr.getName().equals(name) && itr.getPhoneNumber().equals(phone))
                return true;
        }
        for (Patient iter : clinic.getPatients()) {
            if (iter.getName().equals(name) && iter.getPhoneNumber().equals(phone))
                return true;
        }
        return false;
    }

    public boolean checkDiploma(String diplomaID) {
        return diplomaID.matches("[A-Z]{2}[0-9]{8}");
    }
}

