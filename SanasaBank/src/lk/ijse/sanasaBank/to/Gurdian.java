package lk.ijse.sanasaBank.to;

public class Gurdian {
   private String id;
   private String name;
   private double netWorth;
   private String nic;
   private String address;
   private String age;

    public Gurdian(String id) {
        this.id = id;
    }

    public Gurdian(String id, String name, double netWorth, String nic, String address, String age) {
        this.id = id;
        this.name = name;
        this.netWorth = netWorth;
        this.nic = nic;
        this.address = address;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Gurdian{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", netWorth=" + netWorth +
                ", nic='" + nic + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
