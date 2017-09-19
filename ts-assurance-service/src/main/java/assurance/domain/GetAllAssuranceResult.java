package assurance.domain;

import java.util.ArrayList;

public class GetAllAssuranceResult {

    private boolean status;

    private String message;

    private ArrayList<Assurance> assurances;

    public GetAllAssuranceResult() {
        //Default Constructor
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Assurance> getAssurances() {
        return assurances;
    }

    public void setAssurances(ArrayList<Assurance> assurances) {
        this.assurances = assurances;
    }
}
