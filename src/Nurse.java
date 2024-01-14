import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Nurse extends ClinicalStaffMember implements Serializable {
    private List<MedicalAnalysis> analyses = new ArrayList<>();
    private AnalysisType analysisType;

    public Nurse(String name, String dateOfBirth, String phoneNumber, String address, String email, String position, AnalysisType analysisType,
                 String password, String diplomaID) {
        super(name, dateOfBirth, phoneNumber, address, email, position, password, diplomaID);
        this.analysisType = analysisType;
    }

    public AnalysisType getAnalysisType() {
        return analysisType;
    }

    public void addAnalysis(MedicalAnalysis a) {
        analyses.add(a);
    }

    public List<MedicalAnalysis> getAnalyses() {
        return analyses;
    }

    public void analysis() {
        int y = 1;
        if (analyses == null)
            System.out.println("You don't have any analyses to process");
        else {
            for (MedicalAnalysis i : analyses) {
                System.out.println(y + ") " + i.getPatient().getName());
                y++;
            }
            System.out.print("Enter the analysis number\n>");
            int analysisNumber = new Scanner(System.in).nextInt();
            for (MedicalAnalysis itr : analyses) {
                if (analyses.indexOf(itr) == analysisNumber - 1) {
                    System.out.print("Write down the result and date of analysis for the patient " +
                            itr.getPatient().getName() + " on each new line\n> ");
                    itr.setResult(new Scanner(System.in).nextLine());
                    itr.setDate(new Scanner(System.in).nextLine());
                    itr.setStatus(AnalysisStatus.DONE);
                    this.analyses.remove(itr);
                    break;
                }
            }

        }
    }

    public String toString() {
        return "Name: " + getName() + "\nDate of birthday: " + getDateOfBirth() + "\nPhone number: " + getPhoneNumber() +
                "\nAddress: " + getAddress() + "\nEmail: " + getEmail() + "\nPosition: " + getPosition();
    }

}
