package Model;

public class Users {
    private int id;
    private String lname;
    private String fname;
    private String email;
    private String keyVadation;


    /*
     * Getter And Setter
     * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getKeyVadation() {
        return keyVadation;
    }

    public void setKeyVadation(String keyVadation) {
        this.keyVadation = keyVadation;
    }
}
