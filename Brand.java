package PharmacyApplication2;
import java.util.HashMap;
import java.util.List;
public class Brand {
    private List<Pharmacy> pharmacyList;

    public void setPharmacyList(List<Pharmacy> pharmacyList) {

        this.pharmacyList = pharmacyList;
    }


    public void displayAllEmployees() {
        int allEmployees = 0;
        for (Pharmacy element : this.pharmacyList) {
            allEmployees += element.getNumberOfEmployee();
        }
        System.out.println("\u001B[34mThe total number of employees\u001B[0m: " + allEmployees);
    }

    public void showTheMedicineAfterCity(String city) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Pharmacy element : this.pharmacyList) {
            if (element.getCity().equalsIgnoreCase(city)) {
                for (Medicine medicine : element.getMedicineStock()) {
                    if (map.containsKey(medicine.getMedicineName())) {
                        int newQuantity = medicine.getQuantity() + map.get(medicine.getMedicineName());
                        map.put(medicine.getMedicineName(), newQuantity);
                    } else {
                        map.put(medicine.getMedicineName(), medicine.getQuantity());
                    }
                }
            }

        }
        System.out.println(map);
    }

    public void acceptsCardPayment() {
        for (Pharmacy element : this.pharmacyList) {
            if (element.getCardPayment()) {
                System.out.println(element.getPharmacyName() + " " + element.getCity());
            }

        }
    }

    public void displayNonStopPharmacies() {
        for (Pharmacy element : this.pharmacyList) {
            if (element.getSchedule().equalsIgnoreCase("nonstop")) {
                System.out.println(element.getPharmacyName() + " - " + element.getCity());
            }

        }
    }

    public void showEmailAndPhone() {
        for (Pharmacy element : this.pharmacyList) {
            if (element.getPharmacyType().equalsIgnoreCase("online") || element.getPharmacyType().equalsIgnoreCase("hybrid")) {
                System.out.println(element.getEmail() + "\n" + element.getPhoneNumber());
            } else {
                System.out.println("There are no such type of pharmacies. ");
            }
        }
    }
}


