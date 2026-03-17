package BookMyStayApp.domain;

public class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }

    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | ₹" + getPrice());
    }
}