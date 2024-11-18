package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.domain.Enums.Enums.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.CoverageType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.*;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Address;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.Building;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.*;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.CarRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.CarPolicy.LicensePlateRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.AddressRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.BuildingRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.EarthquakePolicy.HouseRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.AuxiliaryRepository.HealthPolicy.PersonalHealthRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.CarPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.WeightsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class DatabaseSeed implements CommandLineRunner {

    private final CarPolicyRepository carPolicyrepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final LicensePlateRepository licensePlateRepository;
    private final WeightsRepository weightsRepository;
    private final CoverageRepository coverageRepository;
    private final HouseRepository houseRepository;
    private final BuildingRepository buildingRepository;
    private final AddressRepository addressRepository;
    private final EarthQuakeWeightsRepository earthQuakeWeightsRepository;
    private final EarthQuakeRepository earthQuakeRepository;
    private final HealthPolicyWeightRepository healthPolicyWeightRepository;
    private final HealthPolicyRepository healthPolicyRepository;
    private final PersonalHealthRepository personalHealthRepository;

    // Constructor
    public DatabaseSeed(CarPolicyRepository carPolicyrepository,
                        CustomerRepository customerRepository,
                        CarRepository carRepository,
                        LicensePlateRepository licensePlateRepository,
                        WeightsRepository weightsRepository,
                        CoverageRepository coverageRepository,
                        HouseRepository houseRepository,
                        BuildingRepository buildingRepository,
                        AddressRepository addressRepository,
                        EarthQuakeWeightsRepository earthQuakeWeightsRepository,
                        EarthQuakeRepository earthQuakeRepository,
                        HealthPolicyWeightRepository healthPolicyWeightRepository,
                        HealthPolicyRepository healthPolicyRepository,
                        PersonalHealthRepository personalHealthRepository) {
        this.carPolicyrepository = carPolicyrepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.weightsRepository = weightsRepository;
        this.coverageRepository = coverageRepository;
        this.houseRepository = houseRepository;
        this.buildingRepository = buildingRepository;
        this.addressRepository = addressRepository;
        this.earthQuakeWeightsRepository = earthQuakeWeightsRepository;
        this.earthQuakeRepository = earthQuakeRepository;
        this.healthPolicyWeightRepository = healthPolicyWeightRepository;
        this.healthPolicyRepository = healthPolicyRepository;
        this.personalHealthRepository = personalHealthRepository;

    }


    @Override
    public void run(String... args) throws Exception {

        boolean condition = false;

        if (customerRepository.count() == 0 || condition) {
            seedDataCustomer();
            System.out.println("CUSTOMER SEED IS COMPLETED");
        }
        if (addressRepository.count() == 0 || condition) {
            seedAddress();
            System.out.println("ADDRESS SEED IS COMPLETED");
        }
        if (buildingRepository.count() == 0 || condition) {
            seedBuildings();
            System.out.println("BUİLDİNG SEED IS COMPLETED");
        }
        if (houseRepository.count() == 0 || condition) {
            seedHouse();
            System.out.println("HOUSE SEED IS COMPLETED");
        }
        if (carRepository.count() == 0 || condition) {
            seedCar();
            System.out.println("CAR SEED IS COMPLETED");
        }
        if (licensePlateRepository.count() == 0 || condition) {
            seedLicensePlate();
            System.out.println("LICENSE_PLATE SEED IS COMPLETED");
        }
        if (coverageRepository.count() == 0 || condition) {
            seedCoverage();
            System.out.println("COVERAGE SEED IS COMPLETED");
        }
        if (carPolicyrepository.count() == 0 || condition) {
            if (coverageRepository.count() != 0) {
                seedDataCarPolicies();
                System.out.println("CAR_POLICY SEED IS COMPLETED");
            }

        }
        if (weightsRepository.count() == 0 || condition) {
            seedDataWeights();
            System.out.println("WEIGHTS SEED IS COMPLETED");
        }
        if (earthQuakeWeightsRepository.count() == 0 || condition) {
            seedEarthquakeWeight();
            System.out.println("EARTHQUAKE_WEIGHTS SEED IS COMPLETED");
        }
        if (earthQuakeRepository.count() == 0 || condition) {
            seedDataEarthquakePolicies();
            System.out.println("EARTHQUAKE SEED IS COMPLETED");
        }
        if (personalHealthRepository.count() == 0 || condition) {
            seedPersonalHealth();
            System.out.println("PERSONAL HEALTH SEED IS COMPLETED");
        }
        if (healthPolicyWeightRepository.count() == 0 || condition) {
            seedHealthPolicyWeight();
            System.out.println("HEALTH WEIGHT SEED IS COMPLETED");
        }
        if (healthPolicyRepository.count() == 0 || condition) {
            seedDataHealthPolicies();
            System.out.println("HEALTH POLICY WEIGHT SEED IS COMPLETED");
        }
        System.out.println("DATABASE SEED IS COMPLETED");
    }

    private void seedCar() {
        List<Car> cars = new ArrayList<>();

        cars.add(Car.builder()
                .make("Toyota")
                .model("Corolla")
                .year(2020)
                .engine(1800)
                .kilometers(25000)
                .price(18000)
                .build());

        cars.add(Car.builder()
                .make("Honda")
                .model("Civic")
                .year(2019)
                .engine(1800)
                .kilometers(30000)
                .price(22000)
                .build());

        cars.add(Car.builder()
                .make("Ford")
                .model("Focus")
                .year(2021)
                .engine(3300)
                .kilometers(15000)
                .price(20000)
                .build());

        cars.add(Car.builder()
                .make("BMW")
                .model("320i")
                .year(2022)
                .engine(3300)
                .kilometers(10000)
                .price(35000)
                .build());

        cars.add(Car.builder()
                .make("Mercedes")
                .model("A-Class")
                .year(2023)
                .engine(3300)
                .kilometers(5000)
                .price(40000)
                .build());

        cars.add(Car.builder()
                .make("Audi")
                .model("A4")
                .year(2018)
                .engine(4200)
                .kilometers(40000)
                .price(25000)
                .build());

        cars.add(Car.builder()
                .make("Volkswagen")
                .model("Golf")
                .year(2021)
                .engine(4200)
                .kilometers(12000)
                .price(21000)
                .build());

        cars.add(Car.builder()
                .make("Hyundai")
                .model("Elantra")
                .year(2020)
                .engine(4200)
                .kilometers(22000)
                .price(19000)
                .build());

        cars.add(Car.builder()
                .make("Kia")
                .model("Forte")
                .year(2019)
                .engine(1500)
                .kilometers(28000)
                .price(17000)
                .build());

        cars.add(Car.builder()
                .make("Nissan")
                .model("Altima")
                .year(2022)
                .engine(1500)
                .kilometers(8000)
                .price(23000)
                .build());

        cars.add(Car.builder()
                .make("Chevrolet")
                .model("Malibu")
                .year(2021)
                .engine(1500)
                .kilometers(18000)
                .price(19500)
                .build());

        cars.add(Car.builder()
                .make("Subaru")
                .model("Impreza")
                .year(2020)
                .engine(1500)
                .kilometers(24000)
                .price(21000)
                .build());

        cars.add(Car.builder()
                .make("Mazda")
                .model("Mazda3")
                .year(2021)
                .engine(2500)
                .kilometers(11000)
                .price(20500)
                .build());

        cars.add(Car.builder()
                .make("Tesla")
                .model("Model 3")
                .year(2023)
                .engine(2500)
                .kilometers(3000)
                .price(45000)
                .build());

        cars.add(Car.builder()
                .make("Porsche")
                .model("Macan")
                .year(2022)
                .engine(2500)
                .kilometers(6000)
                .price(55000)
                .build());

        cars.add(Car.builder()
                .make("Lexus")
                .model("IS")
                .year(2021)
                .engine(2500)
                .kilometers(12000)
                .price(32000)
                .build());

        cars.add(Car.builder()
                .make("Chrysler")
                .model("300")
                .year(2019)
                .engine(2500)
                .kilometers(35000)
                .price(26000)
                .build());

        cars.add(Car.builder()
                .make("Buick")
                .model("Regal")
                .year(2020)
                .engine(2500)
                .kilometers(28000)
                .price(23000)
                .build());

        cars.add(Car.builder()
                .make("Land Rover")
                .model("Range Rover")
                .year(2022)
                .engine(5200)
                .kilometers(5000)
                .price(90000)
                .build());

        cars.add(Car.builder()
                .make("Jaguar")
                .model("XE")
                .year(2021)
                .engine(5200)
                .kilometers(9000)
                .price(40000)
                .build());

        cars.add(Car.builder()
                .make("Mitsubishi")
                .model("Lancer")
                .year(2018)
                .engine(2200)
                .kilometers(45000)
                .price(15000)
                .build());

        cars.add(Car.builder()
                .make("Volvo")
                .model("S60")
                .year(2020)
                .engine(2200)
                .kilometers(15000)
                .price(35000)
                .build());

        cars.add(Car.builder()
                .make("Infiniti")
                .model("Q50")
                .year(2021)
                .engine(2200)
                .kilometers(8000)
                .price(33000)
                .build());

        cars.add(Car.builder()
                .make("Acura")
                .model("TLX")
                .year(2022)
                .engine(2200)
                .kilometers(7000)
                .price(36000)
                .build());

        cars.add(Car.builder()
                .make("Fiat")
                .model("500")
                .year(2019)
                .engine(1200)
                .kilometers(30000)
                .price(12000)
                .build());

        cars.add(Car.builder()
                .make("Peugeot")
                .model("308")
                .year(2020)
                .engine(1200)
                .kilometers(15000)
                .price(18000)
                .build());

        cars.add(Car.builder()
                .make("Renault")
                .model("Clio")
                .year(2019)
                .engine(1200)
                .kilometers(32000)
                .price(15000)
                .build());

        cars.add(Car.builder()
                .make("Citroën")
                .model("C3")
                .year(2020)
                .engine(1000)
                .kilometers(27000)
                .price(16000)
                .build());

        cars.add(Car.builder()
                .make("Skoda")
                .model("Octavia")
                .year(2021)
                .engine(1000)
                .kilometers(14000)
                .price(19000)
                .build());

        cars.add(Car.builder()
                .make("Dacia")
                .model("Sandero")
                .year(2020)
                .engine(1000)
                .kilometers(35000)
                .price(13000)
                .build());


        carRepository.saveAll(cars);
    }

    private void seedLicensePlate() {
        List<LicensePlate> licensePlates = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Long carId = (long) (i % 20 + 1);
            Long customerId = (long) (i % 20 + 1);

            Optional<Car> optionalCar = carRepository.findById(carId);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCar.isPresent() && optionalCustomer.isPresent()) {
                Car car = optionalCar.get();
                Customer customer = optionalCustomer.get();
                licensePlates.add(LicensePlate.builder()
                        .plate("34ABC" + String.format("%04d", i))
                        .car(car)
                        .customer(customer)
                        .build());
            }
        }
        licensePlateRepository.saveAll(licensePlates);
    }

    private void seedDataCustomer() {

        List<Customer> customers = new ArrayList<>();
        Set<String> uniqueTCKNs = new HashSet<>();

        Random random = new Random();

        while (uniqueTCKNs.size() < 20) {
            String tckn = generateValidTCKN(random);
            uniqueTCKNs.add(tckn);
        }
        List<String> names = Arrays.asList(
                "Ahmet Yılmaz", "Mehmet Kaya", "Ayşe Demir", "Fatma Çelik",
                "Ali Şahin", "Emre Koç", "Zeynep Aksoy", "Hasan Özkan",
                "Hüseyin Aydın", "Hatice Korkmaz", "Mustafa Taş", "Elif Yıldız",
                "Kemal Arslan", "Burak Güneş", "Serkan Karaca", "Ayhan Kaplan",
                "Cemre Yılmaz", "Sevgi Öztürk", "Merve Karadeniz", "Berk Ünal"
        );
        List<String> cities = Arrays.asList(
                "İstanbul", "Ankara", "İzmir", "Bursa", "Antalya",
                "Adana", "Konya", "Gaziantep", "Kayseri", "Eskişehir"
        );
        Iterator<String> tcknIterator = uniqueTCKNs.iterator();
        for (int i = 0; i < 20; i++) {
            String city = cities.get(random.nextInt(cities.size()));
            customers.add(Customer.builder()
                    .name(names.get(i))
                    .tckn(tcknIterator.next())
                    .address(city + ", Türkiye")
                    .phone("532" + String.format("%07d", random.nextInt(1_000_0000)))
                    .email(names.get(i).replaceAll("\\s", "") + "@example.com")
                    .password("password" + (i + 1))
                    .birthDay(LocalDate.of(1990 + random.nextInt(10), random.nextInt(12) + 1, random.nextInt(28) + 1))
                    .gender(random.nextInt(2))
                    .grade(random.nextInt(7))
                    .build());
        }


        customerRepository.saveAll(customers);
    }

    private void seedDataCarPolicies() {

        PolicyState state;
        Random random = new Random();

        List<CarPolicy> carPolicies = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long licensePlateId = (long) (i % 100 + 1);
            Long coverageId = (long) (i % 2 == 0 ? 1 : 2);

            Optional<Coverage> optionalCoverage = coverageRepository.findById(coverageId);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<LicensePlate> optionalLicensePlate = licensePlateRepository.findById(licensePlateId);

            if (optionalCustomer.isPresent() && optionalLicensePlate.isPresent() && optionalCoverage.isPresent()) {
                LicensePlate licensePlate = optionalLicensePlate.get();
                Customer customer = licensePlate.getCustomer();
                Coverage coverage = optionalCoverage.get();
                LocalDate startDate = LocalDate.of(2024, 10, 1);
                LocalDate policyLocalDate = startDate.plusDays(i);

                state = PolicyState.values()[random.nextInt(PolicyState.values().length)];

                CarPolicy policy = CarPolicy.builder()
                        .policyDescription("Açıklama " + i)

                        .policyStartDate(policyLocalDate)
                        .policyEndDate(policyLocalDate.plusYears(1))
                        .policyAmount(1000.0 + (i * 100))
                        .policyOfferDate(policyLocalDate)
                        .licensePlate(licensePlate)
                        .customer(customer)
                        .coverage(coverage)
                        .state(state)
                        .build();

                carPolicies.add(policy);
            }
        }
        carPolicyrepository.saveAll(carPolicies);
    }

    private void seedDataWeights() {
        weightsRepository.saveAll(List.of(
                // YAŞ
                Weights.builder().key("AGE_18_25").weight(new BigDecimal("1.2")).minValue(new BigDecimal("18")).maxValue(new BigDecimal("25")).type("CUSTOMER_AGE").build(),
                Weights.builder().key("AGE_25_65").weight(new BigDecimal("1.5")).minValue(new BigDecimal("25")).maxValue(new BigDecimal("65")).type("CUSTOMER_AGE").build(),
                Weights.builder().key("AGE_65_UP").weight(new BigDecimal("1.8")).minValue(new BigDecimal("65")).maxValue(new BigDecimal("100")).type("CUSTOMER_AGE").build(),

                // CİNSİYET
                Weights.builder().key("MALE").weight(new BigDecimal("1.1")).minValue(BigDecimal.ZERO).maxValue(BigDecimal.ONE).type("GENDER").build(),
                Weights.builder().key("FEMALE").weight(new BigDecimal("1.0")).minValue(new BigDecimal(2)).maxValue(new BigDecimal(3)).type("GENDER").build(),

                // MÜŞTERİ BASAMAĞI
                Weights.builder().key("GRADE_1").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("1.0")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_2").weight(new BigDecimal("1.2")).minValue(new BigDecimal("0.1")).maxValue(new BigDecimal("1.1")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_3").weight(new BigDecimal("1.4")).minValue(new BigDecimal("0.2")).maxValue(new BigDecimal("1.2")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_4").weight(new BigDecimal("1.6")).minValue(new BigDecimal("0.3")).maxValue(new BigDecimal("1.3")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_5").weight(new BigDecimal("1.8")).minValue(new BigDecimal("0.4")).maxValue(new BigDecimal("1.4")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_6").weight(new BigDecimal("2.0")).minValue(new BigDecimal("0.5")).maxValue(new BigDecimal("1.5")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_7").weight(new BigDecimal("2.2")).minValue(new BigDecimal("0.6")).maxValue(new BigDecimal("1.6")).type("CUSTOMER_GRADE").build(),
                Weights.builder().key("GRADE_8").weight(new BigDecimal("2.5")).minValue(new BigDecimal("0.7")).maxValue(new BigDecimal("1.7")).type("CUSTOMER_GRADE").build(),

                // ARAÇ MOTOR SINIFI
                Weights.builder().key("ENGINE_0_1300").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("1300")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_1301_1600").weight(new BigDecimal("1.2")).minValue(new BigDecimal("1301")).maxValue(new BigDecimal("1600")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_1601_1800").weight(new BigDecimal("1.5")).minValue(new BigDecimal("1601")).maxValue(new BigDecimal("1800")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_1801_2000").weight(new BigDecimal("1.8")).minValue(new BigDecimal("1801")).maxValue(new BigDecimal("2000")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_2001_2500").weight(new BigDecimal("2.0")).minValue(new BigDecimal("2001")).maxValue(new BigDecimal("2500")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_2501_3000").weight(new BigDecimal("2.3")).minValue(new BigDecimal("2501")).maxValue(new BigDecimal("3000")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_3001_4000").weight(new BigDecimal("2.7")).minValue(new BigDecimal("3001")).maxValue(new BigDecimal("4000")).type("ENGINE").build(),
                Weights.builder().key("ENGINE_4001_UP").weight(new BigDecimal("3.0")).minValue(new BigDecimal("4001")).maxValue(new BigDecimal("10000")).type("ENGINE").build(),

                // ARAÇ BEDEL ARALIĞI
                Weights.builder().key("PRICE_0_500000").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("500000")).type("CAR_PRICE").build(),
                Weights.builder().key("PRICE_500001_1000000").weight(new BigDecimal("1.5")).minValue(new BigDecimal("500001")).maxValue(new BigDecimal("1000000")).type("CAR_PRICE").build(),
                Weights.builder().key("PRICE_1000001_2000000").weight(new BigDecimal("2.0")).minValue(new BigDecimal("1000001")).maxValue(new BigDecimal("2000000")).type("CAR_PRICE").build(),
                Weights.builder().key("PRICE_2000001_3000000").weight(new BigDecimal("2.5")).minValue(new BigDecimal("2000001")).maxValue(new BigDecimal("3000000")).type("CAR_PRICE").build(),
                Weights.builder().key("PRICE_3000001_4000000").weight(new BigDecimal("3.0")).minValue(new BigDecimal("3000001")).maxValue(new BigDecimal("4000000")).type("CAR_PRICE").build(),
                Weights.builder().key("PRICE_4000001_UP").weight(new BigDecimal("3.5")).minValue(new BigDecimal("4000001")).maxValue(new BigDecimal("10000000")).type("CAR_PRICE").build(),

                // ARAÇ YAŞ ARALIĞI
                Weights.builder().key("CAR_AGE_0_1").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("1")).type("CAR_AGE").build(),
                Weights.builder().key("CAR_AGE_2_5").weight(new BigDecimal("1.3")).minValue(new BigDecimal("2")).maxValue(new BigDecimal("5")).type("CAR_AGE").build(),
                Weights.builder().key("CAR_AGE_6_10").weight(new BigDecimal("1.5")).minValue(new BigDecimal("6")).maxValue(new BigDecimal("10")).type("CAR_AGE").build(),
                Weights.builder().key("CAR_AGE_11_15").weight(new BigDecimal("1.7")).minValue(new BigDecimal("11")).maxValue(new BigDecimal("15")).type("CAR_AGE").build(),
                Weights.builder().key("CAR_AGE_16_20").weight(new BigDecimal("2.0")).minValue(new BigDecimal("16")).maxValue(new BigDecimal("20")).type("CAR_AGE").build(),
                Weights.builder().key("CAR_AGE_20_UP").weight(new BigDecimal("2.5")).minValue(new BigDecimal("21")).maxValue(new BigDecimal("100")).type("CAR_AGE").build(),

                // ARAÇ TONAJ ARALIĞI VE TİPİ
                Weights.builder().key("CAR_M1_CLASS").weight(new BigDecimal("1.1")).minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("1.0")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_M2_CLASS").weight(new BigDecimal("1.3")).minValue(new BigDecimal("0.1")).maxValue(new BigDecimal("1.1")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_M3_CLASS").weight(new BigDecimal("1.6")).minValue(new BigDecimal("0.2")).maxValue(new BigDecimal("1.2")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_N1_CLASS").weight(new BigDecimal("1.2")).minValue(new BigDecimal("0.3")).maxValue(new BigDecimal("1.3")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_N2_CLASS").weight(new BigDecimal("1.5")).minValue(new BigDecimal("0.4")).maxValue(new BigDecimal("1.4")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_N3_CLASS").weight(new BigDecimal("2.0")).minValue(new BigDecimal("0.5")).maxValue(new BigDecimal("1.5")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_O1_CLASS").weight(new BigDecimal("1.4")).minValue(new BigDecimal("0.6")).maxValue(new BigDecimal("1.6")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_O2_CLASS").weight(new BigDecimal("1.6")).minValue(new BigDecimal("0.7")).maxValue(new BigDecimal("1.7")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_O3_CLASS").weight(new BigDecimal("2.2")).minValue(new BigDecimal("0.8")).maxValue(new BigDecimal("1.8")).type("CAR_TYPE").build(),
                Weights.builder().key("CAR_O4_CLASS").weight(new BigDecimal("2.5")).minValue(new BigDecimal("0.9")).maxValue(new BigDecimal("1.9")).type("CAR_TYPE").build(),


                // SABİTELER
                Weights.builder().key("BASE").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0.0")).maxValue(new BigDecimal("1.0")).type("CONSTANT").build(),
                Weights.builder().key("EURO").weight(new BigDecimal("1.1")).minValue(new BigDecimal("0.1")).maxValue(new BigDecimal("1.1")).type("CONSTANT").build(),

                // POLİCY TYPE
                Weights.builder().key("KASKO").weight(new BigDecimal("1.7")).minValue(new BigDecimal("0.2")).maxValue(new BigDecimal("1.2")).type("POLICY_TYPE").build(),
                Weights.builder().key("TRAFİK").weight(new BigDecimal("1.0")).minValue(new BigDecimal("0.3")).maxValue(new BigDecimal("1.3")).type("POLICY_TYPE").build()
        ));
    }

    private void seedCoverage() {
        List<Coverage> coverages = new ArrayList<>();

        Coverage coverage2 = Coverage.builder()
                .coverageType(CoverageType.TRAFİK)
                .coverageDescription("Trafik sigortası, trafikte yaşanabilecek kazalarda üçüncü şahıslara verebileceğiniz zararları karşılayan bir zorunluluk sigortasıdır.")
                .build();
        coverages.add(coverage2);

        Coverage coverage = Coverage.builder()
                .coverageType(CoverageType.KASKO)
                .coverageDescription("Kasko sigortası, aracınızı kazalar, hırsızlık ya da doğal afetler gibi beklenmedik durumlara karşı güvence altına alır.")
                .build();
        coverages.add(coverage);

        Coverage coverage3 = Coverage.builder()
                .coverageType(CoverageType.YARI_KAPSAM)
                .coverageDescription("Yarı kapsamlı deprem sigortası, olası bir depremde evinizde meydana gelen zararların bir kısmını karşılayan ekonomik bir güvence sunar.")
                .build();
        coverages.add(coverage3);

        Coverage coverage4 = Coverage.builder()
                .coverageType(CoverageType.TAM_KAPSAM)
                .coverageDescription("Tam kapsamlı deprem sigortası, depremin neden olabileceği her türlü maddi zararı tamamen karşılayan geniş bir güvence sağlar.")
                .build();
        coverages.add(coverage4);

        Coverage coverage5 = Coverage.builder()
                .coverageType(CoverageType.AYAKTA_TEDAVİ)
                .coverageDescription("Ayakta tedavi sigortası, muayene, reçeteli ilaçlar ve ayakta yapılan diğer tıbbi işlemlerde sizi destekler.")
                .build();
        coverages.add(coverage5);

        Coverage coverage6 = Coverage.builder()
                .coverageType(CoverageType.YATARAK_TEDAVİ)
                .coverageDescription("Yatarak tedavi sigortası, hastanede yatarak gerçekleştirilen tedavi ve ameliyat masraflarını güvence altına alır.")
                .build();
        coverages.add(coverage6);

        Coverage coverage7 = Coverage.builder()
                .coverageType(CoverageType.SEYEHAT)
                .coverageDescription("Seyahat sigortası, seyahatleriniz sırasında karşılaşabileceğiniz sağlık sorunları, bagaj kaybı ya da diğer beklenmedik durumlarda sizi korur.")
                .build();
        coverages.add(coverage7);

        coverageRepository.saveAll(coverages);

    }

    private void seedBuildings() {
        Random random = new Random();
        for (int i = 1; i <= 137; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long addressId = (long) (i % 46 + 1);

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<Address> optionalAddress = addressRepository.findById(addressId);
            if (optionalCustomer.isPresent() && optionalAddress.isPresent()) {
                Customer customer = optionalCustomer.get();
                Address address = optionalAddress.get();
                int randomNumber = random.nextInt(10) + 1;

                int randomYear = random.nextInt(2024 - 1980 + 1) + 1980;
                Building building = Building.builder()
                        .apartmentNumber(i % 3 + 1)
                        .constructionStyle(i % 2 + 1)
                        .totalFloors(randomNumber)
                        .constructionYear(randomYear)
                        .address(address)
                        .build();

                buildingRepository.save(building);
            }
        }
    }

    private void seedAddress() {
        List<Address> addresses = List.of(
                new Address(null, 2, "Acıbadem", "Kadıköy", "İstanbul", null),
                new Address(null, 3, "Fenerbahçe", "Kadıköy", "İstanbul", null),
                new Address(null, 4, "Kozyatağı", "Kadıköy", "İstanbul", null),
                new Address(null, 5, "Etiler", "Beşiktaş", "İstanbul", null),
                new Address(null, 6, "Levent", "Beşiktaş", "İstanbul", null),
                new Address(null, 7, "Ortaköy", "Beşiktaş", "İstanbul", null),
                new Address(null, 1, "Çengelköy", "Üsküdar", "İstanbul", null),
                new Address(null, 2, "Kuzguncuk", "Üsküdar", "İstanbul", null),
                new Address(null, 3, "Beylerbeyi", "Üsküdar", "İstanbul", null),
                new Address(null, 4, "Ataköy", "Bakırköy", "İstanbul", null),
                new Address(null, 5, "Yeşilköy", "Bakırköy", "İstanbul", null),
                new Address(null, 6, "Florya", "Bakırköy", "İstanbul", null),
                new Address(null, 7, "Maçka", "Şişli", "İstanbul", null),
                new Address(null, 1, "Nişantaşı", "Şişli", "İstanbul", null),
                new Address(null, 2, "Bomonti", "Şişli", "İstanbul", null),
                new Address(null, 3, "Bahçelievler", "Çankaya", "Ankara", null),
                new Address(null, 4, "Çayyolu", "Çankaya", "Ankara", null),
                new Address(null, 5, "Kızılay", "Çankaya", "Ankara", null),
                new Address(null, 6, "Etlik", "Keçiören", "Ankara", null),
                new Address(null, 7, "Kuşcağız", "Keçiören", "Ankara", null),
                new Address(null, 1, "Bağlum", "Keçiören", "Ankara", null),
                new Address(null, 2, "Demetevler", "Yenimahalle", "Ankara", null),
                new Address(null, 3, "Batıkent", "Yenimahalle", "Ankara", null),
                new Address(null, 4, "İvedik", "Yenimahalle", "Ankara", null),
                new Address(null, 5, "BüyükKayaş", "Mamak", "Ankara", null),
                new Address(null, 6, "ŞahinTepesi", "Mamak", "Ankara", null),
                new Address(null, 7, "Gökçek", "Mamak", "Ankara", null),
                new Address(null, 1, "Alsancak", "Konak", "İzmir", null),
                new Address(null, 2, "Kemeraltı", "Konak", "İzmir", null),
                new Address(null, 3, "Basmane", "Konak", "İzmir", null),
                new Address(null, 4, "Bostanlı", "Karşıyaka", "İzmir", null),
                new Address(null, 5, "Çarşı", "Karşıyaka", "İzmir", null),
                new Address(null, 6, "Yalı", "Karşıyaka", "İzmir", null),
                new Address(null, 7, "Ege Üniversitesi", "Bornova", "İzmir", null),
                new Address(null, 1, "Işıkkent", "Bornova", "İzmir", null),
                new Address(null, 2, "Köyceğiz", "Bornova", "İzmir", null),
                new Address(null, 3, "Kaleiçi", "Muratpaşa", "Antalya", null),
                new Address(null, 4, "Konyaaltı", "Muratpaşa", "Antalya", null),
                new Address(null, 5, "Lara", "Muratpaşa", "Antalya", null),
                new Address(null, 6, "Santral", "Kepez", "Antalya", null),
                new Address(null, 7, "Süleyman Demirel", "Kepez", "Antalya", null),
                new Address(null, 1, "Aksu", "Kepez", "Antalya", null),
                new Address(null, 2, "Mahmutlar", "Alanya", "Antalya", null),
                new Address(null, 3, "Oba", "Alanya", "Antalya", null),
                new Address(null, 4, "Kestel", "Alanya", "Antalya", null)
        );
        addressRepository.saveAll(addresses);
    }

    private void seedHouse() {
        Random random = new Random();
        for (int i = 1; i <= 138; i++) {
            Long customerId = (long) (i % 20 + 1);
            Long buildingId = (long) (i % 138 + 1);

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<Building> optionalBuilding = buildingRepository.findById(buildingId);

            if (optionalCustomer.isPresent() && optionalBuilding.isPresent()) {
                Customer customer = optionalCustomer.get();
                Building building = optionalBuilding.get();
                for (int j = 1; j <= 3; j++) {
                    int randomNumber = random.nextInt(300) + 1;
                    House house = House.builder()
                            .number(j)
                            .customer(customer)
                            .building(building)
                            .squareMeters(randomNumber)
                            .build();

                    houseRepository.save(house);
                }
            }
        }
    }

    private void seedPersonalHealth() {
        Random random = new Random();
        Map<Long, PersonalHealth> customerHealthMap = new HashMap<>();

        for (int i = 1; i <= 100; i++) {
            Long customerId = (long) (i % 20 + 1);

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();

                // Aynı müşteri için PersonalHealth verilerini kontrol et
                PersonalHealth existingHealth = customerHealthMap.get(customerId);

                if (existingHealth == null) {
                    // Eğer müşteri için daha önce sağlık verisi oluşturulmamışsa rastgele oluştur
                    int height = random.nextInt(51) + 150; // 150-200 cm arası boy
                    double weight = random.nextDouble() * 50 + 50; // 50-100 kg arası kilo
                    double bmi = Math.round((weight / Math.pow(height / 100.0, 2)) * 10.0) / 10.0; // BMI hesaplama
                    BloodType bloodType = BloodType.values()[random.nextInt(BloodType.values().length)];
                    boolean alcoholConsumption = random.nextBoolean();
                    boolean smokeConsumption = random.nextBoolean();
                    boolean isPregnant = random.nextBoolean();
                    boolean hasDisability = random.nextBoolean();
                    boolean hasPreviousSurgeries = random.nextBoolean();

                    existingHealth = PersonalHealth.builder()
                            .height(height)
                            .weight(weight)
                            .bmi(bmi)
                            .bloodType(bloodType)
                            .alcoholConsumption(alcoholConsumption)
                            .smokeConsumption(smokeConsumption)
                            .isPregnant(isPregnant)
                            .hasDisability(hasDisability)
                            .hasPreviousSurgeries(hasPreviousSurgeries)
                            .customer(customer)
                            .build();

                    customerHealthMap.put(customerId, existingHealth);
                }

                // Rastgele createdAt tarihi oluştur
                LocalDateTime startDate = LocalDateTime.now().minusMonths(5);
                long daysBetween = java.time.Duration.between(startDate, LocalDateTime.now()).toDays();
                LocalDateTime createdAt = startDate.plusDays(random.nextInt((int) daysBetween + 1));

                // Yeni bir PersonalHealth kaydı oluştur, mevcut sağlık verilerini kullanarak
                PersonalHealth personalHealth = PersonalHealth.builder()
                        .height(existingHealth.getHeight())
                        .weight(existingHealth.getWeight())
                        .bmi(existingHealth.getBmi())
                        .bloodType(existingHealth.getBloodType())
                        .alcoholConsumption(existingHealth.getAlcoholConsumption())
                        .smokeConsumption(existingHealth.getSmokeConsumption())
                        .isPregnant(existingHealth.getIsPregnant())
                        .hasDisability(existingHealth.getHasDisability())
                        .hasPreviousSurgeries(existingHealth.getHasPreviousSurgeries())
                        .createdAt(createdAt)
                        .customer(customer)
                        .build();

                personalHealthRepository.save(personalHealth);
            }
        }
    }

    private void seedEarthquakeWeight() {
        earthQuakeWeightsRepository.saveAll(List.of(
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_1").weight(new BigDecimal("3.2")).minValue(new BigDecimal("1")).maxValue(new BigDecimal("1")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_2").weight(new BigDecimal("2.8")).minValue(new BigDecimal("2")).maxValue(new BigDecimal("2")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_3").weight(new BigDecimal("4.1")).minValue(new BigDecimal("3")).maxValue(new BigDecimal("3")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_4").weight(new BigDecimal("3.7")).minValue(new BigDecimal("4")).maxValue(new BigDecimal("4")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_5").weight(new BigDecimal("3.9")).minValue(new BigDecimal("5")).maxValue(new BigDecimal("5")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_6").weight(new BigDecimal("4.3")).minValue(new BigDecimal("6")).maxValue(new BigDecimal("6")).type("EARTHQUAKERISK").build(),
                EarthQaukeWeights.builder().key("EARTHQUAKERISK_7").weight(new BigDecimal("2.5")).minValue(new BigDecimal("7")).maxValue(new BigDecimal("7")).type("EARTHQUAKERISK").build(),

                EarthQaukeWeights.builder().key("CONSTRUCTIONSTYLE_REINFORCED_CONCRETE").weight(new BigDecimal("5.0")).minValue(new BigDecimal("1")).maxValue(new BigDecimal("1")).type("CONSTRUCTION_STYLE").build(),
                EarthQaukeWeights.builder().key("CONSTRUCTIONSTYLE_OTHER").weight(new BigDecimal("3.0")).minValue(new BigDecimal("2")).maxValue(new BigDecimal("2")).type("CONSTRUCTION_STYLE").build(),

                EarthQaukeWeights.builder().key("CONSTRUCTION_YEAR_0_5").weight(new BigDecimal("3.5")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("5")).type("CONSTRUCTION_YEAR").build(),
                EarthQaukeWeights.builder().key("CONSTRUCTION_YEAR_6_10").weight(new BigDecimal("4.0")).minValue(new BigDecimal("6")).maxValue(new BigDecimal("10")).type("CONSTRUCTION_YEAR").build(),
                EarthQaukeWeights.builder().key("CONSTRUCTION_YEAR_11_20").weight(new BigDecimal("3.8")).minValue(new BigDecimal("11")).maxValue(new BigDecimal("20")).type("CONSTRUCTION_YEAR").build(),
                EarthQaukeWeights.builder().key("CONSTRUCTION_YEAR_21_UP").weight(new BigDecimal("4.5")).minValue(new BigDecimal("21")).maxValue(new BigDecimal("500")).type("CONSTRUCTION_YEAR").build(),

                EarthQaukeWeights.builder().key("TOTAL_FLOORS_0_3").weight(new BigDecimal("2.5")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("3")).type("TOTAL_FLOORS").build(),
                EarthQaukeWeights.builder().key("TOTAL_FLOORS_4_7").weight(new BigDecimal("3.0")).minValue(new BigDecimal("4")).maxValue(new BigDecimal("7")).type("TOTAL_FLOORS").build(),
                EarthQaukeWeights.builder().key("TOTAL_FLOORS_8_10").weight(new BigDecimal("3.3")).minValue(new BigDecimal("8")).maxValue(new BigDecimal("10")).type("TOTAL_FLOORS").build(),
                EarthQaukeWeights.builder().key("TOTAL_FLOORS_11_15").weight(new BigDecimal("4.0")).minValue(new BigDecimal("11")).maxValue(new BigDecimal("15")).type("TOTAL_FLOORS").build(),
                EarthQaukeWeights.builder().key("TOTAL_FLOORS_16_UP").weight(new BigDecimal("4.2")).minValue(new BigDecimal("16")).maxValue(new BigDecimal("100")).type("TOTAL_FLOORS").build(),

                EarthQaukeWeights.builder().key("SQUARE_METERS_0_60").weight(new BigDecimal("2.1")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("60")).type("SQUARE_METERS").build(),
                EarthQaukeWeights.builder().key("SQUARE_METERS_61_80").weight(new BigDecimal("3.0")).minValue(new BigDecimal("61")).maxValue(new BigDecimal("80")).type("SQUARE_METERS").build(),
                EarthQaukeWeights.builder().key("SQUARE_METERS_81_100").weight(new BigDecimal("3.5")).minValue(new BigDecimal("81")).maxValue(new BigDecimal("100")).type("SQUARE_METERS").build(),
                EarthQaukeWeights.builder().key("SQUARE_METERS_101_120").weight(new BigDecimal("4.0")).minValue(new BigDecimal("101")).maxValue(new BigDecimal("120")).type("SQUARE_METERS").build(),
                EarthQaukeWeights.builder().key("SQUARE_METERS_121_150").weight(new BigDecimal("4.3")).minValue(new BigDecimal("121")).maxValue(new BigDecimal("150")).type("SQUARE_METERS").build(),
                EarthQaukeWeights.builder().key("SQUARE_METERS_151_UP").weight(new BigDecimal("4.8")).minValue(new BigDecimal("151")).maxValue(new BigDecimal("5000")).type("SQUARE_METERS").build()



        ));
    }

    private void seedDataEarthquakePolicies() {

        PolicyState state;
        Random random = new Random();

        List<EarthquakePolicy> earthquakePolicies = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long houseId = (long) (i % 402 + 1);
            Long coverageId = (long) (i % 2 == 0 ? 3 : 4);

            Optional<Coverage> optionalCoverage = coverageRepository.findById(coverageId);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<House> optionalHouse = houseRepository.findById(houseId);

            if (optionalCustomer.isPresent() && optionalHouse.isPresent() && optionalCoverage.isPresent()) {
                House house = optionalHouse.get();
                Customer customer = house.getCustomer();
                Coverage coverage = optionalCoverage.get();

                LocalDate startDate = LocalDate.of(2024, 10, 1);
                LocalDate policyLocalDate = startDate.plusDays(i);

                state = PolicyState.values()[random.nextInt(PolicyState.values().length)];

                EarthquakePolicy policy = EarthquakePolicy.builder()
                        .policyDescription("Açıklama " + i)
                        .policyStartDate(policyLocalDate)
                        .policyEndDate(policyLocalDate.plusYears(1))
                        .policyAmount(1000.0 + (i * 100))
                        .policyOfferDate(policyLocalDate)
                        .house(house)
                        .customer(customer)
                        .coverage(coverage)
                        .state(state)
                        .build();

                earthquakePolicies.add(policy);
            }
        }
        earthQuakeRepository.saveAll(earthquakePolicies);
    }

    private void seedHealthPolicyWeight() {
        healthPolicyWeightRepository.saveAll(List.of(
                HealthPolicyWeight.builder().key("WEIGHT_0_50").weight(new BigDecimal("2.0")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("50")).type("WEIGHT").build(),
                HealthPolicyWeight.builder().key("WEIGHT_51_70").weight(new BigDecimal("3.0")).minValue(new BigDecimal("51")).maxValue(new BigDecimal("70")).type("WEIGHT").build(),
                HealthPolicyWeight.builder().key("WEIGHT_71_90").weight(new BigDecimal("4.0")).minValue(new BigDecimal("71")).maxValue(new BigDecimal("90")).type("WEIGHT").build(),
                HealthPolicyWeight.builder().key("WEIGHT_91_UP").weight(new BigDecimal("5.0")).minValue(new BigDecimal("91")).maxValue(new BigDecimal("300")).type("WEIGHT").build(),

                HealthPolicyWeight.builder().key("HEIGHT_0_150").weight(new BigDecimal("2.5")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("150")).type("HEIGHT").build(),
                HealthPolicyWeight.builder().key("HEIGHT_151_170").weight(new BigDecimal("3.0")).minValue(new BigDecimal("151")).maxValue(new BigDecimal("170")).type("HEIGHT").build(),
                HealthPolicyWeight.builder().key("HEIGHT_171_190").weight(new BigDecimal("3.5")).minValue(new BigDecimal("171")).maxValue(new BigDecimal("190")).type("HEIGHT").build(),
                HealthPolicyWeight.builder().key("HEIGHT_191_UP").weight(new BigDecimal("4.0")).minValue(new BigDecimal("191")).maxValue(new BigDecimal("250")).type("HEIGHT").build(),

                HealthPolicyWeight.builder().key("BMI_UNDERWEIGHT").weight(new BigDecimal("1.5")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("18.4")).type("BMI").build(),
                HealthPolicyWeight.builder().key("BMI_NORMAL").weight(new BigDecimal("3.0")).minValue(new BigDecimal("18.5")).maxValue(new BigDecimal("24.9")).type("BMI").build(),
                HealthPolicyWeight.builder().key("BMI_OVERWEIGHT").weight(new BigDecimal("3.5")).minValue(new BigDecimal("25.0")).maxValue(new BigDecimal("29.9")).type("BMI").build(),
                HealthPolicyWeight.builder().key("BMI_OBESE").weight(new BigDecimal("4.0")).minValue(new BigDecimal("30.0")).maxValue(new BigDecimal("50.0")).type("BMI").build(),

                HealthPolicyWeight.builder().key("BLOODTYPE_O_POSITIVE").weight(new BigDecimal("3.0")).minValue(new BigDecimal("7")).maxValue(new BigDecimal("7")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_A_POSITIVE").weight(new BigDecimal("3.5")).minValue(new BigDecimal("1")).maxValue(new BigDecimal("1")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_B_POSITIVE").weight(new BigDecimal("4.0")).minValue(new BigDecimal("3")).maxValue(new BigDecimal("3")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_AB_POSITIVE").weight(new BigDecimal("4.5")).minValue(new BigDecimal("5")).maxValue(new BigDecimal("5")).type("BLOODTYPE").build(),

                HealthPolicyWeight.builder().key("BLOODTYPE_O_NEGATIVE").weight(new BigDecimal("3.0")).minValue(new BigDecimal("8")).maxValue(new BigDecimal("8")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_A_NEGATIVE").weight(new BigDecimal("3.5")).minValue(new BigDecimal("2")).maxValue(new BigDecimal("2")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_B_NEGATIVE").weight(new BigDecimal("4.0")).minValue(new BigDecimal("4")).maxValue(new BigDecimal("4")).type("BLOODTYPE").build(),
                HealthPolicyWeight.builder().key("BLOODTYPE_AB_NEGATIVE").weight(new BigDecimal("4.5")).minValue(new BigDecimal("6")).maxValue(new BigDecimal("6")).type("BLOODTYPE").build(),


                HealthPolicyWeight.builder().key("GENDER_MALE").weight(new BigDecimal("3.0")).minValue(new BigDecimal("1")).maxValue(new BigDecimal("1")).type("GENDER").build(),
                HealthPolicyWeight.builder().key("GENDER_FEMALE").weight(new BigDecimal("3.5")).minValue(new BigDecimal("2")).maxValue(new BigDecimal("2")).type("GENDER").build(),

                HealthPolicyWeight.builder().key("AGE_0_20").weight(new BigDecimal("2.0")).minValue(new BigDecimal("0")).maxValue(new BigDecimal("20")).type("AGE").build(),
                HealthPolicyWeight.builder().key("AGE_21_40").weight(new BigDecimal("3.0")).minValue(new BigDecimal("21")).maxValue(new BigDecimal("40")).type("AGE").build(),
                HealthPolicyWeight.builder().key("AGE_41_60").weight(new BigDecimal("3.5")).minValue(new BigDecimal("41")).maxValue(new BigDecimal("60")).type("AGE").build(),
                HealthPolicyWeight.builder().key("AGE_61_UP").weight(new BigDecimal("4.0")).minValue(new BigDecimal("61")).maxValue(new BigDecimal("120")).type("AGE").build(),


                HealthPolicyWeight.builder().key("AYAKTA_TEDAVİ").weight(new BigDecimal("4.0")).minValue(new BigDecimal("105")).maxValue(new BigDecimal("105")).type("POLICY_TYPE").build(),
                HealthPolicyWeight.builder().key("YATARAK_TEDAVİ").weight(new BigDecimal("4.0")).minValue(new BigDecimal("106")).maxValue(new BigDecimal("106")).type("POLICY_TYPE").build(),
                HealthPolicyWeight.builder().key("SEYEHAT").weight(new BigDecimal("4.0")).minValue(new BigDecimal("107")).maxValue(new BigDecimal("107")).type("POLICY_TYPE").build()







                ));
    }

    private void seedDataHealthPolicies()
    {

        PolicyState state;
        Random random = new Random();

        List<HealthPolicy> healthPolicyList = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long personalHealthId = (long) (i % 100 + 1);
            Long coverageId = (long) (i % 3 == 1 ? 5 : (i % 3 == 2 ? 6 : 7));

            Optional<Coverage> optionalCoverage = coverageRepository.findById(coverageId);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<PersonalHealth> optionalPersonalHealth = personalHealthRepository.findById(personalHealthId);

            if (optionalCustomer.isPresent() && optionalPersonalHealth.isPresent() && optionalCoverage.isPresent()) {
                PersonalHealth personalHealth = optionalPersonalHealth.get();
                Customer customer = personalHealth.getCustomer();
                Coverage coverage = optionalCoverage.get();

                LocalDate startDate = LocalDate.of(2024, 10, 1);
                LocalDate policyLocalDate = startDate.plusDays(i);

                state = PolicyState.values()[random.nextInt(PolicyState.values().length)];

                HealthPolicy policy = HealthPolicy.builder()
                        .policyDescription("Açıklama " + i)
                        .policyStartDate(policyLocalDate)
                        .policyEndDate(policyLocalDate.plusYears(1))
                        .policyAmount(1000.0 + (i * 100))
                        .policyOfferDate(policyLocalDate)
                        .personalHealth(personalHealth)
                        .customer(customer)
                        .coverage(coverage)
                        .state(state)
                        .build();

                healthPolicyList.add(policy);
            }
        }
        healthPolicyRepository.saveAll(healthPolicyList);
    }

    private static String generateValidTCKN(Random random) {
        int[] digits = new int[11];

        digits[0] = random.nextInt(9) + 1;

        for (int i = 1; i < 9; i++) {
            digits[i] = random.nextInt(10);
        }
        int oddSum = digits[0] + digits[2] + digits[4] + digits[6] + digits[8];
        int evenSum = digits[1] + digits[3] + digits[5] + digits[7];
        digits[9] = ((7 * oddSum) - evenSum) % 10;

        int totalSum = 0;
        for (int i = 0; i < 10; i++) {
            totalSum += digits[i];
        }
        digits[10] = totalSum % 10;
        StringBuilder tcknBuilder = new StringBuilder();
        for (int digit : digits) {
            tcknBuilder.append(digit);
        }
        return tcknBuilder.toString();
    }
}


