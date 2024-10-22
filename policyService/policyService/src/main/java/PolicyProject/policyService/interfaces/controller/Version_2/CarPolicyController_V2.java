package PolicyProject.policyService.interfaces.controller.Version_2;

import PolicyProject.policyService.application.service.IService.ICarPolicyService;
import PolicyProject.policyService.application.service.Service.CarPolicyService;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/v2/carPolicy")
@RequiredArgsConstructor
public class CarPolicyController_V2 {

    private final ICarPolicyService carPolicyService;

    @PostMapping
    public CompletableFuture<ResponseEntity<CreateCarPolicyResponse>> createPolicy(
            @Valid @RequestBody CreateCarPolicyRequest createCarPolicyRequest) {
        return carPolicyService.create(
                        CarPolicyMapper.INSTANCE.createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest))
                .thenApply(createdCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(createdCustomer));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<GetCarPolicyResponse>> getPolicy(
            @Valid @ModelAttribute GetCarPolicyRequest getCarPolicyRequest) {
        return carPolicyService.get(
                        CarPolicyMapper.INSTANCE.getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest))
                .thenApply(getCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(getCustomer));
    }

    @PutMapping
    public  CompletableFuture<ResponseEntity<UpdateCarPolicyResponse>> updateCarPolicy(
            @Valid @RequestBody UpdateCarPolicyRequest updateCarPolicyRequest) {
        return carPolicyService.update(
                        CarPolicyMapper.INSTANCE.updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest))
                .thenApply(updatedCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(updatedCustomer));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<GetCarPolicyResponse>>> getPolicies(
            @Valid @ModelAttribute GetCarPolicyListRequest getCarPolicyListRequest) {
        return carPolicyService.getList(
                        CarPolicyMapper.INSTANCE.getCarPoliciesToCarPolicyModel(getCarPolicyListRequest))
                .thenApply(listCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(listCustomer));
    }

    @DeleteMapping
    public CompletableFuture<ResponseEntity<DeleteCarPolicyResponse>> deletePolicy(
            @Valid @ModelAttribute DeleteCarPolicyRequest deleteCarPolicyRequest) {
        return carPolicyService.delete(
                        CarPolicyMapper.INSTANCE.deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest))
                .thenApply(deletedCustomer -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(deletedCustomer));
    }

    @GetMapping("/totalRecord")
    public CompletableFuture<ResponseEntity<Integer>> getTotalRecord() {
        return carPolicyService.getTotalRecord()
                .thenApply(totalRecord -> ResponseEntity.status(HttpStatus.OK).body(totalRecord));
    }



}
