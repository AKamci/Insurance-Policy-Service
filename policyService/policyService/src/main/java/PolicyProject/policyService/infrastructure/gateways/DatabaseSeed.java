package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.*;
import PolicyProject.policyService.infrastructure.persistence.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
public class DatabaseSeed implements CommandLineRunner {

    private final CarPolicyRepository carPolicyrepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final LicensePlateRepository licensePlateRepository;
    private final WeightsRepository weightsRepository;

    // Constructor
    public DatabaseSeed(CarPolicyRepository carPolicyrepository,
                        CustomerRepository customerRepository,
                        CarRepository carRepository,
                        LicensePlateRepository licensePlateRepository,
                        WeightsRepository weightsRepository) {
        this.carPolicyrepository = carPolicyrepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.licensePlateRepository = licensePlateRepository;
        this.weightsRepository = weightsRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        boolean condition = false;

        if (carRepository.count()==0 || condition)
        {
            seedCar();
            System.out.println("CAR SEED IS COMPLETED");
        }
        if (customerRepository.count() == 0 || condition)
        {
            seedDataCustomer();
            System.out.println("CUSTOMER SEED IS COMPLETED");
        }
        if(licensePlateRepository.count()==0 || condition)
        {
            seedLicensePlate();
            System.out.println("LICENSE_PLATE SEED IS COMPLETED");
        }
        if (carPolicyrepository.count() == 0 || condition)
        {
           seedDataCarPolicies();
            System.out.println("CAR_POLICY SEED IS COMPLETED");
        }
        if(weightsRepository.count()==0 || condition)
        {
            seedDataWeights();
            System.out.println("WEIGHTS SEED IS COMPLETED");
        }

        System.out.println("DATABASE SEED IS COMPLETED");
    }

