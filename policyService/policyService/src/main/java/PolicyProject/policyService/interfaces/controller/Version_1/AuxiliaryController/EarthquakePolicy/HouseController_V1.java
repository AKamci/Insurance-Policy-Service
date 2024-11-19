package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.EarthquakePolicy;


import PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy.HouseService;
import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/house")
@RequiredArgsConstructor
public class HouseController_V1 {

    private final HouseService houseService;

    @GetMapping("/WCustomer")
    public ResponseEntity<GetHouseWCustomerResponse> getWCustomer(
            @Valid @ModelAttribute GetHouseWCustomerRequest getHouseWCustomerRequest) {

        GetHouseWCustomerResponse getHouseWCustomerResponse = houseService.getWCustomer(
                HouseMapper.INSTANCE.getHouseWithCustomerRequestToHouseModel(getHouseWCustomerRequest));

        return ResponseEntity.status(HttpStatus.OK).body(getHouseWCustomerResponse);
    }
}
