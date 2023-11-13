package lk.ijse.sanasaBank.to;


public class AddMember {

    private String custId;
    private String name;
    private String email;
    private String gender;
    private String address;
    private int age;
    private String contact;
    private String nic;

    public AddMember(String custId, String name, String email, String gender, String address, int age, String contact, String nic) {
        this.custId = custId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.contact = contact;
        this.nic = nic;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "AddMember{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", contact='" + contact + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }
    /*public AddMember() {
    }

    public AddMember(String id, String firstName, String lastName, String nic, String contact, String address, int age) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Nic = nic;
        this.contact = contact;
        Address = address;
        Age = age;
    }

    String Id;
    String FirstName;
    String LastName;
    String Nic;
    String contact;
    String Address;
    int Age;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String nic) {
        Nic = nic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return "AddMember{" +
                "Id='" + Id + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Nic='" + Nic + '\'' +
                ", contact='" + contact + '\'' +
                ", Address='" + Address + '\'' +
                ", Age=" + Age +
                '}';
    }*/
}
