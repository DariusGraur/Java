package PharmacyApplication2;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Medicine {
    private String medicineName;
    private double medicinePrice;
    private String unit;
    private int unitCode;
    private String productionCompany;
    private boolean withOrWithoutPrescription;
    private String expirationDate;

    private int quantity;
    private String settlementType;

    public Medicine(String medicineName, double medicinePrice, int unitCode, int quantity, String productionCompany, boolean withOrWithoutPrescription, String expirationDate, String settlementType) {
        this.medicineName = medicineName;
        this.medicinePrice = medicinePrice;
        this.unitCode = unitCode;
        this.unit = setUnit();
        this.productionCompany = productionCompany;
        this.withOrWithoutPrescription = withOrWithoutPrescription;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.settlementType = settlementType;

    }

    public Medicine(String medicineName, int quantity) {
        this.medicineName = medicineName;
        this.quantity = quantity;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public String setUnit() {
        if (this.unitCode == 1) {
            return "Tablets";
        } else if (this.unitCode == 2) {
            return "Syrup";
        } else if (this.unitCode == 3) {
            return "Vial";
        }
        return "This option is not valid, please choose 1, 2 or 3";
    }

    public String getUnit() {
        return unit;
    }

    public int getUnitCode() {
        return unitCode;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setMedicinePrice(double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
    public double getMedicinePrice(){
        return medicinePrice;
    }

    public void setSettlementType(String settlementType) {
        if (settlementType.equals("100")) {
            this.medicinePrice = 0.00;
            System.out.println("Total state settlement");
        } else if (settlementType.equals("50")) {
            this.medicinePrice = medicinePrice * 0.5;
            System.out.println("Partial state settlement");
        } else if (settlementType.equals("0")) {
            System.out.println("Fully paid by customer");
        } else {
            System.out.println("This option is not valid, please choose 0, 50 or 100");
        }

    }

    public String getSettlementType() {
        return settlementType;
    }

    public boolean verifyExpirationDate() {
        DateTimeFormatter dF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();

        if (dF.format(today).compareTo(this.expirationDate) < 0 && dF.format(today).compareTo(this.expirationDate) != 0) {

            return true;

        } else {

            return false;
        }


    }


    @Override
    public String toString() {
        return String.format("%-16s= %17s %-32s=  %7s %-21s= %10s %-16s= %17s",
                "{medicineName ", medicineName, "  •   medicinePrice ", medicinePrice,
                "  •   unitCode ", unitCode, "  •   unit ", unit + "\n") +
                String.format("%-16s= %17s %-26s=  %7s %-13s= %10s %-16s= %17s",
                        "productionCompany ", productionCompany, "  •   withOrWithoutPrescription ", withOrWithoutPrescription,
                        "  •   expirationDate ", expirationDate, "  •   quantity ", quantity + "}\n");

    }


}
