package BookMyStayApp.domain;

public class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }

    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | ₹" + getPrice());
    }
}