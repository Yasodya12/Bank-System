package lk.ijse.sanasaBank.to;

public class logIn {
   private String username;

    public String getUsername() {
        return username;
    }

    public logIn(String username, String password) {
        this.username = username;
        this.password = password;
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

    String password;

    public logIn() {
    }

    @Override
    public String toString() {
        return "logIn{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
