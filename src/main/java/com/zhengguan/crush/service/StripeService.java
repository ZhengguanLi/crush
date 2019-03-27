package com.zhengguan.crush.service;

import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.zhengguan.crush.user.ChargeRequest;

@Service
public interface StripeService {
    public void init();
    public Charge charge(ChargeRequest chargeRequest)  throws StripeException;
}
