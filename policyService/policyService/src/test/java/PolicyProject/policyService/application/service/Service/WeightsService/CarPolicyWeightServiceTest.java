package PolicyProject.policyService.application.service.Service.WeightsService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteCarPolicyWeight;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarPolicyWeightServiceTest {

    @Mock
    private ExecuteCarPolicyWeight executeWeight;
    @Mock
    private ObjectValidation objectValidation;
    @Mock
    private CarPolicyWeightsMapper carPolicyWeightsMapper;
    @Mock
    private WeightsModel weightsModel;
    @Mock
    private WeightsModel weightsModel2;

    @InjectMocks
    private CarPolicyWeightService carPolicyWeightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet_ValidWeightsModel_ReturnsGetWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        carPolicyWeightsMapper.WeightsModelToGetWeightResponse(weightsModel);
        executeWeight.executeGet(weightsModel);

        verify(executeWeight,times(1)).executeGet(weightsModel);
        verify(carPolicyWeightsMapper,times(1)).WeightsModelToGetWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }
    @Test
    void testUpdateList_ValidWeightsModel_ReturnsListUpdateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        carPolicyWeightsMapper.WeightsModelListToUpdateWeightResponseList(List.of(weightsModel,weightsModel2));
        executeWeight.executeUpdateList(List.of(weightsModel,weightsModel2));

        verify(executeWeight,times(1)).executeUpdateList(List.of(weightsModel,weightsModel2));
        verify(carPolicyWeightsMapper,times(1)).WeightsModelListToUpdateWeightResponseList(List.of(weightsModel,weightsModel2));
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }
    @Test
    void testGetList_ValidWeightsModel_ReturnsListGetWeightResponse() {
        carPolicyWeightsMapper.WeightsModelListToGetWeightResponse(List.of(weightsModel,weightsModel2));
        executeWeight.executeGetList();

        verify(executeWeight,times(1)).executeGetList();
        verify(carPolicyWeightsMapper,times(1)).WeightsModelListToGetWeightResponse(List.of(weightsModel,weightsModel2));
    }

    @Test
    void testDelete_ValidWeightsModel_ReturnsDeleteWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        carPolicyWeightsMapper.WeightsModelToDeleteWeightResponse(weightsModel);
        executeWeight.executeDelete(weightsModel);

        verify(executeWeight,times(1)).executeDelete(weightsModel);
        verify(carPolicyWeightsMapper,times(1)).WeightsModelToDeleteWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidWeightsModel_ReturnsUpdateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        carPolicyWeightsMapper.WeightsModelToUpdateWeightResponse(weightsModel);
        executeWeight.executeUpdate(weightsModel);

        verify(executeWeight,times(1)).executeUpdate(weightsModel);
        verify(carPolicyWeightsMapper,times(1)).WeightsModelToUpdateWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidWeightsModel_ReturnsCreateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        carPolicyWeightsMapper.WeightsModelToCreateWeightResponse(weightsModel);
        executeWeight.executeCreate(weightsModel);

        verify(executeWeight,times(1)).executeCreate(weightsModel);
        verify(carPolicyWeightsMapper,times(1)).WeightsModelToCreateWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }



}