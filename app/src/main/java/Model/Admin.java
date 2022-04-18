package Model;

public class Admin extends Users {
    private String password;
    private int tel;

    /*
     * Getters And Setters
     * */

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getLname() {
        return super.getLname();
    }

    @Override
    public void setLname(String lname) {
        super.setLname(lname);
    }

    @Override
    public String getFname() {
        return super.getFname();
    }

    @Override
    public void setFname(String fname) {
        super.setFname(fname);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getKeyVadation() {
        return super.getKeyVadation();
    }

    @Override
    public void setKeyVadation(String keyVadation) {
        super.setKeyVadation(keyVadation);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
