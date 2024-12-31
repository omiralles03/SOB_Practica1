package deim.urv.cat.homework2.model;

public class User {
    private String firstName;
    private String lastName;
    private String email;

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
}
