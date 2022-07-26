package com.expensify.Validators;

import com.expensify.model.Subscription;


import java.util.Date;

public class SubscriptionValidator implements IValidator{
    @Override

    public String validate(Object object) {
        Subscription subscription = (Subscription) object;
        Date date = new Date(subscription.getExpiryDate());
        Date today = new Date();
        if(!date.after(today)){
            return "Date can't be smaller than today";
        }
        return null;
    }
}
