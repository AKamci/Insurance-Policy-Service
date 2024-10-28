package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.domain.model.LicensePlateModel;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class Calculator {

    public static LicensePlateModel Calculate(LicensePlateModel licensePlateModel, LicensePlateModel licensePlateModel_Rq) {

     //Poliçe sonrası hesap

        Car car = licensePlateModel.car();
        Customer customer = licensePlateModel.customer();

        String policyType = licensePlateModel_Rq.policyType();
        LocalDate startDate = licensePlateModel_Rq.policyStartDate();
        LocalDate endDate = licensePlateModel_Rq.policyEndDate();
        long daysDifference = ChronoUnit.DAYS.between(startDate, endDate)/25;


        double policyTypeMultiplier = policyType.equals("Kasko") ? 3.5 :
                policyType.equals("Trafik") ? 1.5 : 1;
        double basePremium = 500;
        double ageFactor = (2024 - car.getYear()) * 20;
        double engineFactor = car.getEngine().equalsIgnoreCase("diesel") ? 150 : 100;
        double kilometersFactor = (car.getKilometers() / 10000) * 50;
        double priceFactor = car.getPrice() * 0.01;

        LocalDate today = LocalDate.now();
        int customerAge = Period.between(customer.getBirthDay(), today).getYears();

        double customerAgeFactor = customerAge < 25 ? 200 : (customerAge > 60 ? 100 : 50);
        double genderFactor = customer.getGender().equalsIgnoreCase("Erkek") ? 100 : 50;


        double  totalPremium = daysDifference * (policyTypeMultiplier * (basePremium + ageFactor + engineFactor + kilometersFactor + priceFactor
                + customerAgeFactor + genderFactor));

        return new LicensePlateModel(
                licensePlateModel.id(),
                licensePlateModel.plate(),
                licensePlateModel.car(),
                licensePlateModel.customer(),
                licensePlateModel_Rq.policyType(),
                licensePlateModel_Rq.policyStartDate(),
                licensePlateModel_Rq.policyEndDate(),
                (long) totalPremium
        );
    }

}
