package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.WeightGateway;
import PolicyProject.policyService.application.service.StrategyFactory.WeightStrategyFactory;
import PolicyProject.policyService.domain.Enums.Enums.CAR_AGE;
import PolicyProject.policyService.domain.Enums.Enums.CAR_PRICE;
import PolicyProject.policyService.domain.Enums.Enums.POLICY_TYPE;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.PolicyTypeStrategy.Kasko_Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExecuteWeight {


    private final WeightGateway weightGateway;

    public double getStrategy(LicensePlateModel licensePlateModel) {

        /// ... Stratejiler liste haline getirilmeli.

        Map<IWeightStrategy, String> weightStrategies = new HashMap<>();

       Kasko_Strategy kasko_strategy = (Kasko_Strategy) WeightStrategyFactory.getWeightStrategy(POLICY_TYPE.
               valueOf(licensePlateModel.policyType().toUpperCase()));

       weightStrategies.put(kasko_strategy, POLICY_TYPE.valueOf(licensePlateModel.policyType().toUpperCase()).name() );

       /// ... Oluşturulan liste için veritabanı sorgusu başka fonkiyonda yapılmalı.
        var parameters = getParameter(licensePlateModel);
        return getWeight(weightStrategies, parameters);
    }

    private List<Double> getParameter(LicensePlateModel licensePlateModel)
    {
        List<Double> parameters = new ArrayList<>();
        parameters.add(1.0);//licensePlateModel.policyType();
        parameters.add(1.0);
        /// ... Hesaplama için kullanılacak. Parametre değerleri.
        /// ... Bu liste ağırlıklar ile çarpılarak regresyon hesabı sonucu bize fiyatı verecek.
        return parameters;
    }

    private Double getWeight(Map<IWeightStrategy, String> weightStrategies, List<Double> parameters)
    {
        /// ... Gelen stratejiler ile veritabanı sorgusu yapılmalı ve ağırlıkları liste halinde dönmelidir.

        List<Double> weightDoubleList = new ArrayList<>();

        for (Map.Entry<IWeightStrategy, String> entry : weightStrategies.entrySet())
        {
            double weight = entry.getKey().getWeights(entry.getValue()).getValue();
            weightDoubleList.add(weight);
        }
     return Calculate(weightDoubleList, parameters);
    }


    private double Calculate(List<Double> weightDoubleList, List<Double> parameters)
    {
        /// ... Gelen parametreler ile formül yardımı ile hesaplama yapılmalı. Hesaplanan değer dönmelidir.
        /// ... Hesaplama regresyon ile yapılmalıdır.
        double Amount = 0;
        for (int i = 0; i < weightDoubleList.size(); i++)
        {
            Amount += parameters.get(i)*weightDoubleList.get(i);
        }

        return Amount;
    }
}