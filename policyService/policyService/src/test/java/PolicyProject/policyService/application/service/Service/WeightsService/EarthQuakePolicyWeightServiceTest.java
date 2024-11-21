package PolicyProject.policyService.application.service.Service.WeightsService;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteEarthQuakeWeight;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.EarthQuakeWeightMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


public class EarthQuakePolicyWeightServiceTest {

    @Mock
    private ExecuteEarthQuakeWeight executeWeight;
    @Mock
    private ObjectValidation objectValidation;
    @Mock
    private EarthQuakeWeightMapper earthquakePolicyWeightsMapper;
    @Mock
    private WeightsModel weightsModel;
    @Mock
    private WeightsModel weightsModel2;

    @InjectMocks
    private EarthQuakePolicyWeightService earthQuakePolicyWeightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet_ValidWeightsModel_ReturnsGetWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        earthquakePolicyWeightsMapper.WeightsModelToGetWeightResponse(weightsModel);
        executeWeight.executeGet(weightsModel);

        verify(executeWeight,times(1)).executeGet(weightsModel);
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelToGetWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }
    @Test
    void testUpdateList_ValidWeightsModel_ReturnsListUpdateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        earthquakePolicyWeightsMapper.WeightsModelListToUpdateWeightResponseList(List.of(weightsModel,weightsModel2));
        executeWeight.executeUpdateList(List.of(weightsModel,weightsModel2));

        verify(executeWeight,times(1)).executeUpdateList(List.of(weightsModel,weightsModel2));
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelListToUpdateWeightResponseList(List.of(weightsModel,weightsModel2));
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }
    @Test
    void testGetList_ValidWeightsModel_ReturnsListGetWeightResponse() {
        earthquakePolicyWeightsMapper.WeightsModelListToGetWeightResponse(List.of(weightsModel,weightsModel2));
        executeWeight.executeGetList();

        verify(executeWeight,times(1)).executeGetList();
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelListToGetWeightResponse(List.of(weightsModel,weightsModel2));
    }

    @Test
    void testDelete_ValidWeightsModel_ReturnsDeleteWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        earthquakePolicyWeightsMapper.WeightsModelToDeleteWeightResponse(weightsModel);
        executeWeight.executeDelete(weightsModel);

        verify(executeWeight,times(1)).executeDelete(weightsModel);
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelToDeleteWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidWeightsModel_ReturnsUpdateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        earthquakePolicyWeightsMapper.WeightsModelToUpdateWeightResponse(weightsModel);
        executeWeight.executeUpdate(weightsModel);

        verify(executeWeight,times(1)).executeUpdate(weightsModel);
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelToUpdateWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidWeightsModel_ReturnsCreateWeightResponse() {
        objectValidation.validateModel(weightsModel, "weightsModel");
        earthquakePolicyWeightsMapper.WeightsModelToCreateWeightResponse(weightsModel);
        executeWeight.executeCreate(weightsModel);

        verify(executeWeight,times(1)).executeCreate(weightsModel);
        verify(earthquakePolicyWeightsMapper,times(1)).WeightsModelToCreateWeightResponse(weightsModel);
        verify(objectValidation, times(1)).validateModel(any(WeightsModel.class), any(String.class));
    }
}
