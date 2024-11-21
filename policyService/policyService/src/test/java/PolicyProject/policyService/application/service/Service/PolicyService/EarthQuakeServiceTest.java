package PolicyProject.policyService.application.service.Service.PolicyService;



import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteEarthQuakePolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.model.EarthQuakeModel;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EarthQuakeServiceTest {

    @Mock
    private ExecuteEarthQuakePolicy executeEarthQuakePolicy;

    @Mock
    private EarthQuakeModel earthQuakePolicyModel;

    @Mock
    private EarthQuakeModel earthQuakePolicyModel2;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private EarthQuakeMapper earthQuakePolicyMapper;

    @InjectMocks
    private EarthQuakeService earthQuakeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate_ValidEarthQuakePolicyModel_ReturnsCreateEarthQuakePolicyResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToCreateEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.executeCreate(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeCreate(earthQuakePolicyModel);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToCreateEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidEarthQuakePolicyModel_ReturnsUpdateEarthQuakePolicyResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToUpdateEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.executeUpdate(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeUpdate(earthQuakePolicyModel);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToUpdateEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidEarthQuakePolicyModel_ReturnsListGetEarthQuakePolicyResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelListToGetEarthQuakeResponseList(List.of(earthQuakePolicyModel, earthQuakePolicyModel2));
        executeEarthQuakePolicy.executeGetList(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeGetList(earthQuakePolicyModel);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelListToGetEarthQuakeResponseList(List.of(earthQuakePolicyModel, earthQuakePolicyModel2));
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidEarthQuakePolicyModel_ReturnsDeleteEarthQuakePolicyResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToDeleteEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.executeDelete(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeDelete(earthQuakePolicyModel);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToDeleteEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testGet_ValidEarthQuakePolicyModel_ReturnsGetEarthQuakePolicyResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToGetEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.executeGet(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeGet(earthQuakePolicyModel);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToGetEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testAccept_ValidEarthQuakePolicyModel_ReturnsSetEarthQuakePolicyStatusResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToSetStateEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.ACTIVATE);

        verify(executeEarthQuakePolicy, times(1)).changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.ACTIVATE);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToSetStateEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testReject_ValidEarthQuakePolicyModel_ReturnsSetEarthQuakePolicyStatusResponse() {
        objectValidation.validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");
        earthQuakePolicyMapper.earthQuakeModelToSetStateEarthQuakeResponse(earthQuakePolicyModel);
        executeEarthQuakePolicy.changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.CANCEL);

        verify(executeEarthQuakePolicy, times(1)).changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.CANCEL);
        verify(earthQuakePolicyMapper, times(1)).earthQuakeModelToSetStateEarthQuakeResponse(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testTotalRecord_ValidEarthQuakePolicyModel_ReturnsINT() {
        executeEarthQuakePolicy.executeGetTotalRecord();
        verify(executeEarthQuakePolicy, times(1)).executeGetTotalRecord();
    }
}
