package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy.LicensePlateService;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteCarPolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.UpdateCarPolicyResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CarPolicyServiceTest {


    @Mock
    private ExecuteCarPolicy executeCarPolicy;

    @Mock
    private CarPolicyModel carPolicyModel;

    @Mock
    private CarPolicyModel carPolicyModel2;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private CarPolicyMapper carPolicyMapper;

    @InjectMocks
    private CarPolicyService carPolicyService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreate_ValidCarPolicyModel_ReturnsCreateCarPolicyResponse() {
        when(executeCarPolicy.executeCreate(carPolicyModel)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.create(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeCreate(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    ///  Test update
    @Test
    void testUpdate_ValidCarPolicyModel_ReturnsUpdateCarPolicyResponse() {
        when(executeCarPolicy.executeUpdate(carPolicyModel)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.update(carPolicyModel);

        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
        verify(executeCarPolicy).executeUpdate(carPolicyModel);
    }

    @Test
    void testGetList_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        when(executeCarPolicy.executeGetList(carPolicyModel)).thenReturn(List.of(carPolicyModel, carPolicyModel2));
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.getList(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGetList(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testDelete_ValidCarPolicyModel_ReturnsDeleteCarPolicyResponse() {
        when(executeCarPolicy.executeDelete(carPolicyModel)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.delete(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeDelete(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testGet_ValidCarPolicyModel_ReturnsGetCarPolicyResponse() {
        when(executeCarPolicy.executeGet(carPolicyModel)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.get(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGet(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testGetByPlate_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        when(executeCarPolicy.executeGetWPlate(carPolicyModel)).thenReturn(List.of(carPolicyModel));
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.getByPlate(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGetWPlate(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testGetWPolicy_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        when(executeCarPolicy.executeGet_wPolicy(carPolicyModel)).thenReturn(List.of(carPolicyModel));
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.get(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGet_wPolicy(carPolicyModel);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testAccept_ValidCarPolicyModel_ReturnsSetCarPolicyStatusResponse() {
        when(executeCarPolicy.changeCarPolicyState(carPolicyModel,PolicyEvent.ACTIVATE)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.acceptCarPolicy(carPolicyModel);

        verify(executeCarPolicy, times(1)).changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testReject_ValidCarPolicyModel_ReturnsSetCarPolicyStatusResponse() {
        when(executeCarPolicy.changeCarPolicyState(carPolicyModel,PolicyEvent.CANCEL)).thenReturn(carPolicyModel);
        doNothing().when(objectValidation).validateModel(carPolicyModel, "carPolicyModel");

        carPolicyService.rejectCarPolicy(carPolicyModel);

        verify(executeCarPolicy, times(1)).changeCarPolicyState(carPolicyModel, PolicyEvent.CANCEL);
        verify(objectValidation).validateModel(carPolicyModel, "carPolicyModel");
    }

    @Test
    void testTotalRecord_ValidCarPolicyModel_ReturnsINT() {
        when(executeCarPolicy.executeGetTotalRecord()).thenReturn(5);
        carPolicyService.getTotalRecord();
        verify(executeCarPolicy, times(1)).executeGetTotalRecord();
    }
}
