package edu.classproject.delivery;

import java.util.List;

public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {
    private final DeliveryPartnerRepository repository;
    private final DeliveryPartnerFactory factory;

    // Constructor injection (Dependency Inversion Principle)
    public DeliveryPartnerServiceImpl(DeliveryPartnerRepository repository, DeliveryPartnerFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }

    @Override
    public DeliveryPartner register(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Partner name cannot be empty");
        }
        DeliveryPartner newPartner = factory.createPartner(name);
        repository.save(newPartner);
        return newPartner;
    }

    @Override
    public void setAvailability(String partnerId, boolean available) {
        DeliveryPartner partner = repository.findById(partnerId)
                .orElseThrow(() -> new PartnerNotFoundException("Partner not found: " + partnerId));
        
        partner.setAvailable(available);
        repository.save(partner);
    }

    @Override
    public List<DeliveryPartner> getAvailablePartners() {
        return repository.findAllAvailable();
    }
}