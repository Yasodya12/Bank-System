package lk.ijse.sanasaBank.to;

public class signUp {
   private String username;
   private String password;

   private String adminCode;
    public signUp(String username, String password, String adminCode) {
        this.username = username;
        this.password = password;
        this.adminCode = adminCode;
    }
    public signUp() {
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

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }



    @Override
    public String toString() {
        return "signUp{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", adminCode='" + adminCode + '\'' +
                '}';
    }
}
