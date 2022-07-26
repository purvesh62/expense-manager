package com.expensify.Validators;

import com.expensify.model.Wallet;

public class WalletValidator implements IValidator {
    @Override
    public String validate(Object object) {
        Wallet wallet = (Wallet) object;
        if (wallet.getAmount() <= 0) {
            return "Wallet amount cannot be zero or less";
        }
        if (wallet.getWalletLabel().equals("")) {
            return "Label cannot be empty";
        }
        return null;
    }
}
