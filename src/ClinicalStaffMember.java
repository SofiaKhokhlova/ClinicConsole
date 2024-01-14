import java.io.*;

abstract public class ClinicalStaffMember extends ClinicalMember implements Serializable {
    private String position;
    private String diplomaID;

    public ClinicalStaffMember(String name, String dateOfBirth, String phoneNumber, String address, String email, String position, String password,
                               String diplomaID) {
        super(name, dateOfBirth, phoneNumber, address, email, password);
        this.position = position;
        this.diplomaID = diplomaID;
    }

    public String getDiplomaID() {
        return diplomaID;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
