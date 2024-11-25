package PolicyProject.policyService.domain;

import PolicyProject.policyService.domain.Enums.Enums.SharedEnum.CoverageType;
import jakarta.persistence.Converter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CoverageTypeConverterTest {

    @Test
    public void testConvertToDatabaseColumn_NullCoverageType() {
        CoverageTypeConverter converter = new CoverageTypeConverter();
        Integer result = converter.convertToDatabaseColumn(null);
        assertNull(result, "Expected null when converting null CoverageType to database column");
    }

    @Test
    public void testConvertToDatabaseColumn_ValidCoverageType() {
        CoverageTypeConverter converter = new CoverageTypeConverter();
        Integer result = converter.convertToDatabaseColumn(CoverageType.KASKO);
        assertEquals(102, result, "Expected 102 when converting KASKO CoverageType to database column");
    }

    @Test
    public void testConvertToEntityAttribute_NullCode() {
        CoverageTypeConverter converter = new CoverageTypeConverter();
        CoverageType result = converter.convertToEntityAttribute(null);
        assertNull(result, "Expected null when converting null code to CoverageType");
    }

    @Test
    public void testConvertToEntityAttribute_ValidCode() {
        CoverageTypeConverter converter = new CoverageTypeConverter();
        CoverageType result = converter.convertToEntityAttribute(102);
        assertEquals(CoverageType.KASKO, result, "Expected KASKO when converting 102 to CoverageType");
    }
}
