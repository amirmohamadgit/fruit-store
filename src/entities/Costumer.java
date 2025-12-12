package entities;

public class Costumer extends BaseEntity {

    public static final String TABLE_NAME = "costumers";

    public static final String NAME_COLUMN = "name";
    public static final String PHONE_NUMBER_COLUMN = "phone_number";
    public static final String ADDRESS_COLUMN = "address";


    private String name;
    private String phoneNumber;
    private String address;

    public Costumer(String name, String phoneNumber, String address) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Costumer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

