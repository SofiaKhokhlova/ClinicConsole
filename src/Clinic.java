import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Clinic implements Serializable {
    private String name;
    private String address;
    private String workTime;
    private String workDays;
    private List<Patient> patients = new ArrayList<>();
    private List<ClinicalStaffMember> staffMembers = new ArrayList<>();

    public Clinic() {
    }

    public Clinic(String name, String address, String workTime, String workDays) {
        this.name = name;
        this.address = address;
        this.workTime = workTime;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Nurse> getAllNurses() {
        List<Nurse> nurseList = new ArrayList<>();
        for (ClinicalStaffMember itr : staffMembers) {
            if (itr instanceof Nurse)
                nurseList.add((Nurse) itr);
        }
        return nurseList;
    }

    public int getRandomNurseByType(AnalysisType type) {
        List<Integer> nurses = new ArrayList<>();
        for (Nurse itr : getAllNurses()) {
            if (itr.getAnalysisType() == type)
                nurses.add(getAllNurses().indexOf(itr));
        }
        return new Random().nextInt(nurses.size());
    }

    public List<ClinicalStaffMember> getStaffMembers() {
        return staffMembers;
    }
}