package PolicyProject.policyService.interfaces.controller.Version_1;

import PolicyProject.policyService.application.service.Service.CarPolicyService;
import PolicyProject.policyService.application.service.Service.EarthQuakeService;
import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.request.carPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.domain.dto.response.carPolicyResponse.*;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.EarthQuakeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/earthQuake")
@RequiredArgsConstructor
public class EarthQuakePolicyController_V1 {

        private final EarthQuakeService earthQuakeService;


        @PostMapping
        public ResponseEntity<CreateEarthQuakeResponse> createPolicy(@Valid @RequestBody CreateEarthQuakeRequest createEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(earthQuakeService.create
                            (EarthQuakeMapper.INSTANCE.createEarthQuakeRequestToEarthQuakeModel(createEarthQuakeRequest)));

        }

        @GetMapping
        public ResponseEntity<GetEarthQuakeResponse> getPolicy(@Valid @ModelAttribute GetEarthQuakeRequest getEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.get
                            (EarthQuakeMapper.INSTANCE.getEarthQuakeRequestToEarthQuakeModel(getEarthQuakeRequest)));


        }

        @PutMapping
        public  ResponseEntity<UpdateEarthQuakeResponse> updatePolicy(@Valid @RequestBody UpdateEarthQuakeRequest updateEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.update
                            (EarthQuakeMapper.INSTANCE.updateEarthQuakeRequestToEarthQuakeModel(updateEarthQuakeRequest)));
        }

        @PutMapping("/accepted")
        public  ResponseEntity<SetStateEarthQuakeResponse> acceptPolicy(@Valid @RequestBody SetStateEarthQuakeRequest setStateEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.accept
                            (EarthQuakeMapper.INSTANCE.setStateEarthQuakeRequestToEarthQuakeModel(setStateEarthQuakeRequest)));
        }

        @PutMapping("/rejected")
        public  ResponseEntity<SetStateEarthQuakeResponse> rejectPolicy(@Valid @RequestBody SetStateEarthQuakeRequest setStateEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.reject
                            (EarthQuakeMapper.INSTANCE.setStateEarthQuakeRequestToEarthQuakeModel(setStateEarthQuakeRequest)));
        }

        @GetMapping("/list")
        public ResponseEntity<List<GetEarthQuakeResponse>> getPolicies(@Valid @ModelAttribute GetListEarthQuakeRequest getListEarthQuakeRequest)
        {

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.getList
                            (EarthQuakeMapper.INSTANCE.getEarthQuakeRequestListToEarthQuakeModelList(getListEarthQuakeRequest)));
        }

        @DeleteMapping
        public ResponseEntity<DeleteEarthQuakeResponse> deletePolicy(@Valid @ModelAttribute DeleteEarthQuakeRequest deleteEarthQuakeRequest)
        {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(earthQuakeService.delete
                            (EarthQuakeMapper.INSTANCE.deleteEarthQuakeRequestToEarthQuakeModel(deleteEarthQuakeRequest)));
        }


        @GetMapping("/totalRecord")
        public ResponseEntity<Integer> getTotalRecord()
        {
            return ResponseEntity.status(HttpStatus.OK).body(earthQuakeService.getTotalRecord());
        }



    }



