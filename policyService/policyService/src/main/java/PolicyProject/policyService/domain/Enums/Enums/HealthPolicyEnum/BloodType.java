package PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum;

public enum BloodType {
    A_POSITIVE(1),
    A_NEGATIVE(2),
    B_POSITIVE(3),
    B_NEGATIVE(4),
    AB_POSITIVE(5),
    AB_NEGATIVE(6),
    O_POSITIVE(7),
    O_NEGATIVE(8);

    private final int value;

    BloodType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
