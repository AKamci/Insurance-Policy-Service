package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.domain.model.CustomerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseModelFactory {

    public static HouseModel createHouseModelWithHouseId(Long houseId) {
        return new HouseModel(houseId, null, null, null, null, null, null, null);
    }

    public static HouseModel createNewHouseModelFromExisting(HouseModel houseModel, Long amount) {
        return new HouseModel(
                houseModel.id(),
                houseModel.coverageCode(),
                houseModel.number(),
                houseModel.squareMeters(),
                houseModel.customer(),
                houseModel.building(),
                houseModel.tckn(),
                amount
        );
    }

    public static HouseModel createNewHouseModelFromExistingWithCoverageCode(HouseModel houseModel, Integer coverageCode) {
        return new HouseModel(
                houseModel.id(),
                coverageCode,
                houseModel.number(),
                houseModel.squareMeters(),
                houseModel.customer(),
                houseModel.building(),
                houseModel.tckn(),
                houseModel.Amount()
        );
    }





}

