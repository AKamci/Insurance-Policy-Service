package PolicyProject.policyService.infrastructure.persistence.entity;

import PolicyProject.policyService.domain.model.CarPolicyModel;

public class Calculator {

    public static double Calculate(CarPolicyModel carPolicyModel) {
        Car car= carPolicyModel.car();

        double basePremium = 500;
        double ageFactor = (2024 - car.getYear()) * 20;
        double engineFactor = car.getEngine().equalsIgnoreCase("diesel") ? 150 : 100;
        double kilometersFactor = (car.getKilometers() / 10000) * 50;
        double priceFactor = car.getPrice() * 0.01;

        return basePremium + ageFactor + engineFactor + kilometersFactor + priceFactor;

    }
}
