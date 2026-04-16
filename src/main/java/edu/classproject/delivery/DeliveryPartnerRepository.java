package edu.classproject.delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryPartnerRepository {
    void save(DeliveryPartner partner);
    Optional<DeliveryPartner> findById(String id);
    List<DeliveryPartner> findAllAvailable();
}