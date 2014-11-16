package cat.urv.deim.sob.model;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private int id;
    
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return fixNull(this.firstName);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return fixNull(this.lastName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return fixNull(this.email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }

    public String getMessage() {

        return "\nFirst Name: " + getFirstName() + "\n"
                + "Last Name:  " + getLastName() + "\n"
                + "Email:  " + getEmail() + "\n"
                + "User Name:  " + getUserName() + "\n";
    }
}
