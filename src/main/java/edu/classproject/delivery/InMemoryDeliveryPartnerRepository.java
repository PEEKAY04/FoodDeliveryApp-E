package edu.classproject.delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryDeliveryPartnerRepository implements DeliveryPartnerRepository {
    private final Map<String, DeliveryPartner> store = new HashMap<>();

    @Override
    public void save(DeliveryPartner partner) {
        store.put(partner.getPartnerId(), partner);
    }

    @Override
    public Optional<DeliveryPartner> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<DeliveryPartner> findAllAvailable() {
        return store.values().stream()
                .filter(DeliveryPartner::isAvailable)
                .collect(Collectors.toList());
    }
}