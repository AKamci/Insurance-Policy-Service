package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.repository.CarRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.LicensePlateRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
public class DatabaseSeed implements CommandLineRunner {

    private final CarPolicyRepository carPolicyrepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final LicensePlateRepository licensePlateRepository;

    // Constructor
    public DatabaseSeed(CarPolicyRepository carPolicyrepository, CustomerRepository customerRepository, CarRepository carRepository, LicensePlateRepository licensePlateRepository) {
        this.carPolicyrepository = carPolicyrepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
        this.licensePlateRepository = licensePlateRepository;
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
        System.out.println("DATABASE SEED IS COMPLETED");
    }

    private void seedCar(){
        List<Car> cars = new ArrayList<>();

        cars.add(Car.builder()
                .make("Toyota")
                .model("Corolla")
                .year(2020)
                .engine("1.8L")
                .kilometers(25000)
                .price(18000)
                .build());

        cars.add(Car.builder()
                .make("Honda")
                .model("Civic")
                .year(2019)
                .engine("2.0L")
                .kilometers(30000)
                .price(22000)
                .build());

        cars.add(Car.builder()
                .make("Ford")
                .model("Focus")
                .year(2021)
                .engine("1.5L")
                .kilometers(15000)
                .price(20000)
                .build());

        cars.add(Car.builder()
                .make("BMW")
                .model("320i")
                .year(2022)
                .engine("2.0L")
                .kilometers(10000)
                .price(35000)
                .build());

        cars.add(Car.builder()
                .make("Mercedes")
                .model("A-Class")
                .year(2023)
                .engine("1.3L")
                .kilometers(5000)
                .price(40000)
                .build());

        cars.add(Car.builder()
                .make("Audi")
                .model("A4")
                .year(2018)
                .engine("2.0L")
                .kilometers(40000)
                .price(25000)
                .build());

        cars.add(Car.builder()
                .make("Volkswagen")
                .model("Golf")
                .year(2021)
                .engine("1.4L")
                .kilometers(12000)
                .price(21000)
                .build());

        cars.add(Car.builder()
                .make("Hyundai")
                .model("Elantra")
                .year(2020)
                .engine("2.0L")
                .kilometers(22000)
                .price(19000)
                .build());

        cars.add(Car.builder()
                .make("Kia")
                .model("Forte")
                .year(2019)
                .engine("1.6L")
                .kilometers(28000)
                .price(17000)
                .build());

        cars.add(Car.builder()
                .make("Nissan")
                .model("Altima")
                .year(2022)
                .engine("2.5L")
                .kilometers(8000)
                .price(23000)
                .build());

        cars.add(Car.builder()
                .make("Chevrolet")
                .model("Malibu")
                .year(2021)
                .engine("1.5L")
                .kilometers(18000)
                .price(19500)
                .build());

        cars.add(Car.builder()
                .make("Subaru")
                .model("Impreza")
                .year(2020)
                .engine("2.0L")
                .kilometers(24000)
                .price(21000)
                .build());

        cars.add(Car.builder()
                .make("Mazda")
                .model("Mazda3")
                .year(2021)
                .engine("2.5L")
                .kilometers(11000)
                .price(20500)
                .build());

        cars.add(Car.builder()
                .make("Tesla")
                .model("Model 3")
                .year(2023)
                .engine("Electric")
                .kilometers(3000)
                .price(45000)
                .build());

        cars.add(Car.builder()
                .make("Porsche")
                .model("Macan")
                .year(2022)
                .engine("2.0L")
                .kilometers(6000)
                .price(55000)
                .build());

        cars.add(Car.builder()
                .make("Lexus")
                .model("IS")
                .year(2021)
                .engine("2.0L")
                .kilometers(12000)
                .price(32000)
                .build());

        cars.add(Car.builder()
                .make("Chrysler")
                .model("300")
                .year(2019)
                .engine("3.6L")
                .kilometers(35000)
                .price(26000)
                .build());

        cars.add(Car.builder()
                .make("Buick")
                .model("Regal")
                .year(2020)
                .engine("2.0L")
                .kilometers(28000)
                .price(23000)
                .build());

        cars.add(Car.builder()
                .make("Land Rover")
                .model("Range Rover")
                .year(2022)
                .engine("3.0L")
                .kilometers(5000)
                .price(90000)
                .build());

        cars.add(Car.builder()
                .make("Jaguar")
                .model("XE")
                .year(2021)
                .engine("2.0L")
                .kilometers(9000)
                .price(40000)
                .build());

        cars.add(Car.builder()
                .make("Mitsubishi")
                .model("Lancer")
                .year(2018)
                .engine("2.0L")
                .kilometers(45000)
                .price(15000)
                .build());

        cars.add(Car.builder()
                .make("Volvo")
                .model("S60")
                .year(2020)
                .engine("2.0L")
                .kilometers(15000)
                .price(35000)
                .build());

        cars.add(Car.builder()
                .make("Infiniti")
                .model("Q50")
                .year(2021)
                .engine("2.0L")
                .kilometers(8000)
                .price(33000)
                .build());

        cars.add(Car.builder()
                .make("Acura")
                .model("TLX")
                .year(2022)
                .engine("2.4L")
                .kilometers(7000)
                .price(36000)
                .build());

        cars.add(Car.builder()
                .make("Fiat")
                .model("500")
                .year(2019)
                .engine("1.4L")
                .kilometers(30000)
                .price(12000)
                .build());

        cars.add(Car.builder()
                .make("Peugeot")
                .model("308")
                .year(2020)
                .engine("1.6L")
                .kilometers(15000)
                .price(18000)
                .build());

        cars.add(Car.builder()
                .make("Renault")
                .model("Clio")
                .year(2019)
                .engine("1.2L")
                .kilometers(32000)
                .price(15000)
                .build());

        cars.add(Car.builder()
                .make("Citroën")
                .model("C3")
                .year(2020)
                .engine("1.2L")
                .kilometers(27000)
                .price(16000)
                .build());

        cars.add(Car.builder()
                .make("Skoda")
                .model("Octavia")
                .year(2021)
                .engine("2.0L")
                .kilometers(14000)
                .price(19000)
                .build());

        cars.add(Car.builder()
                .make("Dacia")
                .model("Sandero")
                .year(2020)
                .engine("1.0L")
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
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Ayşe Demir")
                .tckn(uniqueTCKNs.toArray()[1].toString())
                .address("Ankara, Türkiye")
                .phone("5322345678")
                .email("ayse.demir@example.com")
                .password("password234")
                .birthDay(LocalDate.of(1995, 2, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mehmet Öz")
                .tckn(uniqueTCKNs.toArray()[2].toString())
                .address("İzmir, Türkiye")
                .phone("5323456789")
                .email("mehmet.oz@example.com")
                .password("password345")
                .birthDay(LocalDate.of(1988, 3, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Fatma Korkmaz")
                .tckn(uniqueTCKNs.toArray()[3].toString())
                .address("Bursa, Türkiye")
                .phone("5324567890")
                .email("fatma.korkmaz@example.com")
                .password("password456")
                .birthDay(LocalDate.of(1991, 4, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Can")
                .tckn(uniqueTCKNs.toArray()[4].toString())
                .address("Antalya, Türkiye")
                .phone("5325678901")
                .email("ali.can@example.com")
                .password("password567")
                .birthDay(LocalDate.of(1983, 5, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Emine Çelik")
                .tckn(uniqueTCKNs.toArray()[5].toString())
                .address("Konya, Türkiye")
                .phone("5326789012")
                .email("emine.celik@example.com")
                .password("password678")
                .birthDay(LocalDate.of(1994, 6, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Burak Yıldız")
                .tckn(uniqueTCKNs.toArray()[6].toString())
                .address("Adana, Türkiye")
                .phone("5327890123")
                .email("burak.yildiz@example.com")
                .password("password789")
                .birthDay(LocalDate.of(1990, 7, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Zeynep Arslan")
                .tckn(uniqueTCKNs.toArray()[7].toString())
                .address("Gaziantep, Türkiye")
                .phone("5328901234")
                .email("zeynep.arslan@example.com")
                .password("password890")
                .birthDay(LocalDate.of(1998, 8, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Cemal Akman")
                .tckn(uniqueTCKNs.toArray()[8].toString())
                .address("Kayseri, Türkiye")
                .phone("5329012345")
                .email("cemal.akman@example.com")
                .password("password901")
                .birthDay(LocalDate.of(1986, 9, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Merve Koç")
                .tckn(uniqueTCKNs.toArray()[9].toString())
                .address("Sakarya, Türkiye")
                .phone("5320123456")
                .email("merve.koc@example.com")
                .password("password012")
                .birthDay(LocalDate.of(2001, 10, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Oğuzhan Şahin")
                .tckn(uniqueTCKNs.toArray()[10].toString())
                .address("Trabzon, Türkiye")
                .phone("5321234568")
                .email("oguzhan.sahin@example.com")
                .password("password1234")
                .birthDay(LocalDate.of(1996, 11, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Seda Eren")
                .tckn(uniqueTCKNs.toArray()[11].toString())
                .address("Eskişehir, Türkiye")
                .phone("5322345679")
                .email("seda.eren@example.com")
                .password("password2345")
                .birthDay(LocalDate.of(1992, 12, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Rıza Polat")
                .tckn(uniqueTCKNs.toArray()[12].toString())
                .address("Kocaeli, Türkiye")
                .phone("5323456780")
                .email("ali.riza.polat@example.com")
                .password("password3456")
                .birthDay(LocalDate.of(1989, 1, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Nihal Aydın")
                .tckn(uniqueTCKNs.toArray()[13].toString())
                .address("Manisa, Türkiye")
                .phone("5324567891")
                .email("nihayl.aydin@example.com")
                .password("password4567")
                .birthDay(LocalDate.of(1997, 2, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ege Şimşek")
                .tckn(uniqueTCKNs.toArray()[14].toString())
                .address("Samsun, Türkiye")
                .phone("5325678902")
                .email("ege.simsek@example.com")
                .password("password5678")
                .birthDay(LocalDate.of(2000, 3, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Büşra Kaplan")
                .tckn(uniqueTCKNs.toArray()[15].toString())
                .address("Diyarbakır, Türkiye")
                .phone("5326789013")
                .email("busra.kaplan@example.com")
                .password("password6789")
                .birthDay(LocalDate.of(1999, 4, 1)) // Doğum tarihi
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mert Çetin")
                .tckn(uniqueTCKNs.toArray()[16].toString())
                .address("Malatya, Türkiye")
                .phone("5327890124")
                .email("mert.cetin@example.com")
                .password("password7890")
                .birthDay(LocalDate.of(1985, 5, 1)) // Doğum tarihi
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Eylül Aksu")
                .tckn(uniqueTCKNs.toArray()[17].toString())
                .address("Rize, Türkiye")
                .phone("5328901235")
                .email("eylul.aksu@example.com")
                .password("password8901")
                .birthDay(LocalDate.of(1993, 6, 1))
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Deniz Özdemir")
                .tckn(uniqueTCKNs.toArray()[18].toString())
                .address("Aydın, Türkiye")
                .phone("5329012346")
                .email("deniz.ozdemir@example.com")
                .password("password9012")
                .birthDay(LocalDate.of(1981, 7, 1))
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Şeyma Korkut")
                .tckn(uniqueTCKNs.toArray()[19].toString())
                .address("Aksaray, Türkiye")
                .phone("5320123457")
                .email("seyma.korkut@example.com")
                .password("password0123")
                .birthDay(LocalDate.of(1994, 8, 1))
                .gender("Kadın")
                .build());



        customerRepository.saveAll(customers);
    }
    private void seedDataCarPolicies() {


        List<CarPolicy> carPolicies = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {

            Long customerId = (long) (i % 20 + 1);
            Long licensePlateId = (long) (i % 100 + 1);


            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
            Optional<LicensePlate> optionalLicensePlate = licensePlateRepository.findById(licensePlateId);

            if (optionalCustomer.isPresent() && optionalLicensePlate.isPresent()) {
                Customer customer = optionalCustomer.get();
                LicensePlate licensePlate = optionalLicensePlate.get();


                LocalDate startDate = LocalDate.of(2023, 10, 1);
                LocalDate policyLocalDate = startDate.plusDays(i);

                CarPolicy policy = CarPolicy.builder()
                        .policyDescription("Açıklama " + i)
                        .policyType(i % 2 == 0 ? "Kasko" : "Trafik")
                        .policyStatus(i % 3 == 0)
                        .policyStartDate(policyLocalDate)
                        .policyEndDate(policyLocalDate.plusDays(10*i))
                        .policyAmount(1000.0 + (i * 100))
                        .policyOfferDate(policyLocalDate)
                        .licensePlate(licensePlate)
                        .customer(customer)
                        .build();

                carPolicies.add(policy);
            }

                carPolicyrepository.saveAll(carPolicies);
        }
    }
}

