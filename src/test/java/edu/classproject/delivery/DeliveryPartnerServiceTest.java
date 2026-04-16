package edu.classproject.delivery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPartnerServiceTest {

    private DeliveryPartnerService service;
    private DeliveryPartnerRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryDeliveryPartnerRepository();
        DeliveryPartnerFactory factory = new DeliveryPartnerFactory();
        service = new DeliveryPartnerServiceImpl(repository, factory);
    }

    @Test
    void testRegisterAndGetAvailablePartners_HappyPath() {
        // 1. Register a partner
        DeliveryPartner partner1 = service.register("Alice");
        assertNotNull(partner1.getPartnerId());
        assertFalse(partner1.isAvailable(), "New partners should be offline by default");

        // 2. Set availability to true (repeated toggles test)
        service.setAvailability(partner1.getPartnerId(), true);
        
        // 3. Verify only available partners are returned
        List<DeliveryPartner> availablePartners = service.getAvailablePartners();
        assertEquals(1, availablePartners.size());
        assertEquals("Alice", availablePartners.get(0).getName());

        // 4. Toggle back to false
        service.setAvailability(partner1.getPartnerId(), false);
        assertTrue(service.getAvailablePartners().isEmpty());
    }

    @Test
    void testSetAvailability_UnknownPartner_EdgeCase() {
        // Attempting to toggle a partner ID that does not exist should throw our custom exception
        Exception exception = assertThrows(PartnerNotFoundException.class, () -> {
            service.setAvailability("FAKE-ID-999", true);
        });

        assertTrue(exception.getMessage().contains("Partner not found"));
    }

    @Test
    void testRegisterPartner_EmptyName_EdgeCase() {
        // Attempting to register a partner with an empty name should fail
        assertThrows(IllegalArgumentException.class, () -> {
            service.register("");
        });
    }
}