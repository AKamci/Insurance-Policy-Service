package PolicyProject.policyService.domain;

import PolicyProject.policyService.domain.Enums.Enums.SharedEnum.CoverageType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CoverageTypeConverter implements AttributeConverter<CoverageType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CoverageType coverageType) {
        if (coverageType == null) {
            return null;
        }
        return coverageType.getCode();
    }

    @Override
    public CoverageType convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return CoverageType.fromCode(code);
    }
}
