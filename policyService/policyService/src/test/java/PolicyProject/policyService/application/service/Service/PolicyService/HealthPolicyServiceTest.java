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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

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
        when(executeHealthPolicy.executeGet(healthPolicyModel)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.get(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeGet(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidHealthPolicyModel_ReturnsListGetHealthPolicyResponse() {
        when(executeHealthPolicy.executeGetList(healthPolicyModel)).thenReturn(List.of(healthPolicyModel));
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.getList(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeGetList(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidHealthPolicyModel_ReturnsCreateHealthPolicyResponse() {
        when(executeHealthPolicy.executeCreate(healthPolicyModel)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.create(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeCreate(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidHealthPolicyModel_ReturnsUpdateHealthPolicyResponse() {
        when(executeHealthPolicy.executeUpdate(healthPolicyModel)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.update(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeUpdate(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidHealthPolicyModel_ReturnsDeleteHealthPolicyResponse() {
        when(executeHealthPolicy.executeDelete(healthPolicyModel)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.delete(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).executeDelete(healthPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testAccept_ValidHealthPolicyModel_ReturnsSetStateHealthPolicyResponse() {
        when(executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.ACTIVATE)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.accept(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).changeCarPolicyState(healthPolicyModel,PolicyEvent.ACTIVATE);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }
    @Test
    void testReject_ValidHealthPolicyModel_ReturnsSetStateHealthPolicyResponse() {
        when(executeHealthPolicy.changeCarPolicyState(healthPolicyModel, PolicyEvent.CANCEL)).thenReturn(healthPolicyModel);
        doNothing().when(objectValidation).validateModel(healthPolicyModel, "healthPolicyModel");

        healthPolicyService.reject(healthPolicyModel);

        verify(executeHealthPolicy, times(1)).changeCarPolicyState(healthPolicyModel,PolicyEvent.CANCEL);
        verify(objectValidation, times(1)).validateModel(any(HealthPolicyModel.class), any(String.class));
    }

    @Test
    void testTotal_ValidHealthPolicyModel_ReturnsINT() {
        when(executeHealthPolicy.executeGetTotalRecord()).thenReturn(5);

        int totalRecord = healthPolicyService.getTotalRecord();

        assertEquals(5, totalRecord);
        verify(executeHealthPolicy, times(1)).executeGetTotalRecord();
    }






}
