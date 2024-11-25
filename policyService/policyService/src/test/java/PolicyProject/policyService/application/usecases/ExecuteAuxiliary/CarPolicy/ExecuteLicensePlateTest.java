package PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy;

import PolicyProject.policyService.application.gateways.AuxiliaryGateway.CarPolicy.LicensePlateGateway;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.application.usecases.ExecuteWeights.ExecuteCarPolicyWeight;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class ExecuteLicensePlateTest {

    @Mock
    private LicensePlateGateway licensePlateGateway;

    @Mock
    private ExecuteCarPolicyWeight executeWeight;

    @Mock
    private LicensePlate licensePlate;

    @Mock
    private LicensePlateModel licensePlateModel;

    @InjectMocks
    private ExecuteLicensePlate executeLicensePlate;

    @BeforeEach
    public void setup() {
        executeLicensePlate = new ExecuteLicensePlate(licensePlateGateway, executeWeight);
    }

    @Test
    public void testExecuteGetLicensePlate_Found() {
        String plateNumber = "12345";
        when(licensePlateGateway.get(plateNumber)).thenReturn(licensePlate);

        when(LicensePlateMapper.INSTANCE.licensePlateEntityToLicensePlateModel(licensePlate)).thenReturn(licensePlateModel);

        LicensePlateModel result = executeLicensePlate.ExecuteGetLicensePlate(plateNumber);

        assertEquals(licensePlateModel, result);
        verify(licensePlateGateway).get(plateNumber);
    }

    @Test
    public void testExecuteGetLicensePlate_NotFound() {
        String plate = "67890";
        when(licensePlateGateway.get(plate)).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> executeLicensePlate.ExecuteGetLicensePlate(plate));
        verify(licensePlateGateway).get(plate);
    }
}
