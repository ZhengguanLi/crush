package com.zhengguan.crush.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.zhengguan.crush.service.StripeService;
import com.zhengguan.crush.user.ChargeRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model) throws StripeException{
        chargeRequest.setDescription("Crush checkout");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = paymentsService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "checkout/result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex){
        model.addAttribute("error", ex.getMessage());
        return "checkout/result";
    }

}
