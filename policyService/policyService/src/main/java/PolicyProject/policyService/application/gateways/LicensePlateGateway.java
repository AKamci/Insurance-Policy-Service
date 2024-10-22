package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

import java.util.concurrent.CompletableFuture;

public interface LicensePlateGateway {

    CompletableFuture<LicensePlate> get(LicensePlate licensePlate);

}
