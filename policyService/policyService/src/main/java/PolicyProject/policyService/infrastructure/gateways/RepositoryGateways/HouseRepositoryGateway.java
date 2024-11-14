package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;


import PolicyProject.policyService.application.gateways.HouseGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.repository.HouseRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.LicensePlateRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseRepositoryGateway implements HouseGateway {

    private final HouseRepository houseRepository;

    @Override
    public House getWCustomer(House house) {
        if (house == null || house.getBuilding() == null) {
            throw new IllegalArgumentException("House veya Build alanı null olamaz");
        }
        try {
            return houseRepository.findHouseByDetails
                    (house.getBuilding().getAddress().getNeighborhood(),
                            house.getBuilding().getAddress().getDistrict(),
                            house.getBuilding().getAddress().getCity(),
                            house.getBuilding().getApartmentNumber(),
                            house.getNumber());
        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        }
    }

    @Override
    public House get(String plate) {
        return null;
    }

   // @Override
    public House get(Building building, int number) {
//        try {
//            return houseRepository.findHouseByAddressAndApartmentNumber(building.ge);
//        } catch (Exception e) {
//            throw new RuntimeException("Error", e);
//        }
        return null;
    }

}

