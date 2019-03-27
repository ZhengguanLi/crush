package com.zhengguan.crush.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.zhengguan.crush.user.ChargeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeServiceImpl implements StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Override
    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @Override
    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParam = new HashMap<>();
        chargeParam.put("amount", chargeRequest.getAmount());
        chargeParam.put("currency", chargeRequest.getCurrency());
        chargeParam.put("description", chargeRequest.getDescription());
        chargeParam.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParam);
    }
}
