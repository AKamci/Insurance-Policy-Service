package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.PersonalHealthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalHealthModelFactory {
    public static PersonalHealthModel createPersonalHealthModelWithHealthIdAndTckn(Long personalHealthId, String tckn) {
        return new PersonalHealthModel(
                personalHealthId,
                tckn,
                null, null, null, null,
                null, null, null, null,
                null, null, null, null, null
        );
    }

    public static PersonalHealthModel createCalculatedPersonalHealthModel(
            PersonalHealthModel personalHealthModel,
            Integer coverageCode
    ) {
        return new PersonalHealthModel(
                personalHealthModel.id(),
                personalHealthModel.tckn(),
                personalHealthModel.coverage(),
                coverageCode,
                personalHealthModel.customer(),
                personalHealthModel.height(),
                personalHealthModel.weight(),
                personalHealthModel.bmi(),
                personalHealthModel.bloodType(),
                personalHealthModel.alcoholConsumption(),
                personalHealthModel.smokeConsumption(),
                personalHealthModel.isPregnant(),
                personalHealthModel.hasDisability(),
                personalHealthModel.hasPreviousSurgeries(),
                0L
        );
    }


    public static PersonalHealthModel createPersonalHealthModelWithAmount(
            PersonalHealthModel personalHealthModel,
            Long amount
    ) {
        return new PersonalHealthModel(
                personalHealthModel.id(),
                personalHealthModel.tckn(),
                personalHealthModel.coverage(),
                personalHealthModel.coverageCode(),
                personalHealthModel.customer(),
                personalHealthModel.height(),
                personalHealthModel.weight(),
                personalHealthModel.bmi(),
                personalHealthModel.bloodType(),
                personalHealthModel.alcoholConsumption(),
                personalHealthModel.smokeConsumption(),
                personalHealthModel.isPregnant(),
                personalHealthModel.hasDisability(),
                personalHealthModel.hasPreviousSurgeries(),
                amount
        );
    }




}
