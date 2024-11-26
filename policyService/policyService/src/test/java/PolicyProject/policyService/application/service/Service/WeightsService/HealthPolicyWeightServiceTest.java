package PolicyProject.policyService.application.service.Service.WeightsService;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteHealthPolicyWeight;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class HealthPolicyWeightServiceTest {

    @Mock
    private ExecuteHealthPolicyWeight executeWeight;
    @Mock
    private ObjectValidation objectValidation;
    @Mock
    private CarPolicyWeightsMapper carPolicyWeightsMapper;
    @Mock
    private WeightsModel weightsModel;
    @Mock
    private WeightsModel weightsModel2;

    @InjectMocks
    private HealthPolicyWeightService healthPolicyWeightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate_ValidWeightsModel_ReturnsCreateWeightResponse() {
        when(executeWeight.executeCreate(weightsModel)).thenReturn(weightsModel);
        doNothing().when(objectValidation).validateModel(weightsModel, "weightsModel");

        healthPolicyWeightService.create(weightsModel);

        verify(executeWeight, times(1)).executeCreate(weightsModel);
        verify(objectValidation, times(1)).validateModel(weightsModel, "weightsModel");
    }

    @Test
    void testUpdate_ValidWeightsModel_ReturnsUpdateWeightResponse() {
        when(executeWeight.executeUpdate(weightsModel)).thenReturn(weightsModel);
        doNothing().when(objectValidation).validateModel(weightsModel, "weightsModel");

        healthPolicyWeightService.update(weightsModel);

        verify(executeWeight, times(1)).executeUpdate(weightsModel);
        verify(objectValidation, times(1)).validateModel(weightsModel, "weightsModel");
    }

    @Test
    void testDelete_ValidWeightsModel_ReturnsDeleteWeightResponse() {
        when(executeWeight.executeDelete(weightsModel)).thenReturn(weightsModel);
        doNothing().when(objectValidation).validateModel(weightsModel, "weightsModel");

        healthPolicyWeightService.delete(weightsModel);

        verify(executeWeight, times(1)).executeDelete(weightsModel);
        verify(objectValidation, times(1)).validateModel(weightsModel, "weightsModel");
    }

    @Test
    void testGet_ValidWeightsModel_ReturnsGetWeightResponse() {
        when(executeWeight.executeGet(weightsModel)).thenReturn(weightsModel);
        doNothing().when(objectValidation).validateModel(weightsModel, "weightsModel");

        healthPolicyWeightService.get(weightsModel);

        verify(executeWeight, times(1)).executeGet(weightsModel);
        verify(objectValidation, times(1)).validateModel(weightsModel, "weightsModel");
    }

    @Test
    void testGetList_ValidWeightsModel_ReturnsListGetWeightResponse() {
        when(executeWeight.executeGetList()).thenReturn(List.of(weightsModel));

        healthPolicyWeightService.getList();

        verify(executeWeight, times(1)).executeGetList();
    }

    @Test
    void testUpdateList_ValidWeightsModel_ReturnsListUpdateWeightResponse() {
        when(executeWeight.executeUpdateList(List.of(weightsModel))).thenReturn(List.of(weightsModel));
        doNothing().when(objectValidation).validateModelList(List.of(weightsModel), "weightsModelList");

        healthPolicyWeightService.updateList(List.of(weightsModel));

        verify(executeWeight, times(1)).executeUpdateList(List.of(weightsModel));
        verify(objectValidation, times(1)).validateModelList(List.of(weightsModel), "weightsModelList");
    }
}