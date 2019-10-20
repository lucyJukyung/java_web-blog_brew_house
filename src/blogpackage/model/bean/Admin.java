package blogpackage.model.bean;

public class Admin {

    protected int AdminID;
    protected String username;
    protected String password;

    // please add more constructors if you need
    public Admin(int adminID, String username, String password) {
        AdminID = adminID;
        this.username = username;
        this.password = password;
    }

    public Admin() {
    }

    //getters and setters
    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

} // end admin.java

