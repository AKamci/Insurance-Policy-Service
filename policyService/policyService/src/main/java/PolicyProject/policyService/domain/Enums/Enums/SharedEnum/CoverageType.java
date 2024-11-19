package PolicyProject.policyService.domain.Enums.Enums.SharedEnum;

import lombok.Getter;

@Getter
public enum CoverageType {
    TRAFİK(101),
    KASKO(102),
    YARI_KAPSAM(103),
    TAM_KAPSAM(104),
    AYAKTA_TEDAVİ(105),
    YATARAK_TEDAVİ(106),
    SEYEHAT(107);


    private final int code;

    CoverageType(int code) {
        this.code = code;
    }

    public static CoverageType fromCode(int code) {
        for (CoverageType type : CoverageType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CoverageType code: " + code);
    }
}
