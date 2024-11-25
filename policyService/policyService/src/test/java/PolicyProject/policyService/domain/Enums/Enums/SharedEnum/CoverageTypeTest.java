package PolicyProject.policyService.domain.Enums.Enums.SharedEnum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoverageTypeTest {

    @Test
    public void testFromCode_ValidTrafik() {
        int code = 101;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.TRAFİK, result);
    }


    @Test
    public void testFromCode_InvalidLowerBoundary() {
        int code = 100;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverageType.fromCode(code);
        });
        assertEquals("Invalid CoverageType code: 100", exception.getMessage());
    }

    @Test
    public void testFromCode_InvalidUpperBoundary() {
        int code = 111;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverageType.fromCode(code);
        });
        assertEquals("Invalid CoverageType code: 111", exception.getMessage());
    }

    @Test
    public void testFromCode_ValidKasko() {
        int code = 102;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.KASKO, result);
    }

    @Test
    public void testFromCode_ValidYariKapsam() {
        int code = 103;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.YARI_KAPSAM, result);
    }

    @Test
    public void testFromCode_ValidTamKapsam() {
        int code = 104;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.TAM_KAPSAM, result);
    }

    @Test
    public void testFromCode_ValidAyaktaTedavi() {
        int code = 105;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.AYAKTA_TEDAVİ, result);
    }

    @Test
    public void testFromCode_ValidYatarakTedavi() {
        int code = 106;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.YATARAK_TEDAVİ, result);
    }

    @Test
    public void testFromCode_ValidSeyehat() {
        int code = 107;
        CoverageType result = CoverageType.fromCode(code);
        assertEquals(CoverageType.SEYEHAT, result);
    }

    @Test
    public void testFromCode_InvalidCode() {
        int code = 999;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            CoverageType.fromCode(code);
        });
        assertEquals("Invalid CoverageType code: 999", exception.getMessage());
    }
}