package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@Builder
@Entity
@Table(name = "Weights")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weights {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String keys;
    double value;



    //YAŞ
    double AGE_18_25;
    double AGE_25_65;
    double AGE_65_UP;

    //CİNSİYET

    double MALE;
    double FEMALE;

    //MÜŞTERİ BASAMAĞI
    double GRADE_1;
    double GRADE_2;
    double GRADE_3;
    double GRADE_4;
    double GRADE_5;
    double GRADE_6;
    double GRADE_7;
    double GRADE_8;

    //ARAÇ MOTOR SINIFI

    double ENGINE_0_1300;
    double ENGINE_1301_1600;
    double ENGINE_1601_1800;
    double ENGINE_1801_2000;
    double ENGINE_2001_2500;
    double ENGINE_2501_3000;
    double ENGINE_3001_4000;
    double ENGINE_4001_UP;

    //ARAÇ BEDEL ARALIĞI

    double PRICE_0_500000;
    double PRICE_500001_1000000;
    double PRICE_1000001_2000000;
    double PRICE_2000001_3000000;
    double PRICE_3000001_4000000;
    double PRICE_4000001_UP;

    //ARAÇ YAŞ ARALIĞI

    double CAR_AGE_0_1;
    double CAR_AGE_2_5;
    double CAR_AGE_6_10;
    double CAR_AGE_11_15;
    double CAR_AGE_16_20;
    double CAR_AGE_20_UP;

    //ARAÇ TONAJ ARALIĞI VE TİPİ

    double CAR_M1_CLASS;
    double CAR_M2_CLASS;
    double CAR_M3_CLASS;

    double CAR_N1_CLASS;
    double CAR_N2_CLASS;
    double CAR_N3_CLASS;

    double CAR_O1_CLASS;
    double CAR_O2_CLASS;
    double CAR_O3_CLASS;
    double CAR_O4_CLASS;

    //SABİTELER

    double BASE;
    double EURO;

}
