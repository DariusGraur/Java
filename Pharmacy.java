package PharmacyApplication2;
import java.util.*;

public class Pharmacy {
    private String pharmacyName;
    private int numberOfEmployee;
    private Set<Medicine> medicineStock;
    private String city;
    private boolean casContract;
    private boolean cardPayment;
    private String schedule;
    private String pharmacyType;
    private boolean discountWithPurchasingCard;
    private String phoneNumber;
    private String email;


    // Constructor
    public Pharmacy(String pharmacyName) {

        this.pharmacyName = pharmacyName;
    }

    // Settere si Gettere
    public void setMedicineStock(Set<Medicine> medicineStock) {
        this.medicineStock = medicineStock;
    }

    public Set<Medicine> getMedicineStock() {
        return medicineStock;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setNumberOfEmployee(int numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public int getNumberOfEmployee() {
        return numberOfEmployee;
    }


    public boolean hasCasContract() {
        return casContract;
    }

    public void setCasContract(boolean casContract) {
        this.casContract = casContract;
    }


    public boolean getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(boolean cardPayment) {
        this.cardPayment = cardPayment;
    }

    public String getSchedule() {
        return this.schedule;
    }
    public void getCity(String city){
        this.city=city;
    }


    public void setSchedule(String option) {
        if (option.equals("1")) {
            this.schedule = "NonStop";

        } else if (option.equals("2")) {
            this.schedule = "Monday till Friday";

        } else if (option.equals("3")) {
            this.schedule = "Everyday";

        } else {
            System.out.println("This option is not valid, please choose 1, 2 or 3");
        }

    }

    public String getPharmacyType() {
        return pharmacyType;
    }

    public void setPharmacyType(String pharmacyType) {
        if (pharmacyType.equalsIgnoreCase("online")) {
            this.pharmacyType = pharmacyType;
        } else if (pharmacyType.equalsIgnoreCase("in-store")) {
            this.pharmacyType = pharmacyType;
        } else if (pharmacyType.equalsIgnoreCase("hybrid")) {
            this.pharmacyType = pharmacyType;
        } else {
            System.out.println("This option is not valid, please choose online, in-store or hybrid");
        }

    }

    public boolean getDiscountWithPurchasingCard() {
        return discountWithPurchasingCard;
    }

    public void setDiscountWithPurchasingCard(boolean discountWithPurchasingCard) {
        this.discountWithPurchasingCard = discountWithPurchasingCard;
    }

    public String getPhoneNumber() {
        return String.format("Phone number : " + phoneNumber);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkHasOnlyDigits(phoneNumber) && phoneNumber.length() == 10) {
            this.phoneNumber = phoneNumber.substring(0, 3)
                    + "-"
                    + phoneNumber.substring(3, 6)
                    + "-"
                    + phoneNumber.substring(6);
        } else {
            System.out.println("The number is not valid");
        }
    }


    public String getEmail() {
        return String.format("Email address: " + email);
    }


    public void setEmailAddress(String emailAddress) {
        String[] email = emailAddress.split("@");
        if (email.length == 2 && emailAddress.contains(".com") || emailAddress.contains(".ro")) {
            this.email = emailAddress;
        } else {
            System.out.println("Invalid email address");
        }
    }


    public boolean checkHasOnlyDigits(String value) {
        boolean check = true;
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                check = false;
            }

        }
        return check;

    }


// metode

    public void placeOrder(List<Medicine> orderList, boolean discountWithPurchasingCard) {
        for (int i = 0; i < orderList.size(); i++) {
            for (Medicine element : medicineStock) {
                if (orderList.get(i).getMedicineName().equals(element.getMedicineName())) {
                    if (orderList.get(i).getQuantity() <= element.getQuantity() && discountWithPurchasingCard) {
                        element.setMedicinePrice(element.getMedicinePrice() * 0.9);
                        element.setQuantity(element.getQuantity() - orderList.get(i).getQuantity());
                        System.out.println("  " +element);
                    } else if (orderList.get(i).getQuantity() <= element.getQuantity() && !discountWithPurchasingCard) {
                        element.setQuantity(element.getQuantity() - orderList.get(i).getQuantity());
                        System.out.println("  " + element);
                    } else if (orderList.get(i).getQuantity() > element.getQuantity()) {
                        System.out.println(element.getMedicineName() + " is out of stock ");
                    }
                }
            }

        }
    }
    public void addMedicine(List<Medicine> medicineList) {
        for (int i = 0; i < medicineList.size(); i++) {
            for (Medicine currentElement : medicineStock) {
                if (medicineList.get(i).getMedicineName().equalsIgnoreCase(currentElement.getMedicineName())) {
                    currentElement.setQuantity(currentElement.getQuantity() + medicineList.get(i).getQuantity());
                    medicineList.remove(medicineList.get(i));
                }
            }
        }
        medicineStock.addAll(medicineList);
    }

    public int totalStockOfMedicine() {
        int result = 0;
        for (Medicine element : medicineStock) {
            result += element.getQuantity();
        }
        return result;
    }


    public void verifyIfMedicineIsExpired() {
        List<Medicine> toRemove = new ArrayList<>();
        for (Medicine element : this.medicineStock) {
            if (!element.verifyExpirationDate()) {
                toRemove.add(element);
            }
        }
        System.out.println(toRemove);
        medicineStock.removeAll(toRemove);


    }

    public void settlementCategory(Set<Medicine> medicineStock) {
        List<Medicine> withSettlement = new ArrayList<>();
        List<Medicine> withoutSettlement = new ArrayList<>();
        for (Medicine element : medicineStock) {
            if (element.getSettlementType().equalsIgnoreCase("100") || element.getSettlementType().equalsIgnoreCase("50")) {
                withSettlement.add(element);
            } else if (element.getSettlementType().equalsIgnoreCase("0")) {
                withoutSettlement.add(element);
            }

        }
        System.out.println("\n" + getPharmacyName() + "\n" + getCity());
        System.out.println("Medicine with settlement: \n " + withSettlement);
        System.out.println("\nMedicine without settlement: \n " + withoutSettlement);
    }


    public void searchMedicine(String name) {
        boolean checkIfExist = false;
        for (Medicine element : this.medicineStock) {
            if (element.getMedicineName().equalsIgnoreCase(name)) {
                System.out.println(element.getMedicineName());
                break;
            } else {
                checkIfExist = true;

            }
        }
        if (checkIfExist) {
            System.out.println("This medicine doesn't exist");
        }

    }

    public void showMedicineAfterUnitType(int unit) {
        List<String> unitList = new ArrayList<>();
        for (Medicine element : this.medicineStock) {
            if (element.getUnitCode() == unit) {

                unitList.add(element.getMedicineName() + "-" + element.getUnit());
            }
        }

        System.out.println(unitList);
    }

    public void showProductionCompany() {
        for (Medicine element : this.medicineStock) {
            System.out.println(element.getMedicineName() + "-" + element.getProductionCompany());
        }
    }

    @Override
    public String toString() {
        return "Pharmacy" + pharmacyName;
    }
}
