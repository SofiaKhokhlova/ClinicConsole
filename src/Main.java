import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("ClinicData.data");
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("Enter details of the clinic: name, address, opening hours and working days on each new line");
                Clinic clinic = new Clinic(new Scanner(System.in).nextLine(), new Scanner(System.in).nextLine(),
                        new Scanner(System.in).nextLine(), new Scanner(System.in).nextLine());
                ClinicSystem system = new ClinicSystem(clinic);
                system.start();
            }
        } else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            Clinic clinicData = (Clinic) in.readObject();
            in.close();
            ClinicSystem system = new ClinicSystem(clinicData);
            system.start();
        }
    }
}