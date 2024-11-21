package PolicyProject.policyService.interfaces.mappers.WeightsMapperTest;

import PolicyProject.policyService.domain.dto.request.WeightRequest.CreateWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.DeleteWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.GetWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.UpdateWeightRequest;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.domain.model.WeightsModel.WeightsModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.EarthQuakeWeightMapper;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EarthquakeWeightsMapperTest {
    private final EarthQuakeWeightMapper mapper = EarthQuakeWeightMapper.INSTANCE;

    @Test
    public void testWeightsEntityListToWeightsModelList_allFields() {
        List<EarthQaukeWeights> entities = List.of(
                new EarthQaukeWeights(1L, "key1", new BigDecimal("10.5"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1"),
                new EarthQaukeWeights(2L, "key2", new BigDecimal("15.5"), new BigDecimal("2.0"), new BigDecimal("25.0"), "type2")
        );

        List<WeightsModel> models = mapper.WeightsEntityListToWeightsModelList(entities);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(1L, model1.id());
        assertEquals("key1", model1.key());
        assertEquals(new BigDecimal("10.5"), model1.weight());
        assertEquals(new BigDecimal("1.0"), model1.minValue());
        assertEquals(new BigDecimal("20.0"), model1.maxValue());
        assertEquals("type1", model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(2L, model2.id());
        assertEquals("key2", model2.key());
        assertEquals(new BigDecimal("15.5"), model2.weight());
        assertEquals(new BigDecimal("2.0"), model2.minValue());
        assertEquals(new BigDecimal("25.0"), model2.maxValue());
        assertEquals("type2", model2.type());
    }
    @Test
    public void testWeightsEntityListToWeightsModelList_nullValues() {
        List<EarthQaukeWeights> entities = List.of(
                new EarthQaukeWeights(null, null, null, null, null, null),
                new EarthQaukeWeights(null, null, null, null, null, null)
        );

        List<WeightsModel> models = mapper.WeightsEntityListToWeightsModelList(entities);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(null, model1.id());
        assertEquals(null, model1.key());
        assertEquals(null, model1.weight());
        assertEquals(null, model1.minValue());
        assertEquals(null, model1.maxValue());
        assertEquals(null, model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(null, model2.id());
        assertEquals(null, model2.key());
        assertEquals(null, model2.weight());
        assertEquals(null, model2.minValue());
        assertEquals(null, model2.maxValue());
        assertEquals(null, model2.type());
    }
    @Test
    public void testWeightsEntityListToWeightsModelList_partialFields() {
        List<EarthQaukeWeights> entities = List.of(
                new EarthQaukeWeights(1L, "key1", null, new BigDecimal("1.0"), null, "type1"),
                new EarthQaukeWeights(2L, "key2", new BigDecimal("15.5"), null, new BigDecimal("25.0"), null)
        );

        List<WeightsModel> models = mapper.WeightsEntityListToWeightsModelList(entities);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(1L, model1.id());
        assertEquals("key1", model1.key());
        assertEquals(null, model1.weight());
        assertEquals(new BigDecimal("1.0"), model1.minValue());
        assertEquals(null, model1.maxValue());
        assertEquals("type1", model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(2L, model2.id());
        assertEquals("key2", model2.key());
        assertEquals(new BigDecimal("15.5"), model2.weight());
        assertEquals(null, model2.minValue());
        assertEquals(new BigDecimal("25.0"), model2.maxValue());
        assertEquals(null, model2.type());
    }
    @Test
    public void testWeightsEntityToWeightsModel_allFields() {
        EarthQaukeWeights entity = new EarthQaukeWeights(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        WeightsModel model = mapper.WeightsEntityToWeightsModel(entity);

        assertNotNull(model);
        assertEquals(1L, model.id());
        assertEquals("testKey", model.key());
        assertEquals(new BigDecimal("10.5"), model.weight());
        assertEquals(new BigDecimal("1.0"), model.minValue());
        assertEquals(new BigDecimal("20.0"), model.maxValue());
        assertEquals("testType", model.type());
    }
    @Test
    public void testWeightsEntityToWeightsModel_nullValues() {
        EarthQaukeWeights entity = new EarthQaukeWeights(
                null,
                null,
                null,
                null,
                null,
                null
        );

        WeightsModel model = mapper.WeightsEntityToWeightsModel(entity);

        assertNotNull(model);
        assertEquals(null, model.id());
        assertEquals(null, model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testWeightsEntityToWeightsModel_partialFields() {
        EarthQaukeWeights entity = new EarthQaukeWeights(
                1L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        WeightsModel model = mapper.WeightsEntityToWeightsModel(entity);

        assertNotNull(model);
        assertEquals(1L, model.id());
        assertEquals("partialKey", model.key());
        assertEquals(null, model.weight());
        assertEquals(new BigDecimal("2.5"), model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals("partialType", model.type());
    }
    @Test
    public void testWeightsModelToWeightEntity_allFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        EarthQaukeWeights entity = mapper.WeightsModelToWeightEntity(model);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("testKey", entity.getKey());
        assertEquals(new BigDecimal("10.5"), entity.getWeight());
        assertEquals(new BigDecimal("1.0"), entity.getMinValue());
        assertEquals(new BigDecimal("20.0"), entity.getMaxValue());
        assertEquals("testType", entity.getType());
    }
    @Test
    public void testWeightsModelToWeightEntity_nullValues() {
        WeightsModel model = new WeightsModel(
                null,
                null,
                null,
                null,
                null,
                null
        );

        EarthQaukeWeights entity = mapper.WeightsModelToWeightEntity(model);

        assertNotNull(entity);
        assertEquals(null, entity.getId());
        assertEquals(null, entity.getKey());
        assertEquals(null, entity.getWeight());
        assertEquals(null, entity.getMinValue());
        assertEquals(null, entity.getMaxValue());
        assertEquals(null, entity.getType());
    }
    @Test
    public void testWeightsModelToWeightEntity_partialFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        EarthQaukeWeights entity = mapper.WeightsModelToWeightEntity(model);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("partialKey", entity.getKey());
        assertEquals(null, entity.getWeight());
        assertEquals(new BigDecimal("2.5"), entity.getMinValue());
        assertEquals(null, entity.getMaxValue());
        assertEquals("partialType", entity.getType());
    }
    @Test
    public void testWeightsModelListToGetWeightResponse_allFields() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", new BigDecimal("10.5"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), new BigDecimal("2.0"), new BigDecimal("25.0"), "type2")
        );

        List<GetWeightResponse> responses = mapper.WeightsModelListToGetWeightResponse(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        GetWeightResponse response1 = responses.get(0);
        assertEquals(1L, response1.id());
        assertEquals("key1", response1.key());
        assertEquals(new BigDecimal("10.5"), response1.weight());
        assertEquals(new BigDecimal("1.0"), response1.minValue());
        assertEquals(new BigDecimal("20.0"), response1.maxValue());
        assertEquals("type1", response1.type());

        GetWeightResponse response2 = responses.get(1);
        assertEquals(2L, response2.id());
        assertEquals("key2", response2.key());
        assertEquals(new BigDecimal("15.5"), response2.weight());
        assertEquals(new BigDecimal("2.0"), response2.minValue());
        assertEquals(new BigDecimal("25.0"), response2.maxValue());
        assertEquals("type2", response2.type());
    }
    @Test
    public void testWeightsModelListToGetWeightResponse_nullValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(null, null, null, null, null, null),
                new WeightsModel(null, null, null, null, null, null)
        );

        List<GetWeightResponse> responses = mapper.WeightsModelListToGetWeightResponse(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        GetWeightResponse response1 = responses.get(0);
        assertEquals(null, response1.id());
        assertEquals(null, response1.key());
        assertEquals(null, response1.weight());
        assertEquals(null, response1.minValue());
        assertEquals(null, response1.maxValue());
        assertEquals(null, response1.type());

        GetWeightResponse response2 = responses.get(1);
        assertEquals(null, response2.id());
        assertEquals(null, response2.key());
        assertEquals(null, response2.weight());
        assertEquals(null, response2.minValue());
        assertEquals(null, response2.maxValue());
        assertEquals(null, response2.type());
    }
    @Test
    public void testWeightsModelListToGetWeightResponse_partialValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", null, new BigDecimal("1.0"), null, "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), null, new BigDecimal("25.0"), null)
        );

        List<GetWeightResponse> responses = mapper.WeightsModelListToGetWeightResponse(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        GetWeightResponse response1 = responses.get(0);
        assertEquals(1L, response1.id());
        assertEquals("key1", response1.key());
        assertEquals(null, response1.weight());
        assertEquals(new BigDecimal("1.0"), response1.minValue());
        assertEquals(null, response1.maxValue());
        assertEquals("type1", response1.type());

        GetWeightResponse response2 = responses.get(1);
        assertEquals(2L, response2.id());
        assertEquals("key2", response2.key());
        assertEquals(new BigDecimal("15.5"), response2.weight());
        assertEquals(null, response2.minValue());
        assertEquals(new BigDecimal("25.0"), response2.maxValue());
        assertEquals(null, response2.type());
    }
    @Test
    public void testWeightsModelListToUpdateWeightResponseList_allFields() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", new BigDecimal("10.5"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), new BigDecimal("2.0"), new BigDecimal("25.0"), "type2")
        );

        List<UpdateWeightResponse> responses = mapper.WeightsModelListToUpdateWeightResponseList(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        UpdateWeightResponse response1 = responses.get(0);
        assertEquals("key1", response1.key());

        UpdateWeightResponse response2 = responses.get(1);
        assertEquals("key2", response2.key());
    }
    @Test
    public void testWeightsModelListToUpdateWeightResponseList_nullValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(null, null, null, null, null, null),
                new WeightsModel(null, null, null, null, null, null)
        );

        List<UpdateWeightResponse> responses = mapper.WeightsModelListToUpdateWeightResponseList(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        UpdateWeightResponse response1 = responses.get(0);
        assertEquals(null, response1.key());

        UpdateWeightResponse response2 = responses.get(1);
        assertEquals(null, response2.key());
    }
    @Test
    public void testWeightsModelListToUpdateWeightResponseList_partialValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", null, new BigDecimal("1.0"), null, "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), null, new BigDecimal("25.0"), null)
        );

        List<UpdateWeightResponse> responses = mapper.WeightsModelListToUpdateWeightResponseList(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());

        UpdateWeightResponse response1 = responses.get(0);
        assertEquals("key1", response1.key());

        UpdateWeightResponse response2 = responses.get(1);
        assertEquals("key2", response2.key());
    }
    @Test
    public void testWeightsModelToCreateWeightResponse_allFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        CreateWeightResponse response = mapper.WeightsModelToCreateWeightResponse(model);

        assertNotNull(response);
        assertEquals("testKey", response.key());
    }
    @Test
    public void testWeightsModelToUpdateWeightResponse_allFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        UpdateWeightResponse response = mapper.WeightsModelToUpdateWeightResponse(model);

        assertNotNull(response);
        assertEquals("testKey", response.key());
    }
    @Test
    public void testWeightsModelToCreateWeightResponse_nullKey() {
        WeightsModel model = new WeightsModel(
                null,
                null,
                null,
                null,
                null,
                null
        );

        CreateWeightResponse response = mapper.WeightsModelToCreateWeightResponse(model);

        assertNotNull(response);
        assertEquals(null, response.key());
    }
    @Test
    public void testWeightsModelToUpdateWeightResponse_nullKey() {
        WeightsModel model = new WeightsModel(
                null,
                null,
                null,
                null,
                null,
                null
        );

        UpdateWeightResponse response = mapper.WeightsModelToUpdateWeightResponse(model);

        assertNotNull(response);
        assertEquals(null, response.key());
    }
    @Test
    public void testWeightsModelToCreateWeightResponse_partialFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        CreateWeightResponse response = mapper.WeightsModelToCreateWeightResponse(model);

        assertNotNull(response);
        assertEquals("partialKey", response.key());
    }
    @Test
    public void testWeightsModelToUpdateWeightResponse_partialFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        UpdateWeightResponse response = mapper.WeightsModelToUpdateWeightResponse(model);

        assertNotNull(response);
        assertEquals("partialKey", response.key());
    }
    @Test
    public void testUpdateWeightRequestListToWeightsModelList_allFields() {
        List<UpdateWeightRequest> requests = List.of(
                new UpdateWeightRequest(1L, "key1", new BigDecimal("10.5"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1"),
                new UpdateWeightRequest(2L, "key2", new BigDecimal("15.5"), new BigDecimal("2.0"), new BigDecimal("25.0"), "type2")
        );

        List<WeightsModel> models = mapper.UpdateWeightRequestListToWeightsModelList(requests);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(1L, model1.id());
        assertEquals("key1", model1.key());
        assertEquals(new BigDecimal("10.5"), model1.weight());
        assertEquals(new BigDecimal("1.0"), model1.minValue());
        assertEquals(new BigDecimal("20.0"), model1.maxValue());
        assertEquals("type1", model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(2L, model2.id());
        assertEquals("key2", model2.key());
        assertEquals(new BigDecimal("15.5"), model2.weight());
        assertEquals(new BigDecimal("2.0"), model2.minValue());
        assertEquals(new BigDecimal("25.0"), model2.maxValue());
        assertEquals("type2", model2.type());
    }
    @Test
    public void testUpdateWeightRequestListToWeightsModelList_nullValues() {
        List<UpdateWeightRequest> requests = List.of(
                new UpdateWeightRequest(null, null, null, null, null, null),
                new UpdateWeightRequest(null, null, null, null, null, null)
        );

        List<WeightsModel> models = mapper.UpdateWeightRequestListToWeightsModelList(requests);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(null, model1.id());
        assertEquals(null, model1.key());
        assertEquals(null, model1.weight());
        assertEquals(null, model1.minValue());
        assertEquals(null, model1.maxValue());
        assertEquals(null, model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(null, model2.id());
        assertEquals(null, model2.key());
        assertEquals(null, model2.weight());
        assertEquals(null, model2.minValue());
        assertEquals(null, model2.maxValue());
        assertEquals(null, model2.type());
    }
    @Test
    public void testUpdateWeightRequestListToWeightsModelList_partialValues() {
        List<UpdateWeightRequest> requests = List.of(
                new UpdateWeightRequest(1L, "key1", null, new BigDecimal("1.0"), null, "type1"),
                new UpdateWeightRequest(2L, "key2", new BigDecimal("15.5"), null, new BigDecimal("25.0"), null)
        );

        List<WeightsModel> models = mapper.UpdateWeightRequestListToWeightsModelList(requests);

        assertNotNull(models);
        assertEquals(2, models.size());

        WeightsModel model1 = models.get(0);
        assertEquals(1L, model1.id());
        assertEquals("key1", model1.key());
        assertEquals(null, model1.weight());
        assertEquals(new BigDecimal("1.0"), model1.minValue());
        assertEquals(null, model1.maxValue());
        assertEquals("type1", model1.type());

        WeightsModel model2 = models.get(1);
        assertEquals(2L, model2.id());
        assertEquals("key2", model2.key());
        assertEquals(new BigDecimal("15.5"), model2.weight());
        assertEquals(null, model2.minValue());
        assertEquals(new BigDecimal("25.0"), model2.maxValue());
        assertEquals(null, model2.type());
    }
    @Test
    public void testCreateWeightRequestToWeightsModel_allFields() {
        CreateWeightRequest request = new CreateWeightRequest(
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        WeightsModel model = mapper.CreateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals("testKey", model.key());
        assertEquals(new BigDecimal("10.5"), model.weight());
        assertEquals(new BigDecimal("1.0"), model.minValue());
        assertEquals(new BigDecimal("20.0"), model.maxValue());
        assertEquals("testType", model.type());
    }
    @Test
    public void testCreateWeightRequestToWeightsModel_nullValues() {
        CreateWeightRequest request = new CreateWeightRequest(
                null,
                null,
                null,
                null,
                null
        );

        WeightsModel model = mapper.CreateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(null, model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testCreateWeightRequestToWeightsModel_partialValues() {
        CreateWeightRequest request = new CreateWeightRequest(
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        WeightsModel model = mapper.CreateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals("partialKey", model.key());
        assertEquals(null, model.weight());
        assertEquals(new BigDecimal("2.5"), model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals("partialType", model.type());
    }
    @Test
    public void testUpdateWeightRequestToWeightsModel_allFields() {
        UpdateWeightRequest request = new UpdateWeightRequest(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        WeightsModel model = mapper.UpdateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(1L, model.id());
        assertEquals("testKey", model.key());
        assertEquals(new BigDecimal("10.5"), model.weight());
        assertEquals(new BigDecimal("1.0"), model.minValue());
        assertEquals(new BigDecimal("20.0"), model.maxValue());
        assertEquals("testType", model.type());
    }
    @Test
    public void testUpdateWeightRequestToWeightsModel_nullValues() {
        UpdateWeightRequest request = new UpdateWeightRequest(
                null,
                null,
                null,
                null,
                null,
                null
        );

        WeightsModel model = mapper.UpdateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(null, model.id());
        assertEquals(null, model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testUpdateWeightRequestToWeightsModel_partialValues() {
        UpdateWeightRequest request = new UpdateWeightRequest(
                2L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        WeightsModel model = mapper.UpdateWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(2L, model.id());
        assertEquals("partialKey", model.key());
        assertEquals(null, model.weight());
        assertEquals(new BigDecimal("2.5"), model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals("partialType", model.type());
    }
    @Test
    public void testDeleteWeightRequestToWeightsModel_withValidKey() {
        DeleteWeightRequest request = new DeleteWeightRequest("testKey");

        WeightsModel model = mapper.DeleteWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals("testKey", model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testDeleteWeightRequestToWeightsModel_withNullKey() {
        DeleteWeightRequest request = new DeleteWeightRequest(null);

        WeightsModel model = mapper.DeleteWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(null, model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testGetWeightRequestToWeightsModel_withValidKey() {
        GetWeightRequest request = new GetWeightRequest("testKey");

        WeightsModel model = mapper.GetWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals("testKey", model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testGetWeightRequestToWeightsModel_withNullKey() {
        GetWeightRequest request = new GetWeightRequest(null);

        WeightsModel model = mapper.GetWeightRequestToWeightsModel(request);

        assertNotNull(model);
        assertEquals(null, model.key());
        assertEquals(null, model.weight());
        assertEquals(null, model.minValue());
        assertEquals(null, model.maxValue());
        assertEquals(null, model.type());
    }
    @Test
    public void testWeightsModelToDeleteWeightResponse_allFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        DeleteWeightResponse response = mapper.WeightsModelToDeleteWeightResponse(model);

        assertNotNull(response);
        assertEquals("testKey", response.key());
    }
    @Test
    public void testWeightsModelToDeleteWeightResponse_nullKey() {
        WeightsModel model = new WeightsModel(
                null,
                null,
                null,
                null,
                null,
                null
        );

        DeleteWeightResponse response = mapper.WeightsModelToDeleteWeightResponse(model);

        assertNotNull(response);
        assertEquals(null, response.key());
    }
    @Test
    public void testWeightsModelToGetWeightResponse_allFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "testKey",
                new BigDecimal("10.5"),
                new BigDecimal("1.0"),
                new BigDecimal("20.0"),
                "testType"
        );

        GetWeightResponse response = mapper.WeightsModelToGetWeightResponse(model);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("testKey", response.key());
        assertEquals(new BigDecimal("10.5"), response.weight());
        assertEquals(new BigDecimal("1.0"), response.minValue());
        assertEquals(new BigDecimal("20.0"), response.maxValue());
        assertEquals("testType", response.type());
    }
    @Test
    public void testWeightsModelToGetWeightResponse_nullValues() {
        WeightsModel model = new WeightsModel(
                null,
                null,
                null,
                null,
                null,
                null
        );

        GetWeightResponse response = mapper.WeightsModelToGetWeightResponse(model);

        assertNotNull(response);
        assertEquals(null, response.id());
        assertEquals(null, response.key());
        assertEquals(null, response.weight());
        assertEquals(null, response.minValue());
        assertEquals(null, response.maxValue());
        assertEquals(null, response.type());
    }
    @Test
    public void testWeightsModelToGetWeightResponse_partialFields() {
        WeightsModel model = new WeightsModel(
                1L,
                "partialKey",
                null,
                new BigDecimal("2.5"),
                null,
                "partialType"
        );

        GetWeightResponse response = mapper.WeightsModelToGetWeightResponse(model);

        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("partialKey", response.key());
        assertEquals(null, response.weight());
        assertEquals(new BigDecimal("2.5"), response.minValue());
        assertEquals(null, response.maxValue());
        assertEquals("partialType", response.type());
    }
    @Test
    public void testWeightsModelListToWeightEntityList_allFields() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", new BigDecimal("10.5"), new BigDecimal("1.0"), new BigDecimal("20.0"), "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), new BigDecimal("2.0"), new BigDecimal("25.0"), "type2")
        );

        List<EarthQaukeWeights> entities = mapper.WeightsModelListToWeightEntityList(models);

        assertNotNull(entities);
        assertEquals(2, entities.size());

        EarthQaukeWeights entity1 = entities.get(0);
        assertEquals(1L, entity1.getId());
        assertEquals("key1", entity1.getKey());
        assertEquals(new BigDecimal("10.5"), entity1.getWeight());
        assertEquals(new BigDecimal("1.0"), entity1.getMinValue());
        assertEquals(new BigDecimal("20.0"), entity1.getMaxValue());
        assertEquals("type1", entity1.getType());

        EarthQaukeWeights entity2 = entities.get(1);
        assertEquals(2L, entity2.getId());
        assertEquals("key2", entity2.getKey());
        assertEquals(new BigDecimal("15.5"), entity2.getWeight());
        assertEquals(new BigDecimal("2.0"), entity2.getMinValue());
        assertEquals(new BigDecimal("25.0"), entity2.getMaxValue());
        assertEquals("type2", entity2.getType());
    }
    @Test
    public void testWeightsModelListToWeightEntityList_nullValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(null, null, null, null, null, null),
                new WeightsModel(null, null, null, null, null, null)
        );

        List<EarthQaukeWeights> entities = mapper.WeightsModelListToWeightEntityList(models);

        assertNotNull(entities);
        assertEquals(2, entities.size());

        EarthQaukeWeights entity1 = entities.get(0);
        assertEquals(null, entity1.getId());
        assertEquals(null, entity1.getKey());
        assertEquals(null, entity1.getWeight());
        assertEquals(null, entity1.getMinValue());
        assertEquals(null, entity1.getMaxValue());
        assertEquals(null, entity1.getType());

        EarthQaukeWeights entity2 = entities.get(1);
        assertEquals(null, entity2.getId());
        assertEquals(null, entity2.getKey());
        assertEquals(null, entity2.getWeight());
        assertEquals(null, entity2.getMinValue());
        assertEquals(null, entity2.getMaxValue());
        assertEquals(null, entity2.getType());
    }
    @Test
    public void testWeightsModelListToWeightEntityList_partialValues() {
        List<WeightsModel> models = List.of(
                new WeightsModel(1L, "key1", null, new BigDecimal("1.0"), null, "type1"),
                new WeightsModel(2L, "key2", new BigDecimal("15.5"), null, new BigDecimal("25.0"), null)
        );

        List<EarthQaukeWeights> entities = mapper.WeightsModelListToWeightEntityList(models);

        assertNotNull(entities);
        assertEquals(2, entities.size());

        EarthQaukeWeights entity1 = entities.get(0);
        assertEquals(1L, entity1.getId());
        assertEquals("key1", entity1.getKey());
        assertEquals(null, entity1.getWeight());
        assertEquals(new BigDecimal("1.0"), entity1.getMinValue());
        assertEquals(null, entity1.getMaxValue());
        assertEquals("type1", entity1.getType());

        EarthQaukeWeights entity2 = entities.get(1);
        assertEquals(2L, entity2.getId());
        assertEquals("key2", entity2.getKey());
        assertEquals(new BigDecimal("15.5"), entity2.getWeight());
        assertEquals(null, entity2.getMinValue());
        assertEquals(new BigDecimal("25.0"), entity2.getMaxValue());
        assertEquals(null, entity2.getType());
    }
}
