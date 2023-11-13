package lk.ijse.sanasaBank.to;

public class ViewAllMember {

    private String id;
    private String name;
    private String email;
    private String gender;
    private String address;
    private String age;
    private String contact;
    private String nic;

    public ViewAllMember(String id, String name, String email, String gender, String address, String age, String contact, String nic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.contact = contact;
        this.nic = nic;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getNic() {
        return nic;
    }
}
