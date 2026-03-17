package BookMyStayApp.domain;

import java.io.Serializable;

public class Reservation implements Serializable {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}