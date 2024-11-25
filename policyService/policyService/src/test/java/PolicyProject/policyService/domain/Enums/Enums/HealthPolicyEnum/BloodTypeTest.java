package PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BloodTypeTest {

    @Test
    public void testGetValue_A_POSITIVE() {
        BloodType bloodType = BloodType.A_POSITIVE;
        int expected = 1;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_A_NEGATIVE() {
        BloodType bloodType = BloodType.A_NEGATIVE;
        int expected = 2;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_B_POSITIVE() {
        BloodType bloodType = BloodType.B_POSITIVE;
        int expected = 3;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_B_NEGATIVE() {
        BloodType bloodType = BloodType.B_NEGATIVE;
        int expected = 4;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_AB_POSITIVE() {
        BloodType bloodType = BloodType.AB_POSITIVE;
        int expected = 5;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_AB_NEGATIVE() {
        BloodType bloodType = BloodType.AB_NEGATIVE;
        int expected = 6;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_O_POSITIVE() {
        BloodType bloodType = BloodType.O_POSITIVE;
        int expected = 7;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetValue_O_NEGATIVE() {
        BloodType bloodType = BloodType.O_NEGATIVE;
        int expected = 8;
        int actual = bloodType.getValue();
        assertEquals(expected, actual);
    }
}