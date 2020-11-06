package ee.bcs.valiit.tasks.bank_controller;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String fName;
    private String lName;
    private String idCode;
    private List<String> clientsAccounts = new ArrayList();

    public Client(String fName, String lName, String idCode) {
        this.fName = fName;
        this.lName = lName;
        this.idCode = idCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public List<String> getClientsAccounts() {
        return clientsAccounts;
    }

    public void setClientsAccounts(List<String> clientsAccounts) {
        this.clientsAccounts = clientsAccounts;
    }
}
