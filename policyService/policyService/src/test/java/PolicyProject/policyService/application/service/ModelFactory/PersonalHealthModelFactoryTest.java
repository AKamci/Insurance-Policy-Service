package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PersonalHealthModelFactoryTest {
    @Test
    void shouldCreatePersonalHealthModelWithHealthIdAndTckn() {
        Long id = 1L;
        String tckn = "12345678901";
        PersonalHealthModel model = PersonalHealthModelFactory.createPersonalHealthModelWithHealthIdAndTckn(id, tckn);
        assertNotNull(model);
        assertEquals(id, model.id());
        assertEquals(tckn, model.tckn());
    }
    @Test
    void shouldCreateCalculatedPersonalHealthModel() {
        PersonalHealthModel inputModel = new PersonalHealthModel(1L, "12345678901", null, null, null, null, null, null, null, null, null, null, null, null, null);
        Integer coverageCode = 100;
        PersonalHealthModel model = PersonalHealthModelFactory.createCalculatedPersonalHealthModel(inputModel, coverageCode);
        assertNotNull(model);
        assertEquals(inputModel.id(), model.id());
        assertEquals(inputModel.tckn(), model.tckn());
        assertEquals(coverageCode, model.coverageCode());
    }
    @Test
    void shouldCreatePersonalHealthModelWithAmount() {
        PersonalHealthModel inputModel = new PersonalHealthModel(1L, "12345678901", null, null, null, null, null, null, null, null, null, null, null, null, null);
        Long amount = 5000L;
        PersonalHealthModel model = PersonalHealthModelFactory.createPersonalHealthModelWithAmount(inputModel, amount);
        assertNotNull(model);
        assertEquals(inputModel.id(), model.id());
        assertEquals(inputModel.tckn(), model.tckn());
        assertEquals(amount, model.Amount());
    }

}
