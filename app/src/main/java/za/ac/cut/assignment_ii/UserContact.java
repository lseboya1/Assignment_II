package za.ac.cut.assignment_ii;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserContact {

//    public static abstract class NewUserInfo{
//
//        public static final String USER_LOCATION = "user_location";
//        public static final String USER_NAME = "user_name";
//        public static final String USER_MOB = "user_mob";
//        public static final String USER_ADDRESS = "user_address";
//        public static final String TABLE_NAME = "user_info";
//    }

    String name;
    String location;
    String phoneNumber;
    String address;
    String contactUID;

    public String getContactUID() {
        return contactUID;
    }

    public void setContactUID(String contactUID) {
        this.contactUID = contactUID;
    }

    public Map<String , String> userContact;

    public UserContact() {

    }

    public UserContact(String contactUID,String phoneNumber,String location,  String address) {
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.contactUID = contactUID;
    }

    public UserContact(String name, String location, String phoneNumber, String address, String contactUID) {
        this.name = name;
        this.location = location;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.contactUID = contactUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result = new HashMap<>();

        result.put("name",name);
        result.put("location",location);
        result.put("phoneNumber",phoneNumber);
        result.put("address",address);
        result.put("contactUID",contactUID);
        return result;
    }
}
