package PolicyProject.policyService.infrastructure.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.CustomerRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.carPolicyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseSeed implements CommandLineRunner {

    private final carPolicyRepository carPolicyrepository;
    private final CustomerRepository customerRepository;

    // Constructor
    public DatabaseSeed(carPolicyRepository carPolicyrepository, CustomerRepository customerRepository) {
        this.carPolicyrepository = carPolicyrepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() == 0) {
            seedDataCustomer();
        }
        if (carPolicyrepository.count() == 0) {
            seedDataCarPolicies();
        }
    }

    private void seedDataCustomer() {

        List<Customer> customers = new ArrayList<>();

        customers.add(Customer.builder()
                .name("Ahmet Yılmaz")
                .address("İstanbul, Türkiye")
                .phone("0532-123-4567")
                .email("ahmet.yilmaz@example.com")
                .password("password123")
                .age(30)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Ayşe Demir")
                .address("Ankara, Türkiye")
                .phone("0532-234-5678")
                .email("ayse.demir@example.com")
                .password("password234")
                .age(28)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mehmet Öz")
                .address("İzmir, Türkiye")
                .phone("0532-345-6789")
                .email("mehmet.oz@example.com")
                .password("password345")
                .age(35)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Fatma Korkmaz")
                .address("Bursa, Türkiye")
                .phone("0532-456-7890")
                .email("fatma.korkmaz@example.com")
                .password("password456")
                .age(32)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Can")
                .address("Antalya, Türkiye")
                .phone("0532-567-8901")
                .email("ali.can@example.com")
                .password("password567")
                .age(40)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Emine Çelik")
                .address("Konya, Türkiye")
                .phone("0532-678-9012")
                .email("emine.celik@example.com")
                .password("password678")
                .age(29)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Burak Yıldız")
                .address("Adana, Türkiye")
                .phone("0532-789-0123")
                .email("burak.yildiz@example.com")
                .password("password789")
                .age(33)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Zeynep Arslan")
                .address("Gaziantep, Türkiye")
                .phone("0532-890-1234")
                .email("zeynep.arslan@example.com")
                .password("password890")
                .age(25)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Cemal Akman")
                .address("Kayseri, Türkiye")
                .phone("0532-901-2345")
                .email("cemal.akman@example.com")
                .password("password901")
                .age(38)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Merve Koç")
                .address("Sakarya, Türkiye")
                .phone("0532-012-3456")
                .email("merve.koc@example.com")
                .password("password012")
                .age(22)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Oğuzhan Şahin")
                .address("Trabzon, Türkiye")
                .phone("0532-123-4568")
                .email("oguzhan.sahin@example.com")
                .password("password1234")
                .age(27)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Seda Eren")
                .address("Eskişehir, Türkiye")
                .phone("0532-234-5679")
                .email("seda.eren@example.com")
                .password("password2345")
                .age(31)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ali Rıza Polat")
                .address("Kocaeli, Türkiye")
                .phone("0532-345-6780")
                .email("ali.riza.polat@example.com")
                .password("password3456")
                .age(34)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Nihal Aydın")
                .address("Manisa, Türkiye")
                .phone("0532-456-7891")
                .email("nihayl.aydin@example.com")
                .password("password4567")
                .age(26)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Ege Şimşek")
                .address("Samsun, Türkiye")
                .phone("0532-567-8902")
                .email("ege.simsek@example.com")
                .password("password5678")
                .age(23)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Büşra Kaplan")
                .address("Diyarbakır, Türkiye")
                .phone("0532-678-9013")
                .email("busra.kaplan@example.com")
                .password("password6789")
                .age(24)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Mert Çetin")
                .address("Malatya, Türkiye")
                .phone("0532-789-0124")
                .email("mert.cetin@example.com")
                .password("password7890")
                .age(39)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Eylül Aksu")
                .address("Rize, Türkiye")
                .phone("0532-890-1235")
                .email("eylul.aksu@example.com")
                .password("password8901")
                .age(30)
                .gender("Kadın")
                .build());

        customers.add(Customer.builder()
                .name("Deniz Özdemir")
                .address("Aydın, Türkiye")
                .phone("0532-901-2346")
                .email("deniz.ozdemir@example.com")
                .password("password9012")
                .age(36)
                .gender("Erkek")
                .build());

        customers.add(Customer.builder()
                .name("Gamze Arı")
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

            Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();

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
                        .licensePlate("34ABC" + String.format("%04d", i))
                        .customer(customer)
                        .car(null)
                        .build();

                carPolicies.add(policy);
            }

                carPolicyrepository.saveAll(carPolicies);
        }
    }
}

