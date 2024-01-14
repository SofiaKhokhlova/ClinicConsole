import java.io.Serializable;

public class MedicalAnalysis implements Serializable {
    private Patient patient;
    private String title;
    private AnalysisStatus status;
    private AnalysisType type;
    private String result;
    private String date;
    private Nurse nurse;

    public MedicalAnalysis(Patient patient, String title, AnalysisType type, Nurse nurse) {
        this.patient = patient;
        this.title = title;
        this.type = type;
        this.status = AnalysisStatus.REFERRAL_HAS_BEEN_MADE;
        this.nurse = nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnalysisStatus getStatus() {
        return status;
    }

    public void setStatus(AnalysisStatus status) {
        this.status = status;
    }

    public AnalysisType getType() {
        return type;
    }

    public void setType(AnalysisType type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @Override
    public String toString() {
        return "\nPatient: " + patient.getName() + "\nTitle: " + title + "\nAnalysis type: " + type + "\nStatus: " +
                status + "\nResult: " + result + "\nDate: " + date;
    }
}
