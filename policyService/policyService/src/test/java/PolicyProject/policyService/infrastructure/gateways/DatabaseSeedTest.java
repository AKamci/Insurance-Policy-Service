package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.CarRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.LicensePlateRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.AddressRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.BuildingRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.HouseRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.*;

import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.CarPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.CarPolicyWeightsRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DatabaseSeedTest {


@Mock
DatabaseSeed databaseSeedRunner;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private LicensePlateRepository licensePlateRepository;
    @Autowired
    private CoverageRepository coverageRepository;
    @Autowired
    private CarPolicyRepository carPolicyrepository;
    @Autowired
    private CarPolicyWeightsRepository weightsRepository;
    @Autowired
    private EarthQuakeWeightsRepository earthQuakeWeightsRepository;
    @Autowired
    private EarthQuakeRepository earthQuakeRepository;
    @Autowired
    private PersonalHealthRepository personalHealthRepository;
    @Autowired
    private HealthPolicyWeightRepository healthPolicyWeightRepository;
    @Autowired
    private HealthPolicyRepository healthPolicyRepository;


    @Test
    void run() throws Exception {
        databaseSeedRunner.run();

        assertTrue(customerRepository.count() > 0);
        assertTrue(addressRepository.count() > 0);
        assertTrue(buildingRepository.count() > 0);
        assertTrue(houseRepository.count() > 0);
        assertTrue(carRepository.count() > 0);
        assertTrue(licensePlateRepository.count() > 0);
        assertTrue(coverageRepository.count() > 0);
        assertTrue(carPolicyrepository.count() > 0);
        assertTrue(weightsRepository.count() > 0);
        assertTrue(earthQuakeWeightsRepository.count() > 0);
        assertTrue(earthQuakeRepository.count() > 0);
        assertTrue(personalHealthRepository.count() > 0);
        assertTrue(healthPolicyWeightRepository.count() > 0);
        assertTrue(healthPolicyRepository.count() > 0);

    }
}
