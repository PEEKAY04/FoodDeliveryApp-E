package edu.classproject.delivery;

import java.util.UUID;

public class DeliveryPartnerFactory {
    public DeliveryPartner createPartner(String name) {
        String uniqueId = "DP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        // Partners start as offline (unavailable) by default
        return new DeliveryPartner(uniqueId, name, false); 
    }
}