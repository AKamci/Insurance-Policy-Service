package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteCarPolicy;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteHealthPolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.PolicyModel.HealthPolicyModel;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class HealthPolicyServiceTest {


    @Mock
    private ExecuteHealthPolicy executeHealthPolicy;

    @Mock
    private HealthPolicyModel healthPolicyModel;

    @Mock
    private HealthPolicyModel healthPolicyModel2;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private HealthPolicyMapper healthPolicyMapper;

    @InjectMocks
    private HealthPolicyService healthPolicyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testGet_ValidHealthPolicyModel_ReturnsGetHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToGetHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.executeGet(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeGet(healthPolicyModel);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToGetHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidHealthPolicyModel_ReturnsListGetHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelListToGetHealthPolicyResponseList(List.of(healthPolicyModel, healthPolicyModel2));
        executeHealthPolicy.executeGetList(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeGetList(healthPolicyModel);
        verify(healthPolicyMapper, times(1)).healthPolicyModelListToGetHealthPolicyResponseList(List.of(healthPolicyModel, healthPolicyModel2));
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidHealthPolicyModel_ReturnsCreateHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToCreateHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.executeCreate(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeCreate(healthPolicyModel);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToCreateHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidHealthPolicyModel_ReturnsUpdateHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToUpdateHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.executeUpdate(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeUpdate(healthPolicyModel);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToUpdateHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidHealthPolicyModel_ReturnsDeleteHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToDeleteHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.executeDelete(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeDelete(healthPolicyModel);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToDeleteHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testAccept_ValidHealthPolicyModel_ReturnsSetStateHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToSetStateHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.ACTIVATE);

        verify(executeHealthPolicy, times(1)).changeCarPolicyState(healthPolicyModel,PolicyEvent.ACTIVATE);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToSetStateHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }
    @Test
    void testReject_ValidHealthPolicyModel_ReturnsSetStateHealthPolicyResponse() {
        objectValidation.validateModel(healthPolicyModel, "healthPolicyModel");
        healthPolicyMapper.healthPolicyModelToSetStateHealthPolicyResponse(healthPolicyModel);
        executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.CANCEL);

        verify(executeHealthPolicy, times(1)).changeCarPolicyState(healthPolicyModel,PolicyEvent.CANCEL);
        verify(healthPolicyMapper, times(1)).healthPolicyModelToSetStateHealthPolicyResponse(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testTotal_ValidHealthPolicyModel_ReturnsINT() {
        executeHealthPolicy.executeGetTotalRecord();
        verify(executeHealthPolicy, times(1)).executeGetTotalRecord();
    }






}
