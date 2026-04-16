package edu.classproject.delivery;

public class DeliveryPartner {
    private final String partnerId;
    private final String name;
    private boolean available;

    public DeliveryPartner(String partnerId, String name, boolean available) {
        this.partnerId = partnerId;
        this.name = name;
        this.available = available;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}