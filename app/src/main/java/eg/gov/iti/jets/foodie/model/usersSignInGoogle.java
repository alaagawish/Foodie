package eg.gov.iti.jets.foodie.model;

public class usersSignInGoogle {
    String email, userName;

    public usersSignInGoogle() {
    }

    public usersSignInGoogle(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}