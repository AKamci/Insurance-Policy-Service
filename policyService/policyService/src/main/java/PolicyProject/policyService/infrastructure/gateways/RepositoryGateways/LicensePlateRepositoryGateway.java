package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;


import PolicyProject.policyService.application.gateways.LicensePlateGateway;
import PolicyProject.policyService.infrastructure.exception.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.repository.LicensePlateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class LicensePlateRepositoryGateway implements LicensePlateGateway {
    private final LicensePlateRepository licensePlateRepository;



    @Override
   // @Cacheable(value = "customerCache", key = "#licensePlate != null ? #licensePlate.plate : 'unknown'")
    public LicensePlate getWCustomer(LicensePlate licensePlate) {
        if (licensePlate == null || licensePlate.getPlate() == null) {
            throw new IllegalArgumentException("LicensePlate veya plate alanı null olamaz");
        }
        try {
            return licensePlateRepository.findByPlate(licensePlate.getPlate());
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }
    public LicensePlate get(String plate) {
        try {
            return licensePlateRepository.findByPlate(plate);
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }



}
