package PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteAuxiliary.CarPolicy.ExecuteLicensePlate;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LicensePlateServiceTest {

    @Mock
    private ExecuteLicensePlate executeLicensePlate;

    @Mock
    private LicensePlateModel licensePlateModel;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private LicensePlateMapper licensePlateMapper;

    @InjectMocks
    private LicensePlateService licensePlateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetWCustomer_ValidLicensePlateModel() {

        when(executeLicensePlate.ExecuteGetLicensePlateWithCustomer(licensePlateModel)).thenReturn(licensePlateModel);
        doNothing().when(objectValidation).validateModel(licensePlateModel, "licensePlateModel");

        licensePlateService.getWCustomer(licensePlateModel);

        verify(objectValidation).validateModel(licensePlateModel, "licensePlateModel");
        verify(executeLicensePlate, times(1)).ExecuteGetLicensePlateWithCustomer(licensePlateModel);
    }

}
