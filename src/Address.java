public class Address {
    private String street;
    private String pincode;

    public Address(String street, String pincode) {
        this.street = street;
        this.pincode = pincode;
    }

    public String getStreet() {
        return street;
    }

    public String getPincode() {
        return pincode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}
