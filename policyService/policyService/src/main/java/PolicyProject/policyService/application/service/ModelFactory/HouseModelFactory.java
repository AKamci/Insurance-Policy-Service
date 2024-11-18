package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.HouseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseModelFactory {

    public static HouseModel createHouseModelWithHouseId(Long houseId) {
        return new HouseModel(houseId, null, null, null, null, null, null);
    }

    public static HouseModel createNewHouseModelFromExisting(HouseModel houseModel, Long amount) {
        return new HouseModel(
                houseModel.id(),
                houseModel.number(),
                houseModel.squareMeters(),
                houseModel.customer(),
                houseModel.building(),
                houseModel.tckn(),
                amount
        );
    }
}

