import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalJudgment implements Serializable {
    private String date;
    private String conclusion;
    private String treatment;
    private Doctor doctor;
    private Patient patient;
    private List<MedicalAnalysis> analyses = new ArrayList<>();

    public MedicalJudgment(String date, String conclusion, String treatment, Doctor doctor, Patient patient) {
        this.date = date;
        this.conclusion = conclusion;
        this.treatment = treatment;
        this.doctor = doctor;
        this.patient = patient;
    }

    public void addAnalyses(MedicalAnalysis analysis) {
        analyses.add(analysis);
    }

    public List<MedicalAnalysis> getAnalyses() {
        return analyses;
    }

    public String getDate() {
        return date;
    }

    public String getConclusion() {
        return conclusion;
    }

    public String getTreatment() {
        return treatment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setAnalyses(List<MedicalAnalysis> analyses) {
        this.analyses = analyses;
    }

    @Override
    public String toString() {
        return "\nDate: " + date + "\nConclusion: " + conclusion + "\nTreatment" + treatment + "\nDoctor: " + doctor.getName() + "\nPatient: " + patient.getName();
    }
}
