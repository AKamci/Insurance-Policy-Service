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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

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
        when(executeEarthQuakePolicy.executeCreate(earthQuakePolicyModel)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.create(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeCreate(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidEarthQuakePolicyModel_ReturnsUpdateEarthQuakePolicyResponse() {
        when(executeEarthQuakePolicy.executeUpdate(earthQuakePolicyModel)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.update(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeUpdate(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidEarthQuakePolicyModel_ReturnsListGetEarthQuakePolicyResponse() {
        when(executeEarthQuakePolicy.executeGetList(earthQuakePolicyModel)).thenReturn(List.of(earthQuakePolicyModel));
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.getList(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeGetList(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidEarthQuakePolicyModel_ReturnsDeleteEarthQuakePolicyResponse() {
        when(executeEarthQuakePolicy.executeDelete(earthQuakePolicyModel)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.delete(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeDelete(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testGet_ValidEarthQuakePolicyModel_ReturnsGetEarthQuakePolicyResponse() {
        when(executeEarthQuakePolicy.executeGet(earthQuakePolicyModel)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.get(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).executeGet(earthQuakePolicyModel);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testAccept_ValidEarthQuakePolicyModel_ReturnsSetEarthQuakePolicyStatusResponse() {
        when(executeEarthQuakePolicy.changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.ACTIVATE)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.accept(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.ACTIVATE);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testReject_ValidEarthQuakePolicyModel_ReturnsSetEarthQuakePolicyStatusResponse() {
        when(executeEarthQuakePolicy.changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.CANCEL)).thenReturn(earthQuakePolicyModel);
        doNothing().when(objectValidation).validateModel(earthQuakePolicyModel, "earthQuakePolicyModel");

        earthQuakeService.reject(earthQuakePolicyModel);

        verify(executeEarthQuakePolicy, times(1)).changeCarPolicyState(earthQuakePolicyModel, PolicyEvent.CANCEL);
        verify(objectValidation, times(1)).validateModel(any(EarthQuakeModel.class), any(String.class));
    }

    @Test
    void testTotalRecord_ValidEarthQuakePolicyModel_ReturnsINT() {
        when(executeEarthQuakePolicy.executeGetTotalRecord()).thenReturn(5);

        int totalRecord = earthQuakeService.getTotalRecord();

        assertEquals(5, totalRecord);
        verify(executeEarthQuakePolicy, times(1)).executeGetTotalRecord();
    }
}
