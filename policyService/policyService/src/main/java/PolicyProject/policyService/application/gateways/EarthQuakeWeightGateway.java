package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EarthQuakeWeightGateway {
    EarthQaukeWeights get(String key);
    EarthQaukeWeights create(EarthQaukeWeights weights);
    EarthQaukeWeights update(EarthQaukeWeights weights);
    EarthQaukeWeights delete(String key);
    List<EarthQaukeWeights> list();
    List<EarthQaukeWeights> listFilter();
    List<EarthQaukeWeights> updateOrSave(List<EarthQaukeWeights> weights);
}
