package BookMyStayApp.domain;


public class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000);
    }

    public void displayDetails() {
        System.out.println(getRoomType() + " | Beds: " + getBeds() + " | ₹" + getPrice());
    }
}