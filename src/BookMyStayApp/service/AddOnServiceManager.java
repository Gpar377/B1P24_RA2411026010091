package BookMyStayApp.service;

import java.util.*;

public class AddOnServiceManager {

    private Map<String, List<AddOnService>> map = new HashMap<>();

    public void addService(String id, AddOnService s) {
        map.computeIfAbsent(id, k -> new ArrayList<>()).add(s);
    }

    public void display(String id) {
        List<AddOnService> list = map.get(id);

        double total = 0;

        for (AddOnService s : list) {
            s.display();
            total += s.getPrice();
        }

        System.out.println("Total: ₹" + total);
    }
}