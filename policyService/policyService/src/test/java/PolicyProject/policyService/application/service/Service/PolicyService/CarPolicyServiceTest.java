package PolicyProject.policyService.application.service.Service.PolicyService;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy.LicensePlateService;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecutePolicy.ExecuteCarPolicy;
import PolicyProject.policyService.domain.Enums.Enums.PolicyEvent;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.carPolicyModelToCreateCarPolicyResponse(carPolicyModel);
        executeCarPolicy.executeCreate(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeCreate(carPolicyModel);
        verify(carPolicyMapper, times(1)).carPolicyModelToCreateCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidCarPolicyModel_ReturnsUpdateCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel);
        executeCarPolicy.executeUpdate(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeUpdate(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelToUpdateCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelListToGetCarPolicyResponseList(List.of(carPolicyModel, carPolicyModel2));
        executeCarPolicy.executeGetList(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGetList(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelListToGetCarPolicyResponseList(List.of(carPolicyModel, carPolicyModel2));
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidCarPolicyModel_ReturnsDeleteCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel);
        executeCarPolicy.executeDelete(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeDelete(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelToDeleteCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testGet_ValidCarPolicyModel_ReturnsGetCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelToGetCarPolicyResponse(carPolicyModel);
        executeCarPolicy.executeGet(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGet(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelToGetCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testGetByPlate_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelListToGetCarPolicyResponseList(List.of(carPolicyModel, carPolicyModel2));
        executeCarPolicy.executeGetWPlate(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGetWPlate(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelListToGetCarPolicyResponseList(List.of(carPolicyModel, carPolicyModel2));
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testGetWPolicy_ValidCarPolicyModel_ReturnsListGetCarPolicyResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelListToGetCarPolicyResponseList((List.of(carPolicyModel, carPolicyModel2)));
        executeCarPolicy.executeGet_wPolicy(carPolicyModel);

        verify(executeCarPolicy, times(1)).executeGet_wPolicy(carPolicyModel);
        verify(carPolicyMapper, times(1)).cartPolicyModelListToGetCarPolicyResponseList(List.of(carPolicyModel, carPolicyModel2));
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testAccept_ValidCarPolicyModel_ReturnsSetCarPolicyStatusResponse() {

        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelToSetStateCarPolicyResponse(carPolicyModel);
        executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE);

        verify(executeCarPolicy, times(1)).changeCarPolicyState(carPolicyModel, PolicyEvent.ACTIVATE);
        verify(carPolicyMapper, times(1)).cartPolicyModelToSetStateCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testReject_ValidCarPolicyModel_ReturnsSetCarPolicyStatusResponse() {
        objectValidation.validateModel(carPolicyModel, "carPolicyModel");
        carPolicyMapper.cartPolicyModelToSetStateCarPolicyResponse(carPolicyModel);
        executeCarPolicy.changeCarPolicyState(carPolicyModel, PolicyEvent.CANCEL);

        verify(executeCarPolicy, times(1)).changeCarPolicyState(carPolicyModel, PolicyEvent.CANCEL);
        verify(carPolicyMapper, times(1)).cartPolicyModelToSetStateCarPolicyResponse(carPolicyModel);
        verify(objectValidation, times(1)).validateModel(any(CarPolicyModel.class), any(String.class));
    }

    @Test
    void testTotalRecord_ValidCarPolicyModel_ReturnsINT() {
        executeCarPolicy.executeGetTotalRecord();
        verify(executeCarPolicy, times(1)).executeGetTotalRecord();
    }
}
