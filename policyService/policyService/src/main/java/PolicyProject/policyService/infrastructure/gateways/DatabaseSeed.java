package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.repository.CarRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.LicensePlateRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Component
public class DatabaseSeed implements CommandLineRunner {

    private final carPolicyRepository carPolicyrepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;
    private final LicensePlateRepository licensePlateRepository;

    // Constructor
    public DatabaseSeed(carPolicyRepository carPolicyrepository, CustomerRepository customerRepository, CarRepository carRepository, LicensePlateRepository licensePlateRepository) {
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
        if(licensePlateRepository.count()==0 || condition)
        {
            seedLicensePlate();
            System.out.println("LICENSE_PLATE SEED IS COMPLETED");
        }
        if (customerRepository.count() == 0 || condition)
        {
            seedDataCustomer();
            System.out.println("CUSTOMER SEED IS COMPLETED");
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

            Optional<Car> optionalCar = carRepository.findById(carId);
            if (optionalCar.isPresent())
            {
                Car car = optionalCar.get();
                licensePlates.add(LicensePlate.builder()
                        .plate("34ABC" + String.format("%04d", i))  // Örnek plaka: ABC001, ABC002, ...
                        .car(car)
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
                tckn = String.format("%011d", random.nextLong() & Long.MAX_VALUE);
            } while (uniqueTCKNs.contains(tckn));
            uniqueTCKNs.add(tckn);
        }

        customers.add(Customer.builder()
                .name("Ahmet Yılmaz")
                .tckn(uniqueTCKNs.toArray()[0].toString())
                .address("İstanbul, Türkiye")
                .phone("0532-123-4567")
                .email("ahmet.yilmaz@example.com")
                .password("password123")
                .age(30)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Ayşe Demir")
                .tckn(uniqueTCKNs.toArray()[1].toString())
                .address("Ankara, Türkiye")
                .phone("0532-234-5678")
                .email("ayse.demir@example.com")
                .password("password234")
                .age(28)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mehmet Öz")
                .tckn(uniqueTCKNs.toArray()[2].toString())
                .address("İzmir, Türkiye")
                .phone("0532-345-6789")
                .email("mehmet.oz@example.com")
                .password("password345")
                .age(35)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Fatma Korkmaz")
                .tckn(uniqueTCKNs.toArray()[3].toString())
                .address("Bursa, Türkiye")
                .phone("0532-456-7890")
                .email("fatma.korkmaz@example.com")
                .password("password456")
                .age(32)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Can")
                .tckn(uniqueTCKNs.toArray()[4].toString())
                .address("Antalya, Türkiye")
                .phone("0532-567-8901")
                .email("ali.can@example.com")
                .password("password567")
                .age(40)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Emine Çelik")
                .tckn(uniqueTCKNs.toArray()[5].toString())
                .address("Konya, Türkiye")
                .phone("0532-678-9012")
                .email("emine.celik@example.com")
                .password("password678")
                .age(29)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Burak Yıldız")
                .tckn(uniqueTCKNs.toArray()[6].toString())
                .address("Adana, Türkiye")
                .phone("0532-789-0123")
                .email("burak.yildiz@example.com")
                .password("password789")
                .age(33)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Zeynep Arslan")
                .tckn(uniqueTCKNs.toArray()[7].toString())
                .address("Gaziantep, Türkiye")
                .phone("0532-890-1234")
                .email("zeynep.arslan@example.com")
                .password("password890")
                .age(25)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Cemal Akman")
                .tckn(uniqueTCKNs.toArray()[8].toString())
                .address("Kayseri, Türkiye")
                .phone("0532-901-2345")
                .email("cemal.akman@example.com")
                .password("password901")
                .age(38)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Merve Koç")
                .tckn(uniqueTCKNs.toArray()[9].toString())
                .address("Sakarya, Türkiye")
                .phone("0532-012-3456")
                .email("merve.koc@example.com")
                .password("password012")
                .age(22)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Oğuzhan Şahin")
                .tckn(uniqueTCKNs.toArray()[10].toString())
                .address("Trabzon, Türkiye")
                .phone("0532-123-4568")
                .email("oguzhan.sahin@example.com")
                .password("password1234")
                .age(27)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Seda Eren")
                .tckn(uniqueTCKNs.toArray()[11].toString())
                .address("Eskişehir, Türkiye")
                .phone("0532-234-5679")
                .email("seda.eren@example.com")
                .password("password2345")
                .age(31)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Rıza Polat")
                .tckn(uniqueTCKNs.toArray()[12].toString())
                .address("Kocaeli, Türkiye")
                .phone("0532-345-6780")
                .email("ali.riza.polat@example.com")
                .password("password3456")
                .age(34)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Nihal Aydın")
                .tckn(uniqueTCKNs.toArray()[13].toString())
                .address("Manisa, Türkiye")
                .phone("0532-456-7891")
                .email("nihayl.aydin@example.com")
                .password("password4567")
                .age(26)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ege Şimşek")
                .tckn(uniqueTCKNs.toArray()[14].toString())
                .address("Samsun, Türkiye")
                .phone("0532-567-8902")
                .email("ege.simsek@example.com")
                .password("password5678")
                .age(23)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Büşra Kaplan")
                .tckn(uniqueTCKNs.toArray()[15].toString())
                .address("Diyarbakır, Türkiye")
                .phone("0532-678-9013")
                .email("busra.kaplan@example.com")
                .password("password6789")
                .age(24)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mert Çetin")
                .tckn(uniqueTCKNs.toArray()[16].toString())
                .address("Malatya, Türkiye")
                .phone("0532-789-0124")
                .email("mert.cetin@example.com")
                .password("password7890")
                .age(39)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Eylül Aksu")
                .tckn(uniqueTCKNs.toArray()[17].toString())
                .address("Rize, Türkiye")
                .phone("0532-890-1235")
                .email("eylul.aksu@example.com")
                .password("password8901")
                .age(30)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Deniz Özdemir")
                .tckn(uniqueTCKNs.toArray()[18].toString())
                .address("Aydın, Türkiye")
                .phone("0532-901-2346")
                .email("deniz.ozdemir@example.com")
                .password("password9012")
                .age(36)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Gamze Arı")
                .tckn(uniqueTCKNs.toArray()[19].toString())
                .address("Çorum, Türkiye")
                .phone("0532-012-3457")
                .email("gamze.ari@example.com")
                .password("password0123")
                .age(21)
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
                Date policyDate = Date.valueOf(policyLocalDate);

                CarPolicy policy = CarPolicy.builder()
                        .policyName("Poliçe " + i)
                        .policyDescription("Açıklama " + i)
                        .policyType(i % 2 == 0 ? "Kasko" : "Trafik")
                        .policyStatus(i % 2 == 0)
                        .policyDate(policyDate)
                        .policyAmount(1000.0 + (i * 100))
                        .licensePlate(licensePlate)
                        .customer(customer)
                        .build();

                carPolicies.add(policy);
            }

                carPolicyrepository.saveAll(carPolicies);
        }
    }
}