    private void seedCar(){
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
    private void seedLicensePlate(){
        List<LicensePlate> licensePlates = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            Long carId = (long) (i % 20 + 1);
            Long customerId = (long) (i % 20 + 1);

            Optional<Car> optionalCar = carRepository.findById(carId);
            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            if (optionalCar.isPresent() && optionalCustomer.isPresent())
            {
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
        for (int i = 0; i < 20; i++) {
            String tckn;
            do {
                tckn = String.format("%011d", random.nextInt(1_000_000_000) + 100_000_0000);
            } while (uniqueTCKNs.contains(tckn));
            uniqueTCKNs.add(tckn);
        }
        customers.add(Customer.builder()
                .name("Ahmet Yılmaz")
                .tckn(uniqueTCKNs.toArray()[0].toString())
                .address("İstanbul, Türkiye")
                .phone("5321234567")
                .email("ahmet.yilmaz@example.com")
                .password("password123")
                .birthDay(LocalDate.of(1993, 1, 1)) // Doğum tarihi
                .gender(1)
                .grade(1)
                .build());

        customers.add(Customer.builder()
                .name("Ayşe Demir")
                .tckn(uniqueTCKNs.toArray()[1].toString())
                .address("Ankara, Türkiye")
                .phone("5322345678")
                .email("ayse.demir@example.com")
                .password("password234")
                .birthDay(LocalDate.of(1995, 2, 1)) // Doğum tarihi
                .gender(0)
                .grade(2)
                .build());

        customers.add(Customer.builder()
                .name("Mehmet Öz")
                .tckn(uniqueTCKNs.toArray()[2].toString())
                .address("İzmir, Türkiye")
                .phone("5323456789")
                .email("mehmet.oz@example.com")
                .password("password345")
                .birthDay(LocalDate.of(1988, 3, 1)) // Doğum tarihi
                .gender(1)
                .grade(3)
                .build());

        customers.add(Customer.builder()
                .name("Fatma Korkmaz")
                .tckn(uniqueTCKNs.toArray()[3].toString())
                .address("Bursa, Türkiye")
                .phone("5324567890")
                .email("fatma.korkmaz@example.com")
                .password("password456")
                .birthDay(LocalDate.of(1991, 4, 1)) // Doğum tarihi
                .gender(0)
                .grade(4)
                .build());

        customers.add(Customer.builder()
                .name("Ali Can")
                .tckn(uniqueTCKNs.toArray()[4].toString())
                .address("Antalya, Türkiye")
                .phone("5325678901")
                .email("ali.can@example.com")
                .password("password567")
                .birthDay(LocalDate.of(1983, 5, 1))
                .gender(1)
                .grade(5)
                .build());

        customers.add(Customer.builder()
                .name("Emine Çelik")
                .tckn(uniqueTCKNs.toArray()[5].toString())
                .address("Konya, Türkiye")
                .phone("5326789012")
                .email("emine.celik@example.com")
                .password("password678")
                .birthDay(LocalDate.of(1994, 6, 1)) // Doğum tarihi
                .gender(0)
                .grade(6)
                .build());

        customers.add(Customer.builder()
                .name("Burak Yıldız")
                .tckn(uniqueTCKNs.toArray()[6].toString())
                .address("Adana, Türkiye")
                .phone("5327890123")
                .email("burak.yildiz@example.com")
                .password("password789")
                .birthDay(LocalDate.of(1990, 7, 1)) // Doğum tarihi
                .gender(1)
                .grade(7)
                .build());

        customers.add(Customer.builder()
                .name("Zeynep Arslan")
                .tckn(uniqueTCKNs.toArray()[7].toString())
                .address("Gaziantep, Türkiye")
                .phone("5328901234")
                .email("zeynep.arslan@example.com")
                .password("password890")
                .birthDay(LocalDate.of(1998, 8, 1)) // Doğum tarihi
                .gender(0)
                .grade(1)
                .build());

        customers.add(Customer.builder()
                .name("Cemal Akman")
                .tckn(uniqueTCKNs.toArray()[8].toString())
                .address("Kayseri, Türkiye")
                .phone("5329012345")
                .email("cemal.akman@example.com")
                .password("password901")
                .birthDay(LocalDate.of(1986, 9, 1)) // Doğum tarihi
                .gender(1)
                .grade(2)
                .build());

        customers.add(Customer.builder()
                .name("Merve Koç")
                .tckn(uniqueTCKNs.toArray()[9].toString())
                .address("Sakarya, Türkiye")
                .phone("5320123456")
                .email("merve.koc@example.com")
                .password("password012")
                .birthDay(LocalDate.of(2001, 10, 1)) // Doğum tarihi
                .gender(0)
                .grade(3)
                .build());

        customers.add(Customer.builder()
                .name("Oğuzhan Şahin")
                .tckn(uniqueTCKNs.toArray()[10].toString())
                .address("Trabzon, Türkiye")
                .phone("5321234568")
                .email("oguzhan.sahin@example.com")
                .password("password1234")
                .birthDay(LocalDate.of(1996, 11, 1)) // Doğum tarihi
                .gender(1)
                .grade(4)
                .build());

        customers.add(Customer.builder()
                .name("Seda Eren")
                .tckn(uniqueTCKNs.toArray()[11].toString())
                .address("Eskişehir, Türkiye")
                .phone("5322345679")
                .email("seda.eren@example.com")
                .password("password2345")
                .birthDay(LocalDate.of(1992, 12, 1)) // Doğum tarihi
                .gender(0)
                .grade(5)
                .build());

        customers.add(Customer.builder()
                .name("Ali Rıza Polat")
                .tckn(uniqueTCKNs.toArray()[12].toString())
                .address("Kocaeli, Türkiye")
                .phone("5323456780")
                .email("ali.riza.polat@example.com")
                .password("password3456")
                .birthDay(LocalDate.of(1989, 1, 1)) // Doğum tarihi
                .gender(1)
                .grade(6)
                .build());

        customers.add(Customer.builder()
                .name("Nihal Aydın")
                .tckn(uniqueTCKNs.toArray()[13].toString())
                .address("Manisa, Türkiye")
                .phone("5324567891")
                .email("nihayl.aydin@example.com")
                .password("password4567")
                .birthDay(LocalDate.of(1997, 2, 1)) // Doğum tarihi
                .gender(0)
                .grade(5)
                .build());

        customers.add(Customer.builder()
                .name("Ege Şimşek")
                .tckn(uniqueTCKNs.toArray()[14].toString())
                .address("Samsun, Türkiye")
                .phone("5325678902")
                .email("ege.simsek@example.com")
                .password("password5678")
                .birthDay(LocalDate.of(2000, 3, 1)) // Doğum tarihi
                .gender(1)
                .grade(4)
                .build());

        customers.add(Customer.builder()
                .name("Büşra Kaplan")
                .tckn(uniqueTCKNs.toArray()[15].toString())
                .address("Diyarbakır, Türkiye")
                .phone("5326789013")
                .email("busra.kaplan@example.com")
                .password("password6789")
                .birthDay(LocalDate.of(1999, 4, 1)) // Doğum tarihi
                .gender(0)
                .grade(3)
                .build());

        customers.add(Customer.builder()
                .name("Mert Çetin")
                .tckn(uniqueTCKNs.toArray()[16].toString())
                .address("Malatya, Türkiye")
                .phone("5327890124")
                .email("mert.cetin@example.com")
                .password("password7890")
                .birthDay(LocalDate.of(1985, 5, 1)) // Doğum tarihi
                .gender(1)
                .grade(2)
                .build());

        customers.add(Customer.builder()
                .name("Eylül Aksu")
                .tckn(uniqueTCKNs.toArray()[17].toString())
                .address("Rize, Türkiye")
                .phone("5328901235")
                .email("eylul.aksu@example.com")
                .password("password8901")
                .birthDay(LocalDate.of(1993, 6, 1))
                .gender(0)
                .grade(1)
                .build());

        customers.add(Customer.builder()
                .name("Deniz Özdemir")
                .tckn(uniqueTCKNs.toArray()[18].toString())
                .address("Aydın, Türkiye")
                .phone("5329012346")
                .email("deniz.ozdemir@example.com")
                .password("password9012")
                .birthDay(LocalDate.of(1981, 7, 1))
                .gender(1)
                .grade(1)
                .build());

        customers.add(Customer.builder()
                .name("Şeyma Korkut")
                .tckn(uniqueTCKNs.toArray()[19].toString())
                .address("Aksaray, Türkiye")
                .phone("5320123457")
                .email("seyma.korkut@example.com")
                .password("password0123")
                .birthDay(LocalDate.of(1994, 8, 1))
                .gender(0)
                .grade(4)
                .build());



        customerRepository.saveAll(customers);
    }
    private void seedDataCarPolicies() {

        CarPolicyState state;
        Random random = new Random();
        List<CarPolicy> carPolicies = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long licensePlateId = (long) (i % 100 + 1);


            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<LicensePlate> optionalLicensePlate = licensePlateRepository.findById(licensePlateId);

            if (optionalCustomer.isPresent() && optionalLicensePlate.isPresent()) {
                Customer customer = optionalCustomer.get();
                LicensePlate licensePlate = optionalLicensePlate.get();
                LocalDate startDate = LocalDate.of(2024, 10, 1);
                LocalDate policyLocalDate = startDate.plusDays(i);

                state = CarPolicyState.values()[random.nextInt(CarPolicyState.values().length)];


                CarPolicy policy = CarPolicy.builder()
                        .policyDescription("Açıklama " + i)
                        .policyType(i % 2 == 0 ? 101 : 102)
                        .policyStartDate(policyLocalDate)
                        .policyEndDate(policyLocalDate.plusYears(1))
                        .policyAmount(1000.0 + (i * 100))
                        .policyOfferDate(policyLocalDate)
                        .licensePlate(licensePlate)
                        .customer(customer)
                        .state(state)
                        .build();

                carPolicies.add(policy);
            }

                carPolicyrepository.saveAll(carPolicies);
        }
    }
    private void seedDataWeights()
    {
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
}

